package com.jhnews.client;

import com.google.gwt.user.client.ui.Label;

public abstract class UserPage extends Page {
	
	UserPage() {
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
	
	protected abstract void createRestrictedContent();

}
