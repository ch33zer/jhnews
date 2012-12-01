package com.jhnews.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jhnews.shared.Announcement;
import com.jhnews.shared.Session;

/** The async callback component to the LoginService
 * @author Group 8
 *
 */
public interface RestrictedServiceAsync {

	/** @see {@link RestrictedService#isLoggedIn(String)}
	 * @param sessionID
	 * @param callback
	 */
	void isLoggedIn(String sessionID, AsyncCallback<Boolean> callback);

	/** @see {@link RestrictedService#logIn(String, String)}
	 * @param username
	 * @param password
	 * @param callback
	 */
	void logIn(String username, String password, AsyncCallback<Session> callback);
	/** @see {@link RestrictedService#register(String, String)}
	 * @param username
	 * @param password
	 * @param callback
	 */
	void register(String username, String password,
			AsyncCallback<Session> callback);

	/** @see {@link RestrictedService#logOut(String)}
	 * @param sessionID
	 * @param callback
	 */
	void logOut(String sessionID, AsyncCallback<Void> callback);

	/** @see {@link UnrestrictedService#putAnnouncement(Announcement)}
	 * @param announcement
	 * @param callback
	 */
	void putAnnouncement(String sessionID, Announcement announcement, AsyncCallback<Void> callback);

	void getPendingAnnouncements(String sessionID,
			AsyncCallback<List<Announcement>> callback);

	void isAdmin(String sessionID, AsyncCallback<Boolean> callback);

}
