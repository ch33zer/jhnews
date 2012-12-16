package com.jhnews.server;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import com.jhnews.shared.UserExistsException;

/** The server side of the login service
 * @author Group 8
 *
 */
public class RestrictedServiceImpl extends RemoteServiceServlet implements
		RestrictedService, ServletContextListener {
	
	/**
	 * For Serialization	
	 */
	private static final long serialVersionUID = -5369307040587702583L;
	private SessionFactory sessionFactory;
	{
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	private ScheduledExecutorService scheduler;
	/*private Map<String, String> users = new HashMap<String, String>(); //user to hashed password mapping
	private Map<String, Date> sessionIDs = new HashMap<String, Date>(); //Session ID to User mapping*/
	private final static long COOKIE_RETENTION_TIME = 1000 * 60 * 60 * 24;//1000 msecs * 60 secs * 60 minutes * 24 hours = 1 day
	/*{
		users.put("nir", BCrypt.hashpw("dog", BCrypt.gensalt())); //Single user with username
	}*/
	
	
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
			SessionHibernate session = new SessionHibernate();
			session.setSessionID(sessionID);
			Date expiration = new Date(System.currentTimeMillis() + COOKIE_RETENTION_TIME);
			session.setExpireDate(expiration);
			session.setUser(user);
			addSessionToDatabase(session);
			return HibernateConversionUtil.convertHibernateSession(session);
		}
		throw new LoginFailedException();	
	}
	
	private void addSessionToDatabase(SessionHibernate session) {
		Transaction tx = null;
		try {
			org.hibernate.Session hibernateSession = sessionFactory.openSession();
			tx=hibernateSession.beginTransaction();
			hibernateSession.save(session);
			tx.commit();
			hibernateSession.close();
		}
		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	private UserHibernate getUser(String username, String password) {
		org.hibernate.Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<UserHibernate> todayHibernate = session.createCriteria(UserHibernate.class).add(Restrictions.eq("username", username)).list();
		session.close();
		if ( todayHibernate.size()==1 && todayHibernate.get(0)!= null && BCrypt.checkpw(password,todayHibernate.get(0).getHash()))
			return todayHibernate.get(0);
		else {
			return null;
		}
	}
	
	private boolean userExists(String username) {
		org.hibernate.Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<UserHibernate> todayHibernate = session.createCriteria(UserHibernate.class).add(Restrictions.eq("username", username)).list();
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
		List<SessionHibernate> todayHibernate = session.createCriteria(SessionHibernate.class).add(Restrictions.and(Restrictions.eq("sessionID", sessionID), Restrictions.ge("expireDate", new Date()))).list();
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
	public Session register(String username, String password) throws RegistrationFailedException, UserExistsException {

		System.err.println("Adding!");
		if (username == null || password == null) {
			throw new RegistrationFailedException();
		}

		System.err.println("Not Null!");
		if (!FieldVerifier.isValidUserNameAndPassword(username, password)) {
			throw new RegistrationFailedException();
		}

		System.err.println("Valid!");
		if (userExists(username)) {
			throw new UserExistsException();
		}

		System.err.println("User doesn't exist!");
		UserHibernate userHibernate= generateDefaultUser();
		userHibernate.setUsername(username);
		userHibernate.setHash(BCrypt.hashpw(password, BCrypt.gensalt()));
		insertUser(userHibernate);
		try {
			return logIn(username, password);
		} catch (LoginFailedException e) {

			System.err.println("Failed!");
			throw new RegistrationFailedException();
		}
		
/*		if (users.containsKey(username)) {
			throw new UserExistsException();
		}
		users.put(username, BCrypt.hashpw(password, BCrypt.gensalt()));
		try {
			return logIn(username, password);
		} catch (LoginFailedException e) {
			throw new RegistrationFailedException();
		}*/
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
		userHibernate.setTag1(true);
		userHibernate.setTag2(true);
		userHibernate.setTag3(true);
		userHibernate.setTag4(true);
		userHibernate.setTag5(true);
		userHibernate.setTag6(true);
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
		List<AnnouncementHibernate> todayHibernate = criteria != null ? session.createCriteria(AnnouncementHibernate.class).add(criteria).list():session.createCriteria(AnnouncementHibernate.class).list();
		session.close();
		List<Announcement> todays = HibernateConversionUtil.convertHibernateAnnouncementList(todayHibernate);
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
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new EmailManager(), 0, 1, TimeUnit.DAYS);
	}
	
	private static void getMidnightOf(Date date) {
		
	}

}
