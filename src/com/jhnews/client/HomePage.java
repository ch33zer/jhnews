package com.jhnews.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jhnews.shared.Announcement;

/**
 * This page is the default home page 
 * @author Group 8
 *
 */
public class HomePage extends Page implements ValueChangeHandler<String> {
	
	private VerticalPanel masterPanel;
	private List<Announcement> announcements;
	private AnnouncementFetcherAsync service = GWT.create(AnnouncementFetcher.class);
	private final static DateTimeFormat FORMATTER_NO_TIME = DateTimeFormat.getFormat("EEEE, MMMM d, yyyy");
	private final static DateTimeFormat FORMATTER_TIME = DateTimeFormat.getFormat("EEEE, MMMM d, yyyy, h:mm aa");
	private HandlerRegistration handlerRegistration;
	
	/**
	 * Default constructor requests the Announcement information from the server and populates the list
	 */
	public HomePage() {
		if (service == null) {
			service = GWT.create(AnnouncementFetcher.class);
		}
		service.getTodaysAnnouncements(new AsyncCallback<List<Announcement>>() {
			
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
		masterPanel = new VerticalPanel();
		masterPanel.addStyleName("leftVerticalPanel");
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
			int index = 0;
			for (Announcement announcement : announcements) {
				VerticalPanel announcementPanel = new VerticalPanel();
				announcementPanel.addStyleName("announcementPanel");
				VerticalPanel detailPanel = new VerticalPanel();
				detailPanel.addStyleName("detailPanel");
				announcementPanel.add(new Hyperlink(announcement.getTitle(), "" + index++));
				detailPanel.add(new Label(announcement.getBriefDescription()));
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
				announcementPanel.add(detailPanel);
				masterPanel.add(announcementPanel);
			}
		}
	}

}
