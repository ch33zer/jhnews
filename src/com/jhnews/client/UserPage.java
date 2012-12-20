package com.jhnews.client;

import com.google.gwt.dom.client.Style.Clear;
import com.jhnews.shared.User;

/**
 * An abstract class which checks that only logged in users can view the Page
 * @author Group 8
 *
 */
public abstract class UserPage extends Page {
	
	private User user;
	
	/**
	 * Constructor checks if the viewer is logged in
	 */
	public UserPage() {
		setSuccess("Loading...");
		LoginManager.getInstance().isLoggedOn(new LoginManagerCallback<User>() {
			@Override
			public void onSuccess(User result) {
				user = result;
				removeLabel();
				createRestrictedContent();
			}
				@Override
			public void onFail() {
				setError("You must be logged in to view this page");
			}
		});
	}
	
	/**
	 * Extended and called if the viewer is logged in
	 */
	protected abstract void createRestrictedContent();

	protected User getUser() {
		return user;
	}
	
}
