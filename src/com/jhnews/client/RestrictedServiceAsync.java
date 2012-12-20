package com.jhnews.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jhnews.shared.Announcement;
import com.jhnews.shared.Session;
import com.jhnews.shared.Tags;
import com.jhnews.shared.User;

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
	
	/** @see {@link RestrictedService#register(User, String)}
	 * @param user
	 * @param password
	 * @param callback
	 */
	void register(User user, String password, AsyncCallback<Void> callback);

	/** @see {@link RestrictedService#logOut(String)}
	 * @param sessionID
	 * @param callback
	 */
	void logOut(String sessionID, AsyncCallback<Void> callback);

	/** @see {@link RestrictedService#putAnnouncement(String, Announcement)}
	 * @param sessionID
	 * @param announcement
	 * @param callback
	 */
	void putAnnouncement(String sessionID, Announcement announcement, AsyncCallback<Void> callback);

	/** @see {@link RestrictedService#getPendingAnnouncements(String)}
	 * @param sessionID
	 * @param callback
	 */
	void getPendingAnnouncements(String sessionID,
			AsyncCallback<List<Announcement>> callback);

	/** @see {@link RestrictedService#isAdmin(String)}
	 * @param sessionID
	 * @param callback
	 */
	void isAdmin(String sessionID, AsyncCallback<Boolean> callback);

	/** @see {@link RestrictedService#approveAnnouncement(String, Announcement)}
	 * @param sessionID
	 * @param announcement
	 * @param callback
	 */
	void approveAnnouncement(String sessionID, Announcement announcement,
			AsyncCallback<Void> callback);

	/** @see {@link RestrictedService#declineAnnouncement(String, Announcement)}
	 * @param sessionID
	 * @param announcement
	 * @param callback
	 */
	void declineAnnouncement(String sessionID, Announcement announcement,
			AsyncCallback<Void> callback);

	void addAdmin(String sessionID, String email,
			AsyncCallback<Boolean> callback);

	void addTag(String sessionID, String tagName, AsyncCallback<Void> callback);
	
	void removeTag(String sessionID, Tags tag, AsyncCallback<Void> callback);

	void getUser(String sessionID, AsyncCallback<User> callback);

	void saveUserTags(String sessionID, User user, AsyncCallback<Void> callback);

}
