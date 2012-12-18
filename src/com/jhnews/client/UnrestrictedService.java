package com.jhnews.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jhnews.shared.Announcement;
import com.jhnews.shared.NoResultsException;
import com.jhnews.shared.Tags;

/**The AnnouncementFetcher service provides functionality for fetching the announcements from today from the server. In the future it will allow for fetching a variable number of announcements
 * @author Group 8
 */
@RemoteServiceRelativePath("UnrestrictedService")
public interface UnrestrictedService extends RemoteService {
	/** 
	 * Get the announcements for today, specifically, with a date == todays date
	 * @return A list of announcements from today
	 */
	List<Announcement> getTodaysAnnouncements();
	
	/**
	 * Performs a search of Announcements looking for the specified string
	 * @param query The string that is searched for
	 * @return The List of resulting Announcements
	 * @throws NoResultsException Thrown when there are no results
	 */
	List<Announcement> getAnnouncementsWithString(String query) throws NoResultsException;
	
	List<Tags> getAllActiveTags();
}
