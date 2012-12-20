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

	List<Announcement> getTodaysAnnouncements();
	

	List<Announcement> getAnnouncementsWithString(String query) throws NoResultsException;
	
	List<Tags> getAllActiveTags();
}
