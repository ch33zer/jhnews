package com.jhnews.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jhnews.shared.Announcement;

public class PendingAnnouncementPage extends Page {
	
	public PendingAnnouncementPage(Announcement announcement) {
		VerticalPanel masterPanel = new VerticalPanel();
		HorizontalPanel buttonPanel = new HorizontalPanel();
		AnnouncementPanel announcementPanel = new AnnouncementPanel(announcement);
		Button acceptButton = new Button("Accept", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}	
		});
		
		Button rejectButton = new Button("Reject", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
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
