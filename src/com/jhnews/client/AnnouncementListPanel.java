package com.jhnews.client;

import java.util.List;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jhnews.shared.Announcement;

/**
 * A Panel to handle Lists of Announcements
 * @author Group 8
 *
 */
public class AnnouncementListPanel extends Composite {
	
	private VerticalPanel masterPanel;
	protected List<Announcement> announcements;
	protected HandlerRegistration handlerRegistration;
	private final static DateTimeFormat FORMATTER_NO_TIME = DateTimeFormat.getFormat("EEEE, MMMM d, yyyy");
	private final static DateTimeFormat FORMATTER_TIME = DateTimeFormat.getFormat("EEEE, MMMM d, yyyy, h:mm aa");
	
	/**
	 * This is the default constructor that creates a panel containing a list of announcements.
	 *
	 */
	AnnouncementListPanel() {
		masterPanel = new VerticalPanel();
		masterPanel.addStyleName("leftVerticalPanel");
	    initWidget(masterPanel);
	}
	
	/**
	 * Populates the Panel with a List of Announcements
	 * @param announcements The List of Announcements which populate
	 */
	public void setAnnouncementList(List<Announcement> announcements) {
		this.announcements = announcements;
		if (announcements != null) {
			masterPanel.clear();
			for (Announcement announcement : announcements) {
				VerticalPanel announcementPanel = new VerticalPanel();
				announcementPanel.addStyleName("announcementPanel");
				VerticalPanel detailPanel = new VerticalPanel();
				detailPanel.addStyleName("detailPanel");
				announcementPanel.add(new Hyperlink(announcement.getTitle(), "" + announcement.getID()));
				detailPanel.add(new Label(announcement.getBriefDescription()));
				if (announcement.getLocation() != null) {
					detailPanel.add(new Label("Location: " + announcement.getLocation()));
				}
				if (announcement.getEventDate() != null) {
					if (announcement.hasEventTime()) {
						detailPanel.add(new Label("Date: " + FORMATTER_TIME.format(announcement.getEventDate())));
					} else {
						detailPanel.add(new Label("Date: " + FORMATTER_NO_TIME.format(announcement.getEventDate())));
					}
				}
				announcementPanel.add(detailPanel);
				masterPanel.add(announcementPanel);
			}
		} else {
			masterPanel.clear();
		}
	}

	/**
	 * Gets the list of announcements contained by this panel.
	 * @return the list of announcements
	 */
	public List<Announcement> getAnnouncements() {
		return announcements;
	}
}