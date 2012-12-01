package com.jhnews.server;

import java.util.LinkedList;
import java.util.List;

import com.jhnews.shared.Announcement;
import com.jhnews.shared.Session;
import com.jhnews.shared.User;

/**
 * Converts to and from Hibernate classes and client side usable classes
 * @author Group 8
 *
 */
public class HibernateConversionUtil {
	
	/**
	 * Converts Announcement from Hibernate to client
	 * @param announcementHibernate Hibernate's version to be converted
	 * @return The client side version
	 */
	public static Announcement convertHibernateAnnouncment(AnnouncementHibernate announcementHibernate) {
		Announcement announcement = new Announcement();
		announcement.setTitle(announcementHibernate.getTitle());
		announcement.setBriefDescription(announcementHibernate.getBriefDescription());
		announcement.setLocation(announcementHibernate.getLocation());
		announcement.setLongDescription(announcementHibernate.getLongDescription());
		announcement.setID(announcementHibernate.getID());
		announcement.setEventDate(announcementHibernate.getEventDate());
		announcement.setTag1(announcementHibernate.isTag1());
		announcement.setTag2(announcementHibernate.isTag2());
		announcement.setTag3(announcementHibernate.isTag3());
		announcement.setTag4(announcementHibernate.isTag4());
		announcement.setTag5(announcementHibernate.isTag5());
		announcement.setToFreshman(announcementHibernate.isToFreshman());
		announcement.setToSophomore(announcementHibernate.isToSophomore());
		announcement.setToJunior(announcementHibernate.isToJunior());
		announcement.setToSenior(announcementHibernate.isToSenior());
		announcement.setToGraduate(announcementHibernate.isToGraduate());
		announcement.setToFaculty(announcementHibernate.isToFaculty());
		announcement.setHasEventTime(announcementHibernate.isHasEventTime());
		announcement.setApproved(announcementHibernate.isApproved());
		return announcement;
	}
	
	/**
	 * Converts Announcement from client to Hibernate
	 * @param announcement Client's version to be converted
	 * @param copyID Indicates whether or not the ID will be copied
	 * @return Hibernate's version
	 */
	public static AnnouncementHibernate convertAnnouncement(Announcement announcement, boolean copyID) {
		AnnouncementHibernate hibernateAnnouncement = new AnnouncementHibernate();
		hibernateAnnouncement.setTitle(announcement.getTitle());
		hibernateAnnouncement.setBriefDescription(announcement.getBriefDescription());
		hibernateAnnouncement.setLocation(announcement.getLocation());
		hibernateAnnouncement.setLongDescription(announcement.getLongDescription());
		hibernateAnnouncement.setEventDate(announcement.getEventDate());
		if (copyID) {
			hibernateAnnouncement.setID(announcement.getID());
		}		
		hibernateAnnouncement.setTag1(announcement.isTag1());
		hibernateAnnouncement.setTag2(announcement.isTag2());
		hibernateAnnouncement.setTag3(announcement.isTag3());
		hibernateAnnouncement.setTag4(announcement.isTag4());
		hibernateAnnouncement.setTag5(announcement.isTag5());
		hibernateAnnouncement.setToFreshman(announcement.isToFreshman());
		hibernateAnnouncement.setToSophomore(announcement.isToSophomore());
		hibernateAnnouncement.setToJunior(announcement.isToJunior());
		hibernateAnnouncement.setToSenior(announcement.isToSenior());
		hibernateAnnouncement.setToGraduate(announcement.isToGraduate());
		hibernateAnnouncement.setToFaculty(announcement.isToFaculty());
		hibernateAnnouncement.setHasEventTime(announcement.hasEventTime());
		hibernateAnnouncement.setApproved(announcement.isApproved());
		return hibernateAnnouncement;
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
		Session session = new Session();
		session.setExpireDate(hibernateSession.getExpireDate());
		session.setSessionID(hibernateSession.getSessionID());
		session.setUser(convertHibernateUser(hibernateSession.getUser()));
		session.setID(hibernateSession.getID());
		return session;
	}
	
	/**
	 * Converts Announcement from client to Hibernate
	 * @param userHibernate Client's version to be converted
	 * @return Hibernate's version
	 */
	public static User convertHibernateUser(UserHibernate userHibernate) {
		User user = new User();
		user.setEmail(userHibernate.getEmail());
		user.setFirstName(userHibernate.getFirstName());
		user.setHash(userHibernate.getHash());
		user.setID(userHibernate.getID());
		user.setLastName(userHibernate.getLastName());
		user.setUsername(userHibernate.getUsername());
		user.setTag1(userHibernate.isTag1());
		user.setTag2(userHibernate.isTag2());
		user.setTag3(userHibernate.isTag3());
		user.setTag4(userHibernate.isTag4());
		user.setTag5(userHibernate.isTag5());
		user.setAdmin(userHibernate.isAdmin());
		return user;
	}
}

