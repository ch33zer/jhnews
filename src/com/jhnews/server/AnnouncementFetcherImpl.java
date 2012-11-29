package com.jhnews.server;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

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
	public List<Announcement> getTodaysAnnouncements() {
		return getAnnouncements(null);//TODO make this return only todays announcements
	}

	@Override
	public List<Announcement> getAnnouncementsWithString(String query) throws NoResultsException {
		if (query != null){
			return getAnnouncements(Restrictions.or(Restrictions.ilike("title", query,MatchMode.ANYWHERE),Restrictions.ilike("briefDescription", query,MatchMode.ANYWHERE),Restrictions.ilike("longDescription", query,MatchMode.ANYWHERE)));
		}
		throw new NoResultsException();
	}
	
	private List<Announcement> getAnnouncements(Criterion criteria)  {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<AnnouncementHibernate> todayHibernate = criteria != null ? session.createCriteria(AnnouncementHibernate.class).add(criteria).list():session.createCriteria(AnnouncementHibernate.class).list();
		session.close();
		List<Announcement> todays = HibernateUtil.convertHibernateAnnouncementList(todayHibernate);
		return todays;
	}

	@Override
	public void putAnnouncement(Announcement announcement) {
		AnnouncementHibernate announcementHibernate = HibernateUtil.convertAnnouncement(announcement, false);
		System.out.println(announcementHibernate);
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx=session.beginTransaction();
			session.save(announcementHibernate);
			tx.commit();
			session.close();
		}
		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}
	

}