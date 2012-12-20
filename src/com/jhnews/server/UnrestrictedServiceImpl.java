package com.jhnews.server;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jhnews.client.UnrestrictedService;
import com.jhnews.shared.Announcement;
import com.jhnews.shared.NoConfirmationException;
import com.jhnews.shared.NoResultsException;
import com.jhnews.shared.Tags;

/** The server component of the Announcement Fetcher. Retrieves announcements for the user
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
	
	
	/** 
	 * Get the announcements for today, specifically, with a date == todays date
	 * @return A list of announcements from today
	 */
	@Override
	public List<Announcement> getTodaysAnnouncements() {
		//Criterion criteria = Restrictions.and(Restrictions.eq("approved", false), Restrictions.gt("eventDate", new Date()));
		return getAnnouncements(Restrictions.eq("approved", true));//TODO make this return only todays announcements
	}

	/**
	 * Performs a search of Announcements looking for the specified string
	 * @param query The string that is searched for
	 * @return The List of resulting Announcements
	 * @throws NoResultsException Thrown when there are no results
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

	/**
	 * Gets all the announcement tags currently in use
	 * @return the active tags
	 */
	@Override
	public List<Tags> getAllActiveTags() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<TagsHibernate> tagsHibernate =  session.createCriteria(TagsHibernate.class).add(Restrictions.eq("active", true)).list();
		session.close();
		List<Tags> tags = HibernateConversionUtil.convertHibernateTagsList(tagsHibernate);
		return tags;
	}

	@Override
	public void confirmRegistration(String username, String confirmationCode) throws NoConfirmationException {
		org.hibernate.Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<UserHibernate> userHibernates = session.createCriteria(UserHibernate.class).add(Restrictions.eq("username", username)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		session.close();
		if ( userHibernates.size()!=0 && userHibernates.get(0)!= null) {
			UserHibernate userHibernate = userHibernates.get(0);
			if (userHibernate.getUserConfirmationCode().equals(confirmationCode)) {
				userHibernate.setUserConfirmationCode("0");
				Transaction tx = null;
				try {
					session = sessionFactory.openSession();
					tx=session.beginTransaction();
					session.update(userHibernate);
					tx.commit();
					session.close();
				}
				catch (Exception e) {
					if (tx != null) {
						tx.rollback();
						throw new NoConfirmationException();
					}
				}
			} else {
				throw new NoConfirmationException();
			}
		}
	}
	
	
}