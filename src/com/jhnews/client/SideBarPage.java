package com.jhnews.client;

import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This page is the side of each view for navigation
 * @author Group 8
 *
 */
public class SideBarPage extends Page {
	
	/**
	 * Default constructor creates the sidebar based on who is logged in
	 */
	public SideBarPage() {
		boolean isAdmin = false;//TODO needs to be added
		
		Hyperlink searchAll = new Hyperlink("Search Announcements", "SEARCH");
		Hyperlink submitNew = new Hyperlink("Submit Announcement", "SUBMIT");
		
		Hyperlink reviewPending = new Hyperlink("Review Pending Announcements", "REVIEW");
		Hyperlink editTags = new Hyperlink("Edit Category Tags", "EDIT");
		
		VerticalPanel panel = new VerticalPanel();
		panel.addStyleName("leftVerticalPanel");
		
		panel.add(searchAll);
		panel.add(submitNew);
		if (isAdmin) {
			panel.add(reviewPending);
			panel.add(editTags);
		}
		initWidget(panel);
	}
	
}