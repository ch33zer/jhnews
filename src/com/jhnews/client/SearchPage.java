package com.jhnews.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Clear;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.jhnews.shared.Announcement;

/**
 * Searches the database for specified Announcements
 * @author Group 8
 *
 */
public class SearchPage extends Page {
	
	private UnrestrictedServiceAsync unrestrictedService = GWT.create(UnrestrictedService.class);
	
	private TextBox queryText;
	private TagsPanel tagsPanel;
	private AnnouncementListPanel announcementListPanel;
	
	/**
	 * Default constructor requests the Announcement information from the server and populates the list
	 */
	public SearchPage() {
		if (unrestrictedService == null) {
			unrestrictedService = GWT.create(RestrictedService.class);
		}
		setPageTitle("Search Announcement");
		isLeftAlign();
		
		tagsPanel = new TagsPanel();
		
		announcementListPanel = new AnnouncementListPanel();
		
		HorizontalPanel searchPanel = new HorizontalPanel();
		queryText = new TextBox();
		searchPanel.add(queryText);
		Button searchButton = new Button("Search", new ClickHandler() {
	
			@Override
			public void onClick(ClickEvent event) {
				removeLabel();
				unrestrictedService.getAnnouncementsWithString(queryText.getText(), new AsyncCallback<List<Announcement>>() {	
					@Override
					public void onSuccess(List<Announcement> result) {
						announcementListPanel.setAnnouncementList(result);
						if (result.size() == 0) {
							setSuccess("No results");
						}
					}
					
					@Override
					public void onFailure(Throwable caught) {
						announcementListPanel.setAnnouncementList(null);
					}
				});
			}
		});
		searchPanel.add(searchButton);
		
		addWidget(tagsPanel);
		addWidget(searchPanel);
		addWidget(announcementListPanel);
	}

}
