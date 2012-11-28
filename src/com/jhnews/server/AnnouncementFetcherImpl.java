package com.jhnews.server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
	private SessionFactory sessionFactory;
	{
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public List getTodaysAnnouncements() {
		Session session = sessionFactory.openSession();
		List todayHibernate = session.createCriteria(AnnouncementHibernate.class).list();
		session.close();
		List<Announcement> todays = new LinkedList<Announcement>();
		for (Object o : todayHibernate) {
				AnnouncementHibernate currentHibernate = (AnnouncementHibernate) o;
				Announcement current = new Announcement();
				current.setTitle(currentHibernate.getTitle());
				current.setBriefDescription(currentHibernate.getBriefDescription());
				current.setLocation(currentHibernate.getLocation());
				current.setLongDescription(currentHibernate.getLongDescription());
				current.setID(currentHibernate.getID());
				current.setEventDate(currentHibernate.getEventDate());
				todays.add(current);
		}
		return todays;
	}

	@Override
	public List<Announcement> getAnnouncementsWithString(String query) throws NoResultsException {
/*		if (query != null){
		List<Announcement> results = new ArrayList<Announcement>();
		for (Announcement announcement : announcements) {
			if (announcement.getLongDescription().contains(query) || announcement.getBriefDescription().contains(query) || announcement.getTitle().contains(query)) {
				results.add(announcement);
			}
		}
		if (results.size() > 0)
			return results;
		}*/
		throw new NoResultsException();
	}

	@Override
	public void putAnnouncement(Announcement announcement) {
		//announcements.add(announcement);
	}
	

}