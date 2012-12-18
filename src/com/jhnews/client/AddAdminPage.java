package com.jhnews.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class AddAdminPage extends AdminPage {

	private HintTextBox adminEmailBox;
	private Button addButton;	
	
	public AddAdminPage() {
		setPageTitle("Add Admin");
		isLeftAlign();
	}
	
	@Override
	protected void createRestrictedContent() {
		HorizontalPanel addPanel = new HorizontalPanel();
		adminEmailBox = new HintTextBox("User email");
		addButton = new Button("Add", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		addPanel.add(adminEmailBox);
		addPanel.add(addButton);
		
		addWidget(new Label("Give user administrative privileges"));
		addWidget(addPanel);
	}

}
