package com.jhnews.server;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jhnews.client.LoginService;
import com.jhnews.shared.LoginFailedException;
import com.jhnews.shared.NotLoggedInException;
import com.jhnews.shared.RegistrationFailedException;
import com.jhnews.shared.Session;
import com.jhnews.shared.UserExistsException;

/** The server side of the login service
 * @author Group 8
 *
 */
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {
	
	/**
	 * For Serialization	
	 */
	private static final long serialVersionUID = -5369307040587702583L;
	private SessionFactory sessionFactory;
	{
		sessionFactory = HibernateUtil.getSessionFactory();
	}
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

	/* (non-Javadoc)
	 * @see com.jhnews.client.LoginService#isLoggedIn(java.lang.String)
	 */
	@Override
	public boolean isLoggedIn(String sessionID) throws NotLoggedInException {
		if (sessionID == null) {
			throw new NotLoggedInException();
		}
		return checkDatabaseLogin(sessionID);
		//return sessionIDs.containsKey(sessionID) && sessionIDs.get(sessionID).after(new Date());
	}
	
	private boolean checkDatabaseLogin(String sessionID) {
		org.hibernate.Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<SessionHibernate> todayHibernate = session.createCriteria(SessionHibernate.class).add(Restrictions.and(Restrictions.eq("sessionID", sessionID), Restrictions.ge("expireDate", new Date()))).list();
		session.close();
		return todayHibernate.size() == 1;
	}



	/* (non-Javadoc)
	 * @see com.jhnews.client.LoginService#register(java.lang.String, java.lang.String)
	 */
	@Override
	public Session register(String username, String password) throws RegistrationFailedException, UserExistsException {
		//TODO
		/*if (username == null || password == null) {
			throw new RegistrationFailedException();
		}
		if (!FieldVerifier.isValidUserNameAndPassword(username, password)) {
			throw new RegistrationFailedException();
		}
		if (users.containsKey(username)) {
			throw new UserExistsException();
		}
		users.put(username, BCrypt.hashpw(password, BCrypt.gensalt()));
		try {
			return logIn(username, password);
		} catch (LoginFailedException e) {
			throw new RegistrationFailedException();
		}
		*/
		return null;
	}

	@Override
	public void logOut(String sessionID) {
		//TODO
		//sessionIDs.remove(sessionID);
	}

}
