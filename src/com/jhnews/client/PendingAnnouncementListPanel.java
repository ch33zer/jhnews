package com.jhnews.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.jhnews.shared.Announcement;

/**
 * Extends the panel to generate PendingAnnouncementPages specifically
 * @author Group 8
 *
 */
public class PendingAnnouncementListPanel extends AnnouncementListPanel {
	
	/**
	 * Processes the Announcement hyperlink list clicked
	 * @param event The event of the hyperlink click
	 */
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		int ID;
		try {
			ID = Integer.parseInt(event.getValue());
		} catch(NumberFormatException e) {
			return;
		}
		handlerRegistration.removeHandler();
		for (Announcement announcement : announcements) {
			if (announcement.getID() == ID) {
				PageManager.getInstance().generatePendingAnnouncementPage(announcement);
				break;
			}
		}
	}
}
