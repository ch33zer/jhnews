package com.jhnews.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jhnews.shared.Announcement;
import com.jhnews.shared.Tags;

/** 
 * The ASync component of AnnouncmentFetcher. This handles the callback component of the RPC call
 * @author Group 8
 *
 */
public interface UnrestrictedServiceAsync {

	/** 
	 * @see {@link UnrestrictedService#getTodaysAnnouncements()}
	 * @param callback
	 */
	void getTodaysAnnouncements(AsyncCallback<List<Announcement>> callback);
	
	/** @see {@link UnrestrictedService#getAnnouncementsWithString(String)}
	 * @param query
	 * @param callback
	 */
	void getAnnouncementsWithString(String query, AsyncCallback<List<Announcement>> callback);

	/** @see {@link UnrestrictedService#getAllActiveTags()}
	 * @param callback
	 */
	void getAllActiveTags(AsyncCallback<List<Tags>> callback);

	/** @see {@link UnrestrictedService#getAnnouncement(int)}
	 * @param ID
	 * @param callback
	 */
	void getAnnouncement(int ID, AsyncCallback<Announcement> callback);

	/** @see {@link UnrestrictedService#confirmRegistration(String, String)}
	 * @param username
	 * @param confirmationCode
	 * @param callback
	 */
	void confirmRegistration(String username, String confirmationCode,
			AsyncCallback<Void> callback);

	void getAnnouncementWithTags(List<Tags> tagList,
			AsyncCallback<List<Announcement>> callback);

	void getAnnouncementsByStringandTags(List<Tags> tagList, String query,
			AsyncCallback<List<Announcement>> callback);
}
