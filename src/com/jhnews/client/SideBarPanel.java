package com.jhnews.client;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jhnews.shared.Announcement;

/**
 * This page is the side of each view for navigation
 * @author Group 8
 *
 */
public class SideBarPanel extends Composite implements LoginListener {
	
	private RestrictedServiceAsync restrictedService = GWT.create(RestrictedService.class);
	
	private VerticalPanel masterPanel;
	private Label adminLabel;
	private Hyperlink addAdmin;
	private Hyperlink editTags;
	private Hyperlink emailSettings;
	private Hyperlink pendingReview;
	
	private LoginManager loginManager = LoginManager.getInstance();
	
	/**
	 * Default constructor creates the sidebar based on who is logged in
	 */
	public SideBarPanel() {
		Hyperlink search = new Hyperlink("Search Announcements", "SEARCH");
		Hyperlink submit = new Hyperlink("Submit Announcement", "SUBMIT");
		adminLabel = new Label("Admin Options");
		addAdmin = new Hyperlink("Add Admin", "ADMIN");
		editTags = new Hyperlink("Edit Category Tags", "EDIT");
		emailSettings = new Hyperlink("Email Settings", "EMAIL");
		pendingReview = new Hyperlink("Pending Reviews", "PENDING");
		
		
		masterPanel = new VerticalPanel();
		masterPanel.addStyleName("leftVerticalPanel");
		
		masterPanel.add(search);
		masterPanel.add(submit);		
		
		loginManager.addLoginListener(this);
		
		onLogin();
		
		initWidget(masterPanel);
	}
	
	/**
	 * Sets sidebar for an admin
	 */
	private void userIsAdmin() {
		masterPanel.add(adminLabel);
		masterPanel.add(addAdmin);
		masterPanel.add(editTags);
		masterPanel.add(emailSettings);
		masterPanel.add(pendingReview);
		updatePendingReview();
	}
	
	/**
	 * Updates the sidebar so that the number of pending reviews is accurate
	 */
	public void updatePendingReview() {
		restrictedService.getPendingAnnouncements(LoginManager.getInstance().getSessionID(), new AsyncCallback<List<Announcement>>() {
			@Override
			public void onSuccess(List<Announcement> result) {
				pendingReview.setText("Pending Reviews (" + result.size() + ")");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
	}
	
	/**
	 * Sets sidebar for when user is not an admin
	 */
	public void userIsNotAdmin() {
		masterPanel.remove(adminLabel);
		masterPanel.remove(addAdmin);
		masterPanel.remove(editTags);
		masterPanel.remove(emailSettings);
		masterPanel.remove(pendingReview);
	}

	/**
	 * Checks if the user is an admin and reacts appropriately
	 */
	@Override
	public void onLogin() {
		Timer time = new Timer();
		time.schedule(new TimerTask() {
			
			@Override
			public void run() {
				//How are you?
			}
		}, 1000);
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

	/**
	 * Ensures that admin settings are disabled when logged off
	 */
	@Override
	public void onLogout() {
		userIsNotAdmin();		
	}
	
}