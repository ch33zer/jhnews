package com.jhnews.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jhnews.shared.Announcement;

/** 
 * The ASync component of AnnouncmentFetcher. This handles the callback component of the RPC call
 * @author Group 8
 *
 */
public interface AnnouncementFetcherAsync {

	/** 
	 * @see {@link AnnouncementFetcher#getTodaysAnnouncements()}
	 * @param callback
	 */
	void getTodaysAnnouncements(AsyncCallback<List<Announcement>> callback);
	
	/** @see {@link AnnouncementFetcher#getAnnouncementsWithString(String)}
	 * @param query
	 * @param callback
	 */
	void getAnnouncementsWithString(String query, AsyncCallback<List<Announcement>> callback);

	void putAnnouncement(Announcement announcement, AsyncCallback<Void> callback);

}
