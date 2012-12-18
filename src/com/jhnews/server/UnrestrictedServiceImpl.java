package com.jhnews.server;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jhnews.client.UnrestrictedService;
import com.jhnews.shared.Announcement;
import com.jhnews.shared.NoResultsException;
import com.jhnews.shared.Tags;

/** The server component of the Announcment Fetcher. Retrieves announcments for the user
 * @author Group 8
 *
 */
public class UnrestrictedServiceImpl extends RemoteServiceServlet implements UnrestrictedService {

	/**
	 * For serialization
	 */
	private static final long serialVersionUID = 5194159470059930101L;
	private SessionFactory sessionFactory;
	{
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	
	/* (non-Javadoc)
	 * @see com.jhnews.client.UnrestrictedService#getTodaysAnnouncements()
	 */
	@Override
	public List<Announcement> getTodaysAnnouncements() {
		//Criterion criteria = Restrictions.and(Restrictions.eq("approved", false), Restrictions.gt("eventDate", new Date()));
		return getAnnouncements(Restrictions.eq("approved", true));//TODO make this return only todays announcements
	}

	/* (non-Javadoc)
	 * @see com.jhnews.client.UnrestrictedService#getAnnouncementsWithString(java.lang.String)
	 */
	@Override
	public List<Announcement> getAnnouncementsWithString(String query) throws NoResultsException {
		if (query != null){
			return getAnnouncements(Restrictions.or(Restrictions.ilike("title", query,MatchMode.ANYWHERE),Restrictions.ilike("briefDescription", query,MatchMode.ANYWHERE),Restrictions.ilike("longDescription", query,MatchMode.ANYWHERE)));
		}
		throw new NoResultsException();
	}
	
	/**
	 * Gets the announcements based on the query
	 * @param criteria Definition for the query
	 * @return Results of the query
	 */
	private List<Announcement> getAnnouncements(Criterion criteria)  {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<AnnouncementHibernate> todayHibernate = criteria != null ? session.createCriteria(AnnouncementHibernate.class).add(criteria).list():session.createCriteria(AnnouncementHibernate.class).list();
		List<Announcement> todays = HibernateConversionUtil.convertHibernateAnnouncementList(todayHibernate);
		session.close();
		return todays;
	}

	@Override
	public List<Tags> getAllActiveTags() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<TagsHibernate> tagsHibernate =  session.createCriteria(TagsHibernate.class).add(Restrictions.eq("active", true)).list();
		session.close();
		List<Tags> tags = HibernateConversionUtil.convertHibernateTagsList(tagsHibernate);
		return tags;
	}
	

}