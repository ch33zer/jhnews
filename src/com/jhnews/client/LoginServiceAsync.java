package com.jhnews.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jhnews.shared.Session;

/** The async callback component to the LoginService
 * @author Group 8
 *
 */
public interface LoginServiceAsync {

	/** @see {@link LoginService#isLoggedIn(String)}
	 * @param sessionID
	 * @param callback
	 */
	void isLoggedIn(String sessionID, AsyncCallback<Boolean> callback);

	/** @see {@link LoginService#logIn(String, String)}
	 * @param username
	 * @param password
	 * @param callback
	 */
	void logIn(String username, String password, AsyncCallback<Session> callback);
	/** @see {@link LoginService#register(String, String)}
	 * @param username
	 * @param password
	 * @param callback
	 */
	void register(String username, String password,
			AsyncCallback<Session> callback);

	void logOut(String sessionID, AsyncCallback<Void> callback);

}
