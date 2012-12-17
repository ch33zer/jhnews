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
public class PendingAnnouncementListPage extends AdminPage {
	
	private PendingAnnouncementListPanel announcementListPanel;
	private RestrictedServiceAsync restrictedService = GWT.create(RestrictedService.class);
	
	/**
	 * This is the default constructor that creates a panel containing a list of pending announcements.
	 *
	 */
	public PendingAnnouncementListPage() {
		setPageTitle("Pending Reviews");
	}

	@Override
	protected void createRestrictedContent() {
		if (restrictedService == null) {
			restrictedService = GWT.create(RestrictedService.class);
		}
		announcementListPanel = new PendingAnnouncementListPanel();
		restrictedService.getPendingAnnouncements(LoginManager.getInstance().getSessionID(), new AsyncCallback<List<Announcement>>() {
			
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