package com.jhnews.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jhnews.shared.Announcement;

/**
 * Searches the database for specified Announcements
 * @author Group 8
 *
 */
public class SearchPage extends Page implements ValueChangeHandler<String>{
	
	private VerticalPanel announcementsPanel;
	private VerticalPanel masterPanel;
	private List<Announcement> announcements;
	private AnnouncementFetcherAsync service = GWT.create(AnnouncementFetcher.class);
	private final static DateTimeFormat FORMATTER_NO_TIME = DateTimeFormat.getFormat("EEEE, MMMM d, yyyy");
	private final static DateTimeFormat FOMRATTER_TIME = DateTimeFormat.getFormat("EEEE, MMMM d, yyyy, h:mm aa");
	private HandlerRegistration handlerRegistration;
	
	/**
	 * Default constructor requests the Announcement information from the server and populates the list
	 */
	public SearchPage() {
		if (service == null) {
			service = GWT.create(AnnouncementFetcher.class);
		}
		masterPanel = new VerticalPanel();
		HorizontalPanel searchPanel = new HorizontalPanel();
		searchPanel.add(new Label("Search string: "));
		final TextBox query = new TextBox();
		searchPanel.add(query);
		Button button = new Button("Submit", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				service.getAnnouncementsWithString(query.getText(), new AsyncCallback<List<Announcement>>() {
					
					@Override
					public void onSuccess(List<Announcement> result) {
						announcements = result;
						populateAnnouncementPanel();
					}
					
					@Override
					public void onFailure(Throwable caught) {
						announcements = null;
					}
				});
			}
		});
		searchPanel.add(button);
		announcementsPanel = new VerticalPanel();
		announcementsPanel.addStyleName("leftVerticalPanel");
		masterPanel.add(searchPanel);
		masterPanel.add(announcementsPanel);
	    initWidget(masterPanel);
	    handlerRegistration = History.addValueChangeHandler(this);
	}

	/**
	 * Processes the Announcement hyperlink list clicked
	 * @param event The event of the hyperlink click
	 */
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		int index;
		try {
			index = Integer.parseInt(event.getValue());
		} catch(NumberFormatException e) {
			return;
		}
		handlerRegistration.removeHandler();
		PageManager.getInstance().generateAnnouncementPage(announcements.get(index));
		}
	
	/**
	 * Populates the Announcement Panel with Announcements
	 */
	private void populateAnnouncementPanel() {
		if (announcements != null) {
			announcementsPanel.clear();
			int index = 0;
			for (Announcement announcement : announcements) {
				VerticalPanel announcementPanel = new VerticalPanel();
				announcementPanel.addStyleName("announcementPanel");
				announcementPanel.add(new Hyperlink(announcement.getTitle(), "" + index++));
				announcementPanel.add(new Label(announcement.getBriefDescription()));
				if (announcement.getLocation() != null) {
					announcementPanel.add(new Label("Location: " + announcement.getLocation()));
				}
				if (announcement.getEventDate() != null) {
					if (announcement.hasEventTime()) {
						announcementPanel.add(new Label("Date: " + FOMRATTER_TIME.format(announcement.getEventDate())));
					} else {
						announcementPanel.add(new Label("Date: " + FORMATTER_NO_TIME.format(announcement.getEventDate())));
					}
				}
				announcementsPanel.add(announcementPanel);
			}
		}
	}

}
