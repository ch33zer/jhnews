package com.jhnews.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.jhnews.shared.User;

/**
 * This is the user preferences and settings page for the application.
 * @author Group 8
 */
public class PreferencesPage extends UserPage {

	private CheckBox notificationCheckBox;
	private CheckBox homeTagsCheckBox;
	private TagsPanel tagsPanel;
	private User user;
	
	/**
	 * Default constructor creates the preferences page for the user.
	 */
	public PreferencesPage() {
		setPageTitle("Preferences");
		isLeftAlign();
	}

	@Override
	protected void createRestrictedContent() {
		homeTagsCheckBox = new CheckBox("Limit homepage to chosen tags");
		notificationCheckBox = new CheckBox("Enable email notifications");
		tagsPanel = new TagsPanel();
		LoginManager.getInstance().getUser(new LoginManagerCallback<User>() {
			
			@Override
			public void onSuccess(User result) {
				user = result;
				tagsPanel.checkUserTags(result);
				homeTagsCheckBox.setValue(result.isCustomHomepage());
				notificationCheckBox.setValue(result.isEmailEnable());
			}
			
			@Override
			public void onFail() {
				//Do nothing
			}
		});
		Button saveButton = new Button("Save", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tagsPanel.setTagsInUser(user);
				user.setEmailEnable(notificationCheckBox.getValue());
				user.setCustomHomepage(homeTagsCheckBox.getValue());
				LoginManager.getInstance().saveUser(user, new LoginManagerCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						setSuccess("Saved!");
					}
					
					@Override
					public void onFail() {
						setError("Failed to save");
					}
				});
			}
		});		
		
		addWidget(homeTagsCheckBox);
		addWidget(notificationCheckBox);
		addWidget(new Label("Interest tags:"));
		addWidget(tagsPanel);
		addWidget(saveButton);
	}
}
