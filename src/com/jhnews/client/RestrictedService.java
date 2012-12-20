package com.jhnews.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jhnews.shared.Announcement;
import com.jhnews.shared.LoginFailedException;
import com.jhnews.shared.NotLoggedInException;
import com.jhnews.shared.RegistrationFailedException;
import com.jhnews.shared.Session;
import com.jhnews.shared.Tags;
import com.jhnews.shared.User;
import com.jhnews.shared.UserExistsException;
/** 
 * Provides a login and registration service. Uses RPC to communicate with a servlet.
 * @author Group 8
 *
 */
@RemoteServiceRelativePath("RestrictedService")
public interface RestrictedService extends RemoteService {

	@SuppressWarnings("javadoc")
	/* (non-Javadoc)
	 * @see com.jhnews.server.RestrictedServiceImpl#logIn(java.lang.String, java.lang.String)
	 */
	Session logIn(String username, String password) throws LoginFailedException;

	@SuppressWarnings("javadoc")
	/* (non-Javadoc)
	 * @see com.jhnews.server.RestrictedServiceImpl#isLoggedIn(java.lang.String)
	 */
	boolean isLoggedIn(String sessionID) throws NotLoggedInException;

	@SuppressWarnings("javadoc")
	/* (non-Javadoc)
	 * @see com.jhnews.server.RestrictedServiceImpl#register(java.lang.String, java.lang.String)
	 */
	Session register(User user, String password) throws RegistrationFailedException, UserExistsException;
	
	@SuppressWarnings("javadoc")
	/* (non-Javadoc)
	 * @see com.jhnews.server.RestrictedServiceImpl#logOut(java.lang.String, java.lang.String)
	 */
	void logOut(String sessionID);
	
	@SuppressWarnings("javadoc")
	/* (non-Javadoc)
	 * @see com.jhnews.server.RestrictedServiceImpl#putAnnouncement(java.lang.String, com.jhnews.shared.Announcement)
	 */
	void putAnnouncement(String sessionID, Announcement announcement) throws NotLoggedInException;
	
	@SuppressWarnings("javadoc")
	/* (non-Javadoc)
	 * @see com.jhnews.server.RestrictedServiceImpl#getPendingAnnouncement(java.lang.String)
	 */
	List<Announcement> getPendingAnnouncements(String sessionID);
	
	@SuppressWarnings("javadoc")
	/* (non-Javadoc)
	 * @see com.jhnews.server.RestrictedServiceImpl#approveAnnouncement(java.lang.String, com.jhnews.shared.Announcement)
	 */
	void approveAnnouncement(String sessionID, Announcement announcement);
	
	@SuppressWarnings("javadoc")
	/* (non-Javadoc)
	 * @see com.jhnews.server.RestrictedServiceImpl#declineAnnouncement(java.lang.String, com.jhnews.shared.Announcement)
	 */
	void declineAnnouncement(String sessionID, Announcement announcement);
	
	@SuppressWarnings("javadoc")
	/* (non-Javadoc)
	 * @see com.jhnews.server.RestrictedServiceImpl#isAdmin(java.lang.String)
	 */
	boolean isAdmin(String sessionID);
	
	@SuppressWarnings("javadoc")
	/* (non-Javadoc)
	 * @see com.jhnews.server.RestrictedServiceImpl#addAdmin(java.lang.String, java.lang.String)
	 */
	boolean addAdmin(String sessionID, String email);
	
	@SuppressWarnings("javadoc")
	/* (non-Javadoc)
	 * @see com.jhnews.server.RestrictedServiceImpl#addTag(java.lang.String, java.lang.String)
	 */
	void addTag(String sessionID, String tagName);
	
	@SuppressWarnings("javadoc")
	/* (non-Javadoc)
	 * @see com.jhnews.server.RestrictedServiceImpl#removeTag(java.lang.String, com.jhnews.shared.Tags)
	 */
	void removeTag(String sessionID, Tags tag);
	
	@SuppressWarnings("javadoc")
	/* (non-Javadoc)
	 * @see com.jhnews.server.RestrictedServiceImpl#getUser(java.lang.String)
	 */
	User getUser(String sessionID) throws NotLoggedInException;
	
	@SuppressWarnings("javadoc")
	/* (non-Javadoc)
	 * @see com.jhnews.server.RestrictedServiceImpl#saveUserTags(java.lang.String, com.jhnews.shared.User)
	 */
	void saveUserTags(String sessionID, User user);
}