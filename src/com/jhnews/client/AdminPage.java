package com.jhnews.client;

import com.google.gwt.user.client.ui.Label;

/**
 * An abstract class which checks that only admins can view the Page
 * @author Group 8
 *
 */
public abstract class AdminPage extends Page {
	
	/**
	 * Constructor checks if the viewer is an admin
	 */
	public AdminPage() {
		LoginManager.getInstance().isAdmin(new LoginManagerCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				createRestrictedContent();
			}
				@Override
			public void onFail() {
				addWidget(new Label("You must be an admin to view this page"));
			}
		});
	}
	
	/**
	 * Extended and called if the viewer is an admin
	 */
	protected abstract void createRestrictedContent();

}
