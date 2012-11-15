package com.jhnews.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Grid;
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
		notificationCheckBox = new CheckBox("Enable email notifications");
		Grid tagPanel = new Grid(1, 3);
		tagPanel.addStyleName("detailPanel");
		tagPanel.setWidget(0, 0, new CheckBox("Free"));
		tagPanel.getCellFormatter().setWidth(0, 0, "100px");
		tagPanel.setWidget(0, 1, new CheckBox("Food"));
		tagPanel.getCellFormatter().setWidth(0, 1, "100px");
		tagPanel.setWidget(0, 2, new CheckBox("Greek life"));
		tagPanel.getCellFormatter().setWidth(0, 2, "100px");
		Button saveButton = new Button("Save", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});		
		
		masterPanel.add(pageTitleLabel);
		masterPanel.add(notificationCheckBox);
		masterPanel.add(new Label("Interest tags:"));
		masterPanel.add(tagPanel);
		masterPanel.add(saveButton);
		initWidget(masterPanel);
	}
}
