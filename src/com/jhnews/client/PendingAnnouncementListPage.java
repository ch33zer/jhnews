package com.jhnews.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jhnews.shared.Announcement;

/**
 * A Panel to handle list of pending announcements
 * @author Group 8
 *
 */
public class PendingAnnouncementListPage extends Page {
	
	private PendingAnnouncementListPanel announcementListPanel;
	private RestrictedServiceAsync service = GWT.create(RestrictedService.class);
	
	/**
	 * This is the default constructor that creates a panel containing a list of pending announcements.
	 *
	 */
	public PendingAnnouncementListPage() {
		announcementListPanel = new PendingAnnouncementListPanel();
		if (service == null) {
			service = GWT.create(UnrestrictedService.class);
		}
		service.getPendingAnnouncements(LoginManager.getInstance().getSessionID(), new AsyncCallback<List<Announcement>>() {
			
			@Override
			public void onSuccess(List<Announcement> result) {
				announcementListPanel.setAnnouncementList(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				announcementListPanel.setAnnouncementList(null);
			}
		});
	    initWidget(announcementListPanel);
	}
	
}