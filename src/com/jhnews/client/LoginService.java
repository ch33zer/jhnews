package com.jhnews.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jhnews.shared.LoginFailedException;
import com.jhnews.shared.NotLoggedInException;
import com.jhnews.shared.RegistrationFailedException;
import com.jhnews.shared.Session;
import com.jhnews.shared.UserExistsException;
/** 
 * Provides a login and registration service. Uses RPC to communicate with a servlet.
 * @author Group 8
 *
 */
@RemoteServiceRelativePath("LoginService")
public interface LoginService extends RemoteService {
	/** Attempt to log in with the specified username and password
	 * @param username The users username
	 * @param password The users password
	 * @return The sessionID. Whenever we do anything that requires registration, this ID is required in the call
	 * @throws LoginFailedException If the login fails for any reason, this exception is raised
	 */
	Session logIn(String username, String password) throws LoginFailedException;
	/** Uses the sessionID to check if the user is logged in or not
	 * @param sessionID The users sessionID, obtained through a previous call to {@link #logIn(String, String)}
	 * @return Whether the user is logged on or not. AKA, whether or not their session ID is valid
	 * @throws NotLoggedInException Throw if the user is not logged on
	 */
	boolean isLoggedIn(String sessionID) throws NotLoggedInException;
	/**Registers the username and password with the server
	 * @param username The user's requested username
	 * @param password The user's password
	 * @return The session object for the current session. Registering also logs the user in.
	 * @throws RegistrationFailedException If the registration fails for any reason, this is thrown
	 * @throws UserExistsException 
	 */
	Session register(String username, String password) throws RegistrationFailedException, UserExistsException;
	
	void logOut(String sessionID);
}
