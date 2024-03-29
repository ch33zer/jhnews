package com.jhnews.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
/**
 * This page allows existing admins to give admin priviledges to another user.
 * @author Group 8
 *
 */
public class AddAdminPage extends AdminPage {

	private HintTextBox adminEmailBox;
	private Button addButton;	
	private RestrictedServiceAsync service = GWT.create(RestrictedService.class);

	/**
	 * This is the constructor for the add admin page class.
	 */
	public AddAdminPage() {
		if (service != null) {
			service = GWT.create(RestrictedService.class);
		}
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
				service.addAdmin(LoginManager.getInstance().getSessionID(), adminEmailBox.getText().trim(), new AsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean result) {
						if (result) {
							setSuccess("Admin added");
						}
						else {
							setError("Failed to add admin");
						}
					}
					
					@Override
					public void onFailure(Throwable caught) {
						setError("Failed to add admin");
					}
				});
			}
		});
		addPanel.add(adminEmailBox);
		addPanel.add(addButton);
		
		addWidget(new Label("Give user administrative privileges"));
		addWidget(addPanel);
	}

}
