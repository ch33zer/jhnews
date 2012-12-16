package com.jhnews.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jhnews.shared.Announcement;

/**
 * This page displays the individual pending announcement details
 * @author Group 8
 *
 */
public class PendingAnnouncementPage extends Page {
	
	private RestrictedServiceAsync service = GWT.create(RestrictedService.class);
	private Announcement announcement;
	
	/**
	 * This is the default constructor creates the announcement page based on the Announcement object in question.
	 * @param announcement The announcement details
	 */
	public PendingAnnouncementPage(Announcement announcement) {
		this.announcement = announcement;
		VerticalPanel masterPanel = new VerticalPanel();
		HorizontalPanel buttonPanel = new HorizontalPanel();
		AnnouncementPanel announcementPanel = new AnnouncementPanel(announcement);
		Button acceptButton = new Button("Accept", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				service.approveAnnouncement(LoginManager.getInstance().getSessionID(), PendingAnnouncementPage.this.announcement, new AsyncCallback<Void>() {	
					@Override
					public void onSuccess(Void result) {
						PageManager.getInstance().updateSideBar();
						PageManager.getInstance().setBody(PagesEnum.PENDING);
					}
					@Override
					public void onFailure(Throwable caught) {
						
					}
				});
			}	
		});
		
		Button rejectButton = new Button("Reject", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				service.declineAnnouncement(LoginManager.getInstance().getSessionID(), PendingAnnouncementPage.this.announcement, new AsyncCallback<Void>() {	
					@Override
					public void onSuccess(Void result) {
						PageManager.getInstance().updateSideBar();
						PageManager.getInstance().setBody(PagesEnum.PENDING);
					}
					@Override
					public void onFailure(Throwable caught) {
						
					}
				});
			}		
		});
		
		masterPanel.add(announcementPanel);
		buttonPanel.add(acceptButton);
		buttonPanel.add(rejectButton);
		buttonPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		masterPanel.add(buttonPanel);
		initWidget(masterPanel);
	}
}
