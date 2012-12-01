package com.jhnews.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jhnews.shared.Announcement;

public class PendingAnnouncementPage extends Page {
	
	private AnnouncementListPanel announcementListPanel;
	private RestrictedServiceAsync service = GWT.create(UnrestrictedService.class);
	
	public PendingAnnouncementPage() {
		announcementListPanel = new AnnouncementListPanel();
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
