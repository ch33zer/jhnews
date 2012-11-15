package com.jhnews.client;

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
	
	/**
	 * Default constructor creates the preferences page for the user.
	 */
	public PreferencesPage() {
		VerticalPanel masterPanel = new VerticalPanel();
		masterPanel.addStyleName("leftVerticalPanel");
		Label pageTitleLabel = new Label("Preferences");
		pageTitleLabel.addStyleDependentName("title");
		pageTitleLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		notificationCheckBox = new CheckBox("Enable user notifications");
		
		masterPanel.add(pageTitleLabel);
		masterPanel.add(notificationCheckBox);
		initWidget(masterPanel);
	}
}
