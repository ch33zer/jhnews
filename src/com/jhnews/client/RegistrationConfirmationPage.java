package com.jhnews.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

public class RegistrationConfirmationPage extends Page {
	
	private HintTextBox emailBox;
	private HintTextBox codeBox;
	private Button confirmButton;
	
	private UnrestrictedServiceAsync unrestrictedService = GWT.create(UnrestrictedService.class);
	
	public RegistrationConfirmationPage() {
		setPageTitle("Registration Confirmation");
		isLeftAlign();
		
		emailBox = new HintTextBox("Email");
		codeBox = new HintTextBox("Registration code");
		confirmButton = new Button("Confirm", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				unrestrictedService.confirmRegistration(emailBox.getText(), codeBox.getText(), new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						setError("Failed to confirm");
					}

					@Override
					public void onSuccess(Void result) {
						History.newItem("LOGIN");
					}
				});
			}
		});
			
		addWidget(new Label("Please enter the code received in your email"));
		addWidget(emailBox);
		addWidget(codeBox);
		addWidget(confirmButton);
	}

}
