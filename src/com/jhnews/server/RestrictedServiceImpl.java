package com.jhnews.server;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jhnews.client.RestrictedService;
import com.jhnews.shared.Announcement;
import com.jhnews.shared.FieldVerifier;
import com.jhnews.shared.LoginFailedException;
import com.jhnews.shared.NoConfirmationException;
import com.jhnews.shared.NotLoggedInException;
import com.jhnews.shared.RegistrationFailedException;
import com.jhnews.shared.Session;
import com.jhnews.shared.Tags;
import com.jhnews.shared.TimeUtil;
import com.jhnews.shared.User;
import com.jhnews.shared.UserExistsException;

/** 
 * The server side of the login service
 * @author Group 8
 *
 */
public class RestrictedServiceImpl extends RemoteServiceServlet implements
		RestrictedService, ServletContextListener, Runnable {
	
	/**
	 * For Serialization	
	 */
	private static final long serialVersionUID = -5369307040587702583L;
	private SessionFactory sessionFactory;
	{
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	private ScheduledExecutorService scheduler;
	{
		scheduler = Executors.newSingleThreadScheduledExecutor();
	    scheduler.scheduleAtFixedRate(this, 0, 1, TimeUnit.DAYS);
	}
	private final static long COOKIE_RETENTION_TIME = 1000 * 60 * 60 * 24;//1000 msecs * 60 secs * 60 minutes * 24 hours = 1 day
	

	/** Attempt to log in with the specified username and password
	 * @param username The users username
	 * @param password The users password
	 * @return The sessionID. Whenever we do anything that requires registration, this ID is required in the call
	 * @throws LoginFailedException If the login fails for any reason, this exception is raised
	 */
	@Override
	public Session logIn(String username, String password) throws LoginFailedException, NoConfirmationException {
		if (username == null || password == null) {
			throw new LoginFailedException();
		}
		UserHibernate user = getUser(username, password);
		if (user!=null) {
			if (!user.getUserConfirmationCode().equals("0")) {
				throw new NoConfirmationException();
			}
			String sessionID = UUID.randomUUID().toString();
			SessionHibernate sessionHibernate = new SessionHibernate();
			sessionHibernate.setSessionID(sessionID);
			Date expiration = new Date(System.currentTimeMillis() + COOKIE_RETENTION_TIME);
			sessionHibernate.setExpireDate(expiration);
			sessionHibernate.setUser(user);
			Transaction tx = null;
			org.hibernate.Session hibernateSession = sessionFactory.openSession();
			Session session = null;
			try {
				tx=hibernateSession.beginTransaction();
				hibernateSession.save(sessionHibernate);
				session = HibernateConversionUtil.convertHibernateSession(sessionHibernate);
				tx.commit();
			}
			catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
			}
			hibernateSession.close();
			if (session != null) {
				return session;
			}
			throw new LoginFailedException();
		}
		throw new LoginFailedException();	
	}
	
	private UserHibernate getUser(String username, String password) {
		org.hibernate.Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<UserHibernate> todayHibernate = session.createCriteria(UserHibernate.class).add(Restrictions.eq("username", username)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		session.close();
		if ( todayHibernate.size()!=0 && todayHibernate.get(0)!= null && BCrypt.checkpw(password,todayHibernate.get(0).getHash()))
			return todayHibernate.get(0);
		else {
			return null;
		}
	}
	
	private boolean userExists(String username) {
		org.hibernate.Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<UserHibernate> todayHibernate = session.createCriteria(UserHibernate.class).add(Restrictions.eq("username", username)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		session.close();
		if ( todayHibernate.size()==1 && todayHibernate.get(0)!= null)
			return true;
		else {
			return false;
		}
	}


	/** Uses the sessionID to check if the user is logged in or not
	 * @param sessionID The users sessionID, obtained through a previous call to {@link #logIn(String, String)}
	 * @return Whether the user is logged on or not. AKA, whether or not their session ID is valid
	 * @throws NotLoggedInException Throw if the user is not logged on
	 */
	@Override
	public boolean isLoggedIn(String sessionID) throws NotLoggedInException {
		if (sessionID == null) {
			throw new NotLoggedInException();
		}
		return databaseIsLoggedIn(sessionID);
	}
	
	private boolean databaseIsLoggedIn(String sessionID) {
		return getUserFromSessionID(sessionID) != null;
	}
	
	private UserHibernate getUserFromSessionID(String sessionID) {
		SessionHibernate hiberSession = getSessionFromSessionID(sessionID);
		if (hiberSession != null) {
			return hiberSession.getUser();
		}
		return null;
	}
	
	private SessionHibernate getSessionFromSessionID(String sessionID) {
		org.hibernate.Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<SessionHibernate> todayHibernate = session.createCriteria(SessionHibernate.class).add(Restrictions.and(Restrictions.eq("sessionID", sessionID), Restrictions.ge("expireDate", new Date()))).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		session.close();
		if (todayHibernate.size() != 0) {
			return todayHibernate.get(0);
		}
		else {
			return null;
		}
	}

	/**Registers the username and password with the server
	 * @param user The user's information
	 * @param password The user's password
	 * @return The session object for the current session. Registering also logs the user in.
	 * @throws RegistrationFailedException If the registration fails for any reason, this is thrown
	 * @throws UserExistsException 
	 */
	@Override
	public void register(User user, String password) throws RegistrationFailedException, UserExistsException {

		if (user == null || password == null) {
			throw new RegistrationFailedException();
		}

		if (!FieldVerifier.isValidUserNameAndPassword(user.getUsername(), password)) {
			throw new RegistrationFailedException();
		}

		if (userExists(user.getUsername())) {
			throw new UserExistsException();
		}
		
		String confirmationCode = RandomStringUtils.randomAlphabetic(7).trim();

		UserHibernate userHibernate = generateDefaultUser();
		userHibernate.setUsername(user.getUsername());
		userHibernate.setHash(BCrypt.hashpw(password, BCrypt.gensalt()));
		userHibernate.setFirstName(user.getFirstName());
		userHibernate.setLastName(user.getLastName());
		userHibernate.setEmail(user.getUsername());
		userHibernate.setUserConfirmationCode(confirmationCode);
		insertUser(userHibernate);

		try {
			EmailSender.send(userHibernate, "Email Confirmation", confirmationCode, false);
		} catch (MessagingException e) {
			throw new RegistrationFailedException();
		} catch (UnsupportedEncodingException e) {
			throw new RegistrationFailedException();
		}
		
	}
	
	private void insertUser(UserHibernate userHibernate) {
		Transaction tx = null;
		try {
			org.hibernate.Session hibernateSession = sessionFactory.openSession();
			tx=hibernateSession.beginTransaction();
			hibernateSession.save(userHibernate);
			tx.commit();
			hibernateSession.close();
		}
		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	private UserHibernate generateDefaultUser() {
		UserHibernate userHibernate = new UserHibernate();
		userHibernate.setAdmin(false);
		userHibernate.setEmail("");
		userHibernate.setFirstName("");
		userHibernate.setLastName("");
		userHibernate.setHash("");
		Set<UserTagsHibernate> userTagsList = new HashSet<UserTagsHibernate>();
		UserTagsHibernate userTags;
		for (TagsHibernate tag : getAllActiveTagsHibernate()) {
			userTags = new UserTagsHibernate();
			userTags.setUser(userHibernate);
			userTags.setTags(tag);
			userTagsList.add(userTags);
		}
		userHibernate.setTags(userTagsList);
		userHibernate.setUsername("");
		return userHibernate;
	}

	private void deleteSession(SessionHibernate hiberSession) {
		Transaction tx = null;
		try {
			org.hibernate.Session session = sessionFactory.openSession();
			tx=session.beginTransaction();
			session.delete(hiberSession);
			tx.commit();
			session.close();
		}
		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	/**Log the current user out
	 * @param sessionID The sessionID corresponding to the current users session
	 */
	@Override
	public void logOut(String sessionID) {
		SessionHibernate hiberSession = getSessionFromSessionID(sessionID);
		if (hiberSession != null) {
			deleteSession(hiberSession);
		}
	}
	
	/**
	 * Saves an announcement on the server
	 * @param sessionID Session ID of the current user
	 * @param announcement The announcement to be saved
	 * @throws NotLoggedInException In the case that the user is not logged in
	 */
	@Override
	public void putAnnouncement(String sessionID, Announcement announcement) throws NotLoggedInException {
		UserHibernate user = getUserFromSessionID(sessionID);
		if (user != null) {
			AnnouncementHibernate announcementHibernate = HibernateConversionUtil.convertAnnouncement(announcement, false);
			announcementHibernate.setSubmitter(user);
			Transaction tx = null;
			try {
				org.hibernate.Session session = sessionFactory.openSession();
				tx=session.beginTransaction();
				session.save(announcementHibernate);
				tx.commit();
				session.close();
			}
			catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
			}
		}
	}
	private List<Announcement> getAnnouncements(Criterion criteria)  {
		return HibernateConversionUtil.convertHibernateAnnouncementList(getAnnouncementsHibernate(criteria));
	}
	
	private List<AnnouncementHibernate> getAnnouncementsHibernate(Criterion criteria)  {
		org.hibernate.Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<AnnouncementHibernate> todayHibernate = criteria != null ? session.createCriteria(AnnouncementHibernate.class).add(criteria).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list():session.createCriteria(AnnouncementHibernate.class).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		session.close();
		return todayHibernate;
	}

	/**
	 * Returns the pending announcements for the admin
	 * @param sessionID Session ID of the current user
	 * @return The List of pending announcements
	 */
	@Override
	public List<Announcement> getPendingAnnouncements(String sessionID) {
		UserHibernate user = getUserFromSessionID(sessionID);
		if (user != null) {
			if (user.isAdmin()) {
				return getAnnouncements(Restrictions.eq("approved", false));
			}
		}
		return null;
	}

	/**
	 * Determines if the current user is an admin
	 * @param sessionID Session ID of the current user
	 * @return True if the current user is an admin
	 */
	@Override
	public boolean isAdmin(String sessionID) {
		UserHibernate user = getUserFromSessionID(sessionID);
		if (user != null) {
			return user.isAdmin();
		}
		return false;
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		scheduler.shutdownNow();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	}
	
	private static void getMidnightOf(Date date) {
		
	}

	/**
	 * Allows the admin to approve the pending announcement
	 * @param sessionID Session ID of the admin
	 * @param announcement Announcement to approve
	 */
	@Override
	public void approveAnnouncement(String sessionID, Announcement announcement) {
		UserHibernate user = getUserFromSessionID(sessionID);
		if (user != null) {
			if (user.isAdmin()) {
				announcement.setApproved(true);
				AnnouncementHibernate announcementHibernate = HibernateConversionUtil.convertAnnouncement(announcement, true);
				Transaction tx = null;		
				try {
					org.hibernate.Session session = sessionFactory.openSession();
					tx = session.beginTransaction();
					session.update(announcementHibernate);
					tx.commit();
					session.close();
				}
				catch (Exception e) {
					if (tx != null) {
						tx.rollback();
					}
				}
				try {
					String message = "Your announcement " + announcementHibernate.getTitle()
							+ " has been accepted! It will appear on " 
							+ announcementHibernate.getEventDate();
					EmailSender.send(user, "Announcement Accepted", message, false);
				} catch (MessagingException e) {
					
				} catch (UnsupportedEncodingException e) {
					
				}
			}
		}
	}

	/**
	 * Allows the admin to decline the pending announcement
	 * @param sessionID Session ID of the admin
	 * @param announcement Announcement to decline
	 */
	@Override
	public void declineAnnouncement(String sessionID, Announcement announcement) {
		UserHibernate user = getUserFromSessionID(sessionID);
		if (user != null) {
			if (user.isAdmin()) {
				AnnouncementHibernate announcementHibernate = HibernateConversionUtil.convertAnnouncement(announcement, true);
				Transaction tx = null;
				try {
					org.hibernate.Session session = sessionFactory.openSession();
					tx = session.beginTransaction();
					session.delete(announcementHibernate);
					tx.commit();
					session.close();
				}
				catch (Exception e) {
					if (tx != null) {
						tx.rollback();
					}
				}
				try {
					String message = "Your announcement " + announcementHibernate.getTitle()
							+ " has been declined, sorry.";
					EmailSender.send(user, "Announcement Declined", message, false);
				} catch (MessagingException e) {
					
				} catch (UnsupportedEncodingException e) {
					
				}
			}
		}
	}

	@Override
	public void run() {
		org.hibernate.Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<UserHibernate> users = session.createCriteria(UserHibernate.class).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		session.close();
		for (UserHibernate user : users) {
			Set<AnnouncementHibernate> announcements = new HashSet<AnnouncementHibernate>();
			for (UserTagsHibernate tags : user.getTags()) {
				List<AnnouncementHibernate> annList = getAnnouncementsWithTag(getTodaysAnnouncementsHibernate(),tags.getTags());
				for (AnnouncementHibernate announcement : annList) {
					announcements.add(announcement);
				}
			}
			if (announcements.size() != 0 ) {
				String messageBody = generateAnnouncmentEmail(announcements);
				try {
					EmailSender.send(user, "Todays Announcements", messageBody, true);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	

	private final static DateFormat FORMATTER_NO_TIME = new SimpleDateFormat("EEEE, MMMM d, yyyy");
	private final static DateFormat FORMATTER_TIME = new SimpleDateFormat("EEEE, MMMM d, yyyy, h:mm aa");
	
	private String generateAnnouncmentEmail(
			Set<AnnouncementHibernate> announcements) {
		StringBuilder sb = new StringBuilder();
		sb.append("<head></head><body>");
		for (AnnouncementHibernate ann : announcements) {
			sb.append("<a href=\"http://127.0.0.1:8888/Jhnews.html?gwt.codesvr=127.0.0.1:9997#").append( ann.getID()).append("\">").append( "<p><b>").append(ann.getTitle()).append("</b></p>").append( "</a>" );
			sb.append("<p>").append(ann.isHasEventTime()?FORMATTER_TIME.format(ann.getEventDate()):FORMATTER_NO_TIME.format(ann.getEventDate())).append("</p>");
			sb.append("<p>").append(ann.getBriefDescription()).append("</p>");
			sb.append("<br>");
		}
		sb.append("</body>");
		return sb.toString();
	}

	private List<AnnouncementHibernate> getAnnouncementsWithTag(List<AnnouncementHibernate> announcements, TagsHibernate tags) {
		Iterator<AnnouncementHibernate> annIterator = announcements.iterator();
		List<AnnouncementHibernate> anns = new ArrayList<AnnouncementHibernate>();

		while (annIterator.hasNext()) {
			AnnouncementHibernate ann = annIterator.next();
			try {
				TagsHibernate tag1, tag2, tag3;
				tag1 = ann.getTag1();
				tag2 = ann.getTag2();
				tag3 = ann.getTag3();
				if (tag1 != null && tag1.equals(tags)) {
					anns.add(ann);
				} else if (tag2 != null && tag2.equals(tags)) {
					anns.add(ann);
				} else if (tag3 != null && tag3.equals(tags)) {
					anns.add(ann);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return announcements;
	}

	private List<Tags> getAllActiveTags() {
		List<Tags> tags = HibernateConversionUtil.convertHibernateTagsList(getAllActiveTagsHibernate());
		return tags;
	}
	private List<TagsHibernate> getAllActiveTagsHibernate() {
		org.hibernate.Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<TagsHibernate> tagsHibernate =  session.createCriteria(TagsHibernate.class).add(Restrictions.eq("active", true)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		session.close();
		return tagsHibernate;
	}

	@Override
	public boolean addAdmin(String sessionID, String email) {
		boolean success = false;
		if (isAdmin(sessionID)) {
			Transaction tx = null;
			try {
				org.hibernate.Session session = sessionFactory.openSession();
				List list = session.createCriteria(UserHibernate.class).add(Restrictions.ilike("email", email.trim())).list();
				if (list.size() > 0) {
					UserHibernate futureAdmin = (UserHibernate) list.get(0);
					futureAdmin.setAdmin(true);
					tx = session.beginTransaction();
					session.update(futureAdmin);
					tx.commit();
					success = true;
				}
				session.close();
			}
			catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
			}
		}
		return success;
	}

	@Override
	public void addTag(String sessionID, String tagName) {
		if (isAdmin(sessionID)) {
			Transaction tx = null;
			TagsHibernate tagHibernate = tagExists(tagName);
			if (tagHibernate == null) {
				tagHibernate = generateDefaultTag();
				tagHibernate.setName(tagName);
				try {
					org.hibernate.Session session = sessionFactory.openSession();
					tx = session.beginTransaction();
					session.save(tagHibernate);
					tx.commit();
					session.close();
				} catch (Exception e) {
					if (tx != null) {
						tx.rollback();
					}
				}
			} else if (!tagHibernate.isActive()) {
				tagHibernate.setActive(true);
				try {
					org.hibernate.Session session = sessionFactory.openSession();
					tx = session.beginTransaction();
					session.update(tagHibernate);
					tx.commit();
					session.close();
				} catch (Exception e) {
					if (tx != null) {
						tx.rollback();
					}
				}
			}
		}
	}
	
	@Override
	public void removeTag(String sessionID, Tags tag) {
		if (isAdmin(sessionID)) {
			tag.setActive(false);
			Transaction tx = null;
			try {
				org.hibernate.Session session = sessionFactory.openSession();
				tx = session.beginTransaction();
				session.update(HibernateConversionUtil.convertTags(tag));
				tx.commit();
				session.close();
			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
			}
		}
	}
	
	private TagsHibernate generateDefaultTag() {
		TagsHibernate tagHibernate = new TagsHibernate();
		tagHibernate.setName("");
		tagHibernate.setActive(true);
		return tagHibernate;
	}

	private TagsHibernate tagExists(String name) {
		org.hibernate.Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<TagsHibernate> tagsHibernate =  session.createCriteria(TagsHibernate.class).add(Restrictions.ilike("name", name)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		session.close();
		if ( tagsHibernate.size()==1 && tagsHibernate.get(0)!= null)
			return tagsHibernate.get(0);
		else {
			return null;
		}
	}

	@Override
	public User getUser(String sessionID) throws NotLoggedInException {
		if (isLoggedIn(sessionID))
			return HibernateConversionUtil.convertHibernateUser(getUserFromSessionID(sessionID));
		return null;
	}

	@Override
	public void saveUserTags(String sessionID, User user) {
		UserHibernate dbUser = getUserFromSessionID(sessionID);
		if (dbUser != null && user != null && dbUser.getUsername().equals(user.getUsername())) {
			Iterator<UserTagsHibernate> tagIter = dbUser.getTags().iterator();
			while (tagIter.hasNext()) {
				UserTagsHibernate utHib = tagIter.next();
				utHib.setUser(null);
				tagIter.remove();
			}
			//Delete old tags
			Transaction tx = null;
			try {
				org.hibernate.Session session = sessionFactory.openSession();
				tx = session.beginTransaction();
				session.update(dbUser);
				tx.commit();
				session.close();
			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
			}
			//Insert new ones
			tx = null;
			try {
				org.hibernate.Session session = sessionFactory.openSession();
				tx = session.beginTransaction();
				session.update(HibernateConversionUtil.convertUser(user));
				tx.commit();
				session.close();
			} catch (Exception e) {
				if (tx != null) {
					tx.rollback();
				}
			}
		}
	}
	
	public List<AnnouncementHibernate> getTodaysAnnouncementsHibernate() {
		//Criterion criteria = Restrictions.and(Restrictions.eq("approved", false), Restrictions.gt("eventDate", new Date()));
		return getAnnouncementsHibernate(Restrictions.and(Restrictions.eq("approved", true),Restrictions.ge("eventDate",TimeUtil.getMidnightOf(new Date())),Restrictions.le("eventDate", TimeUtil.getMidnightOfTomorrow(new Date()))));
	}


	
}
