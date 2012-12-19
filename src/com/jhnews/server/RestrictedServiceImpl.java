package com.jhnews.server;

import java.io.UnsupportedEncodingException;
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
import com.jhnews.shared.NotLoggedInException;
import com.jhnews.shared.RegistrationFailedException;
import com.jhnews.shared.Session;
import com.jhnews.shared.Tags;
import com.jhnews.shared.User;
import com.jhnews.shared.UserExistsException;

/** The server side of the login service
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

	
	
	
	
	/* (non-Javadoc)
	 * @see com.jhnews.client.LoginService#logIn(java.lang.String, java.lang.String)
	 */
	@Override
	public Session logIn(String username, String password) throws LoginFailedException {
		if (username == null || password == null) {
			throw new LoginFailedException();
		}
		UserHibernate user = getUser(username, password);
		if (user!=null) {
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

	/* (non-Javadoc)
	 * @see com.jhnews.client.LoginService#isLoggedIn(java.lang.String)
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



	/* (non-Javadoc)
	 * @see com.jhnews.client.LoginService#register(java.lang.String, java.lang.String)
	 */
	@Override
	public Session register(User user, String password) throws RegistrationFailedException, UserExistsException {

		if (user == null || password == null) {
			throw new RegistrationFailedException();
		}

		if (!FieldVerifier.isValidUserNameAndPassword(user.getUsername(), password)) {
			throw new RegistrationFailedException();
		}

		if (userExists(user.getUsername())) {
			throw new UserExistsException();
		}

		UserHibernate userHibernate= generateDefaultUser();
		userHibernate.setUsername(user.getUsername());
		userHibernate.setHash(BCrypt.hashpw(password, BCrypt.gensalt()));
		userHibernate.setFirstName(user.getFirstName());
		userHibernate.setLastName(user.getLastName());
		userHibernate.setEmail(user.getUsername());
		insertUser(userHibernate);
		try {
			return logIn(user.getUsername(), password);
		} catch (LoginFailedException e) {

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

	@Override
	public void logOut(String sessionID) {
		SessionHibernate hiberSession = getSessionFromSessionID(sessionID);
		if (hiberSession != null) {
			deleteSession(hiberSession);
		}
	}
	
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
		org.hibernate.Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<AnnouncementHibernate> todayHibernate = criteria != null ? session.createCriteria(AnnouncementHibernate.class).add(criteria).list():session.createCriteria(AnnouncementHibernate.class).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		List<Announcement> todays = HibernateConversionUtil.convertHibernateAnnouncementList(todayHibernate);
		session.close();
		return todays;
	}

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
			}
		}
	}

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
			}
		}
	}

	@Override
	public void run() {
		org.hibernate.Session session = sessionFactory.openSession();
		List<UserHibernate> users = session.createCriteria(UserHibernate.class).list();
		try {
			EmailSender.send(users, "TEST", "TEST");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.close();
	}
	
	private List<Tags> getAllActiveTags() {
		List<Tags> tags = HibernateConversionUtil.convertHibernateTagsList(getAllActiveTagsHibernate());
		return tags;
	}
	private List<TagsHibernate> getAllActiveTagsHibernate() {
		org.hibernate.Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<TagsHibernate> tagsHibernate =  session.createCriteria(TagsHibernate.class).add(Restrictions.eq("active", true)).list();
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
		List<TagsHibernate> tagsHibernate =  session.createCriteria(TagsHibernate.class).add(Restrictions.ilike("name", name)).list();
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
	
}
