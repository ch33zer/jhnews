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
public class SideBarPage extends Page implements LoginListener {
	
	private RestrictedServiceAsync service = GWT.create(RestrictedService.class);
	
	private VerticalPanel masterPanel;
	private Hyperlink pendingReview;
	private Hyperlink editTags;
	
	private LoginManager loginManager = LoginManager.getInstance();
	
	
	/**
	 * Default constructor creates the sidebar based on who is logged in
	 */
	public SideBarPage() {
		Hyperlink search = new Hyperlink("Search Announcements", "SEARCH");
		Hyperlink submit = new Hyperlink("Submit Announcement", "SUBMIT");
		
		pendingReview = new Hyperlink("Pending Reviews (#)", "PENDING");
		editTags = new Hyperlink("Edit Category Tags", "EDIT");
		
		masterPanel = new VerticalPanel();
		masterPanel.addStyleName("leftVerticalPanel");
		
		masterPanel.add(search);
		masterPanel.add(submit);		
		
		loginManager.addLoginListener(this);
		
		initWidget(masterPanel);
	}
	
	public void userIsAdmin() {
		masterPanel.add(pendingReview);
		masterPanel.add(editTags);

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
	
	public void userIsNotAdmin() {
		masterPanel.remove(editTags);
		masterPanel.remove(pendingReview);
	}

	@Override
	public void onLogin() {
		loginManager.isAdmin(new LoginManagerCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					userIsAdmin();
				}
				
			}
			@Override
			public void onFail() {
				
			}		
		});		
	}

	@Override
	public void onLogout() {
		userIsNotAdmin();		
	}
	
}