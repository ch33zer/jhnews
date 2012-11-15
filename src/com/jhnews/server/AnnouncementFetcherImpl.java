package com.jhnews.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jhnews.client.AnnouncementFetcher;
import com.jhnews.shared.Announcement;
import com.jhnews.shared.NoResultsException;

/** The server component of the Announcment Fetcher. Retrieves announcments for the user
 * @author Group 8
 *
 */
public class AnnouncementFetcherImpl extends RemoteServiceServlet implements AnnouncementFetcher {

	/**
	 * For serialization
	 */
	private static final long serialVersionUID = 5194159470059930101L;
	private static List<Announcement> announcements;
	{
		Calendar calender = Calendar.getInstance();
		
		announcements = new ArrayList<Announcement>();
		
		Announcement announcement1 = new Announcement();
		announcement1.setID(1);
		announcement1.setTitle("Sterling Brunch");
		announcement1.setLocation("Homewood, FFC");
		calender.set(2012, 9, 21);
		announcement1.setEventDate(calender.getTime());
		announcement1.setHasEventTime(false);
		announcement1.setBriefDescription("Enjoy an all-you-care-to-eat special brunch menu with live jazz");
		announcement1.setLongDescription("Menu highlights include smoked salmon, peel-and-eat shrimp, a wonderful dessert " +
				"display and many other breakfast and lunch favorites.<br/>" +
				"Meal swipes, dining dollars, J-cash, cash, debit, credit accepted.<br/>" +
				"Only one regular meal swipe for meal plan participants<br/>" +
				"Dining dollars: $13.15<br/>" +
				"Cash/Debit/Credit/J-cash: $14.60");
		announcements.add(announcement1);
		
		Announcement announcement2 = new Announcement();
		announcement2.setTitle("Peabody Concert Orchestra");
		announcement2.setLocation("Peabody, Miriam A. Friedberg Concert Hall");
		calender.set(2012, 10, 2, 8, 0);
		announcement2.setEventDate(calender.getTime());
		announcement2.setHasEventTime(true);
		announcement2.setBriefDescription("The Peabody Concert Orchestra will perform under the direction of Hajime Teri Murai.");
		announcement2.setLongDescription("The Peabody Concert Orchestra will perform in Peabody's " +
				"Miriam A. Friedberg Concert Hall, located at 17 E. Mt. Vernon Pl., Baltimore. <br/>" +
				"Under the direction of Hajime Teri Murai, the program will include works by Libby Larsen, " +
				"Igor Stravinsky and Paul Hindemith. Cost is $15 Adults, $10 Seniors, $5 Students with ID.<br/>" +
				"For tickets, call 410-234-4800.");
		announcements.add(announcement2);
	}
	
	@Override
	public List<Announcement> getTodaysAnnouncements() {
		return announcements;
	}

	@Override
	public List<Announcement> getAnnouncementsWithString(String query) throws NoResultsException {
		if (query != null){
		List<Announcement> results = new ArrayList<Announcement>();
		for (Announcement announcement : announcements) {
			if (announcement.getLongDescription().contains(query) || announcement.getBriefDescription().contains(query) || announcement.getTitle().contains(query)) {
				results.add(announcement);
			}
		}
		if (results.size() > 0)
			return results;
		}
		throw new NoResultsException();
	}

	@Override
	public void putAnnouncement(Announcement announcement) {
		announcements.add(announcement);
	}
	

}