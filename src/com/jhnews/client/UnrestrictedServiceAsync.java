package com.jhnews.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jhnews.shared.Announcement;

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

}
