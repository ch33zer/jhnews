package com.jhnews.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jhnews.shared.Announcement;

/**
 * Searches the database for specified Announcements
 * @author Group 8
 *
 */
public class SearchPage extends Page {
	
	private VerticalPanel masterPanel;
	private AnnouncementFetcherAsync service = GWT.create(AnnouncementFetcher.class);
	private TextBox queryText;
	private AnnouncementListPanel announcementListPanel;
	
	/**
	 * Default constructor requests the Announcement information from the server and populates the list
	 */
	public SearchPage() {
		if (service == null) {
			service = GWT.create(AnnouncementFetcher.class);
		}
		masterPanel = new VerticalPanel();
		masterPanel.addStyleName("leftVerticalPanel");
		Label pageTitleLabel = new Label("Search Announcement");
		pageTitleLabel.addStyleDependentName("title");
		pageTitleLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		masterPanel.add(pageTitleLabel);
		announcementListPanel = new AnnouncementListPanel();
		
		HorizontalPanel searchPanel = new HorizontalPanel();
		queryText = new TextBox();
		searchPanel.add(queryText);
		Button searchButton = new Button("Search", new ClickHandler() {
	
			@Override
			public void onClick(ClickEvent event) {
				service.getAnnouncementsWithString(queryText.getText(), new AsyncCallback<List<Announcement>>() {
					
					@Override
					public void onSuccess(List<Announcement> result) {
						announcementListPanel.setAnnouncementList(result);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						announcementListPanel.setAnnouncementList(null);
					}
				});
			}
		});
		searchPanel.add(searchButton);

		
		masterPanel.add(searchPanel);
		masterPanel.add(announcementListPanel);
	    initWidget(masterPanel);
	}

}
