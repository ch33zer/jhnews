package com.jhnews.server;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.jhnews.shared.Announcement;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new AnnotationConfiguration()
            .configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Announcement convertHibernateAnnouncment(AnnouncementHibernate announcementHibernate) {
		Announcement announcement = new Announcement();
		announcement.setTitle(announcementHibernate.getTitle());
		announcement.setBriefDescription(announcementHibernate.getBriefDescription());
		announcement.setLocation(announcementHibernate.getLocation());
		announcement.setLongDescription(announcementHibernate.getLongDescription());
		announcement.setID(announcementHibernate.getID());
		announcement.setEventDate(announcementHibernate.getEventDate());
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
		return hibernateAnnouncement;
	}
	
	public static List<Announcement> convertHibernateAnnouncementList(List<AnnouncementHibernate> list) {
		List<Announcement> announcments = new LinkedList<Announcement>();
		for (AnnouncementHibernate el : list) {
			announcments.add(convertHibernateAnnouncment(el));
		}
		return announcments;
	}
}