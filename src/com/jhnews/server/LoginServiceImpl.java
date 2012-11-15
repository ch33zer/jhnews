package com.jhnews.server;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jhnews.client.LoginService;
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
public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {
	
	/**
	 * For Serialization
	 */
	private static final long serialVersionUID = -5369307040587702583L;
	private Map<String, String> users = new HashMap<String, String>(); //user to hashed password mapping
	private Map<String, Date> sessionIDs = new HashMap<String, Date>(); //Session ID to User mapping
	private final static long COOKIE_RETENTION_TIME = 1000 * 60 * 60 * 24;//1000 msecs * 60 secs * 60 minutes * 24 hours = 1 day
	{
		users.put("nir", BCrypt.hashpw("dog", BCrypt.gensalt())); //Single user with username
	}
	/* (non-Javadoc)
	 * @see com.jhnews.client.LoginService#logIn(java.lang.String, java.lang.String)
	 */
	@Override
	public Session logIn(String username, String password) throws LoginFailedException {
		if (username == null || password == null) {
			throw new LoginFailedException();
		}
		
		if (users.get(username) != null && BCrypt.checkpw(password, users.get(username))) {
			String sessionID = UUID.randomUUID().toString();
			Session session = new Session();
			session.setSessionID(sessionID);
			Date expiration = new Date(System.currentTimeMillis() + COOKIE_RETENTION_TIME);
			session.setExpireDate(expiration);
			session.setUsername(username);
			sessionIDs.put(sessionID, expiration);
			return session;
		}
		throw new LoginFailedException();	
	}

	/* (non-Javadoc)
	 * @see com.jhnews.client.LoginService#isLoggedIn(java.lang.String)
	 */
	@Override
	public boolean isLoggedIn(String sessionID) throws NotLoggedInException {
		if (sessionID == null) {
			throw new NotLoggedInException();
		}
		return sessionIDs.containsKey(sessionID) && sessionIDs.get(sessionID).after(new Date());
	}



	/* (non-Javadoc)
	 * @see com.jhnews.client.LoginService#register(java.lang.String, java.lang.String)
	 */
	@Override
	public Session register(String username, String password) throws RegistrationFailedException, UserExistsException {
		if (username == null || password == null) {
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
		
	}

	@Override
	public void logOut(String sessionID) {
		sessionIDs.remove(sessionID);
	}

}
