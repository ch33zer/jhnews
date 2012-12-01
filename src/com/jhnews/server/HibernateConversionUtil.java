package com.jhnews.server;

import java.util.LinkedList;
import java.util.List;

import com.jhnews.shared.Announcement;
import com.jhnews.shared.Session;
import com.jhnews.shared.User;

public class HibernateConversionUtil {
	
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
		return announcement;
	}
	
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
		return hibernateAnnouncement;
	}
	
	public static List<Announcement> convertHibernateAnnouncementList(List<AnnouncementHibernate> list) {
		List<Announcement> announcments = new LinkedList<Announcement>();
		for (AnnouncementHibernate el : list) {
			announcments.add(convertHibernateAnnouncment(el));
		}
		return announcments;
	}
	
	public static Session convertHibernateSession(SessionHibernate hibernateSession) {
		Session session = new Session();
		session.setExpireDate(hibernateSession.getExpireDate());
		session.setSessionID(hibernateSession.getSessionID());
		session.setUser(convertHibernateUser(hibernateSession.getUser()));
		session.setID(hibernateSession.getID());
		return session;
	}
	
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
		return user;
	}
}

