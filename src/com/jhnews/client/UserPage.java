package com.jhnews.client;

import com.google.gwt.user.client.ui.Label;

/**
 * An abstract class which checks that only logged in users can view the Page
 * @author Group 8
 *
 */
public abstract class UserPage extends Page {
	
	/**
	 * Constructor checks if the viewer is logged in
	 */
	public UserPage() {
		LoginManager.getInstance().isLoggedOn(new LoginManagerCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				createRestrictedContent();
			}
				@Override
			public void onFail() {
				addWidget(new Label("You must be logged in to view this page"));
			}
		});
	}
	
	/**
	 * Extended and called if the viewer is an admin
	 */
	protected abstract void createRestrictedContent();

}
