package com.jhnews.server;

import java.util.LinkedList;
import java.util.List;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import com.jhnews.shared.Announcement;
import com.jhnews.shared.Session;
import com.jhnews.shared.Tags;
import com.jhnews.shared.User;

/**
 * Converts to and from Hibernate classes and client side usable classes
 * @author Group 8
 *
 */
public class HibernateConversionUtil {

	private static Mapper getDozerInstance() {
		return DozerBeanMapperSingletonWrapper.getInstance();
	}
	
	/**
	 * Converts Announcement from Hibernate to client
	 * @param announcementHibernate Hibernate's version to be converted
	 * @return The client side version
	 */
	public static Announcement convertHibernateAnnouncment(AnnouncementHibernate announcementHibernate) {
		return getDozerInstance().map(announcementHibernate, Announcement.class);
	}
	
	/**
	 * Converts lists of Hibernate tags to lists of client tags
	 * @param list the list of Hibernate Tags
	 * @return the list of client tags
	 */
	public static List<Tags> convertHibernateTagsList(List<TagsHibernate> list) {
		List<Tags> announcments = new LinkedList<Tags>();
		for (TagsHibernate el : list) {
			announcments.add(convertHibernateTags(el));
		}
		return announcments;
	}
	
	/**
	 * Convert Hibernate tags to client tags
	 * @param tags the Hibernate tags to convert
	 * @return the client tags
	 */
	public static Tags convertHibernateTags(TagsHibernate tags) {
		return getDozerInstance().map(tags, Tags.class);
	}
	
	/**
	 * Convert client Tags to Hibernate tags
	 * @param tags the client tags to convert
	 * @return Hibernate tags
	 */
	public static TagsHibernate convertTags(Tags tags) {
		return getDozerInstance().map(tags, TagsHibernate.class);
	}
	
	/**
	 * Converts Announcement from client to Hibernate
	 * @param announcement Client's version to be converted
	 * @param copyID Indicates whether or not the ID will be copied
	 * @return Hibernate's version
	 */
	public static AnnouncementHibernate convertAnnouncement(Announcement announcement, boolean copyID) {
		return getDozerInstance().map(announcement, AnnouncementHibernate.class);
	}
	
	/**
	 * Converts Announcement List from Hibernate to client
	 * @param list Hibernate's version to be converted
	 * @return The client side version
	 */
	public static List<Announcement> convertHibernateAnnouncementList(List<AnnouncementHibernate> list) {
		List<Announcement> announcments = new LinkedList<Announcement>();
		for (AnnouncementHibernate el : list) {
			announcments.add(convertHibernateAnnouncment(el));
		}
		return announcments;
	}
	
	/**
	 * Converts Session from Hibernate to client
	 * @param hibernateSession Hibernate's version to be converted
	 * @return The client side version
	 */
	public static Session convertHibernateSession(SessionHibernate hibernateSession) {
		return getDozerInstance().map(hibernateSession, Session.class);
	}
	
	/**
	 * Converts Announcement from client to Hibernate 
	 * @param userHibernate Client's version to be converted
	 * @return Hibernate's version
	 */
	public static User convertHibernateUser(UserHibernate userHibernate) {
		return getDozerInstance().map(userHibernate, User.class);
	}

	/**
	 * Converts User from client to Hibernate
	 * @param user the client's version of User
	 * @return the Hibernate version of User
	 */
	public static UserHibernate convertUser(User user) {
		return getDozerInstance().map(user, UserHibernate.class);
	}
}

