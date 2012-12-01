package com.jhnews.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jhnews.shared.Announcement;

/**
 * This page is the side of each view for navigation
 * @author Group 8
 *
 */
public class SideBarPage extends Page {
	
	private RestrictedServiceAsync service = GWT.create(UnrestrictedService.class);
	
	private Hyperlink pendingReview;
	
	/**
	 * Default constructor creates the sidebar based on who is logged in
	 */
	public SideBarPage() {
		boolean isAdmin = true;//TODO needs to be added
		
		
		
		Hyperlink search = new Hyperlink("Search Announcements", "SEARCH");
		Hyperlink submit = new Hyperlink("Submit Announcement", "SUBMIT");
		
		pendingReview = new Hyperlink("Pending Reviews (#)", "REVIEW");
		Hyperlink editTags = new Hyperlink("Edit Category Tags", "EDIT");
		
		VerticalPanel panel = new VerticalPanel();
		panel.addStyleName("leftVerticalPanel");
		
		panel.add(search);
		panel.add(submit);
		if (isAdmin) {
			panel.add(pendingReview);
			panel.add(editTags);

			service.getPendingAnnouncements(LoginManager.getInstance().getSessionID(), new AsyncCallback<List<Announcement>>() {
				
				@Override
				public void onSuccess(List<Announcement> result) {
					pendingReview.setText("Pending Reviews (" + result.size() + ")");
				}
				
				@Override
				public void onFailure(Throwable caught) {
					
				}
			});
		}
		initWidget(panel);
	}
	
	
	
}