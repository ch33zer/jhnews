package com.jhnews.client;

import com.google.gwt.user.client.ui.Label;

public abstract class AdminPage extends Page {
	
	AdminPage() {
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
	
	protected abstract void createRestrictedContent();

}
