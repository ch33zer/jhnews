package com.jhnews.server;

import java.util.LinkedList;
import java.util.List;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.DozerInitializer;
import org.dozer.Mapper;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import com.jhnews.shared.Announcement;
import com.jhnews.shared.Session;
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
}

