package com.jhnews.client;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jhnews.shared.Announcement;

/**
 * This page displays the individual announcement details
 * @author Group 8
 *
 */
public class AnnouncementPanel extends Composite {

	private static final DateTimeFormat FORMATTER_NO_TIME = DateTimeFormat.getFormat("EEEE, MMMM d, yyyy");
	private static final DateTimeFormat FORMATTER_TIME = DateTimeFormat.getFormat("EEEE, MMMM d, yyyy, h:mm aa");
	
	/**
	 * This is the default constructor creates the announcement page based on the Announcement object in question.
	 *
	 * @param announcement The announcement on which to base the page
	 */
	public AnnouncementPanel(Announcement announcement) {
		VerticalPanel masterPanel = new VerticalPanel();
		masterPanel.addStyleName("leftVerticalPanel");
		VerticalPanel detailPanel = new VerticalPanel();
		detailPanel.addStyleName("detailPanel");
		Label announcementTitleLabel = new Label(announcement.getTitle());
		announcementTitleLabel.addStyleDependentName("bold");
		masterPanel.add(announcementTitleLabel);
		detailPanel.add(new HTML(announcement.getLongDescription()));
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
		String tags = "Tags: " + announcement.getTagString();
		detailPanel.add(new Label(tags));
		masterPanel.add(detailPanel);
		initWidget(masterPanel);
	}

}
