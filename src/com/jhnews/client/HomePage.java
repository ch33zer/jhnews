package com.jhnews.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jhnews.shared.Announcement;

/**
 * This page is the default home page 
 * @author Group 8
 *
 */
public class HomePage extends Page {
	
	private AnnouncementListPanel announcementListPanel;
	private UnrestrictedServiceAsync unrestrictedService = GWT.create(UnrestrictedService.class);
	
	/**
	 * Default constructor requests the Announcement information from the server and populates the list
	 */
	public HomePage() {
		if (unrestrictedService == null) {
			unrestrictedService = GWT.create(UnrestrictedService.class);
		}
		setPageTitle("Today's Announcements");
		announcementListPanel = new AnnouncementListPanel();
		unrestrictedService.getTodaysAnnouncements(new AsyncCallback<List<Announcement>>() {
			
			@Override
			public void onSuccess(List<Announcement> result) {
				announcementListPanel.setAnnouncementList(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				announcementListPanel.setAnnouncementList(null);
			}
		});
		addWidget(announcementListPanel);
	}

}
