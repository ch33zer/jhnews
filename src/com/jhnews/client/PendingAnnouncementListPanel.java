package com.jhnews.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;

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
		int index;
		try {
			index = Integer.parseInt(event.getValue());
		} catch(NumberFormatException e) {
			return;
		}
		handlerRegistration.removeHandler();
		PageManager.getInstance().generatePendingAnnouncementPage(announcements.get(index));
	}
	
}
