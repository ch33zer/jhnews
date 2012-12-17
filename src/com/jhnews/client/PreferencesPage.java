package com.jhnews.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This is the user preferences and settings page for the application.
 * @author Group 8
 */
public class PreferencesPage extends Page {

	private CheckBox notificationCheckBox;
	private TagsPanel tagsPanel;
	
	/**
	 * Default constructor creates the preferences page for the user.
	 */
	public PreferencesPage() {
		setPageTitle("Preferences");
		isLeftAlign();
		notificationCheckBox = new CheckBox("Enable email notifications");
		tagsPanel = new TagsPanel();
		Button saveButton = new Button("Save", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});		
		
		addWidget(notificationCheckBox);
		addWidget(new Label("Interest tags:"));
		addWidget(tagsPanel);
		addWidget(saveButton);
	}
}
