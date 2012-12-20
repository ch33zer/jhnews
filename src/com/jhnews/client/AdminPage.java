package com.jhnews.client;

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
		setSuccess("Loading...");
		LoginManager.getInstance().isAdmin(new LoginManagerCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				removeLabel();
				createRestrictedContent();
			}
				@Override
			public void onFail() {
				setError("You must be an admin to view this page");
			}
		});
	}
	
	/**
	 * Extended and called if the viewer is an admin
	 */
	protected abstract void createRestrictedContent();

}
