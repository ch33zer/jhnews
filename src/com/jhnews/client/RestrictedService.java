package com.jhnews.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jhnews.shared.Announcement;
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
@RemoteServiceRelativePath("RestrictedService")
public interface RestrictedService extends RemoteService {
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
	
	/**Log the current user out
	 * @param sessionID The sessionID corresponding to the current users session
	 */
	void logOut(String sessionID);
	
	/**
	 * Saves an announcement on the server
	 * @param sessionID Session ID of the current user
	 * @param announcement The announcement to be saved
	 * @throws NotLoggedInException In the case that the user is not logged in
	 */
	void putAnnouncement(String sessionID, Announcement announcement) throws NotLoggedInException;
	
	/**
	 * Returns the pending announcements for the admin
	 * @param sessionID Session ID of the current user
	 * @return The List of pending announcements
	 */
	List<Announcement> getPendingAnnouncements(String sessionID);
	
	/**
	 * Allows the admin to approve the pending announcement
	 * @param sessionID Session ID of the admin
	 * @param announcement Announcement to approve
	 */
	void approveAnnouncement(String sessionID, Announcement announcement);
	
	/**
	 * Allows the admin to decline the pending announcement
	 * @param sessionID Session ID of the admin
	 * @param announcement Announcement to decline
	 */
	void declineAnnouncement(String sessionID, Announcement announcement);
	
	/**
	 * Determines if the current user is an admin
	 * @param sessionID Session ID of the current user
	 * @return True if the current user is an admin
	 */
	boolean isAdmin(String sessionID);
}
