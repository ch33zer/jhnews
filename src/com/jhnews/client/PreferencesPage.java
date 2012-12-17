package com.jhnews.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;

/**
 * This is the user preferences and settings page for the application.
 * @author Group 8
 */
public class PreferencesPage extends UserPage {

	private CheckBox notificationCheckBox;
	private CheckBox homeTagsCheckBox;
	private TagsPanel tagsPanel;
	
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
		Button saveButton = new Button("Save", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});		
		
		addWidget(homeTagsCheckBox);
		addWidget(notificationCheckBox);
		addWidget(new Label("Interest tags:"));
		addWidget(tagsPanel);
		addWidget(saveButton);
	}
}
