package com.jhnews.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

/**
 * Page to allow the admin to change the outgoing email settings 
 * @author Group 8
 *
 */
public class EmailPage extends AdminPage {
	
	private HorizontalPanel timePanel;
	private ListBox hourListBox;
	private ListBox minuteListBox;
	private ListBox meridianListBox;
	private Button saveButton;
	
	public EmailPage() {
		setPageTitle("Email Settings");
		isLeftAlign();
	}

	@Override
	protected void createRestrictedContent() {	
		createListBoxes();
		
		saveButton = new Button("Save", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
		
		addWidget(new Label("Set email send time"));
		addWidget(timePanel);
		addWidget(saveButton);
	}
	
	private void createListBoxes() {
		timePanel = new HorizontalPanel();
		hourListBox = new ListBox();
		for (int i = 1; i < 13; i++) {
			hourListBox.addItem("" + i);
		}
		hourListBox.setVisibleItemCount(1);
		minuteListBox = new ListBox();
		minuteListBox.addItem("00");
		minuteListBox.addItem("15");
		minuteListBox.addItem("30");
		minuteListBox.addItem("45");
		minuteListBox.setVisibleItemCount(1);
		meridianListBox = new ListBox();
		meridianListBox.addItem("AM");
		meridianListBox.addItem("PM");
		meridianListBox.setVisibleItemCount(1);
		timePanel.add(hourListBox);
		timePanel.add(new Label(":"));
		timePanel.add(minuteListBox);
		timePanel.add(meridianListBox);
	}

}
