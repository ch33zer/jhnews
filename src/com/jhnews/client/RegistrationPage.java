package com.jhnews.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.jhnews.shared.Session;

/**
 * This is the user registration page for the application.
 * @author Group 8
 */
public class RegistrationPage extends Page {

	private Label errorLabel;
	private Label successLabel;
	private HintTextBox emailBox;
	private HintPasswordTextBox passwordBox;
	private HintPasswordTextBox retypePasswordBox;

	/**
	 * Default constructor creates the registration page for the user
	 */
	public RegistrationPage() {
		// Variables
		setPageTitle("Register");
		errorLabel = new Label("Failed to register. The username may already exist.");
		errorLabel.setStyleDependentName("error", true);
		errorLabel.setVisible(false);
		successLabel = new Label("Registered. Redirecting to login page...");
		successLabel.setStyleDependentName("success", true);
		successLabel.setVisible(false);
		emailBox = new HintTextBox("Email");
		emailBox.setMaxLength(100);
		passwordBox = new HintPasswordTextBox("Password");
		passwordBox.setMaxLength(100);
		retypePasswordBox = new HintPasswordTextBox("Retype password");
		retypePasswordBox.setMaxLength(100);
		
		Button registerButton = new Button("Register", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (checkCredentials()) {
					LoginManager.getInstance().register(emailBox.getText(),
						passwordBox.getText(), new LoginManagerCallback<Session>() {		
						@Override
						public void onSuccess(Session result) {
								errorLabel.setVisible(false);
								successLabel.setVisible(true);
								History.newItem("LOGIN");
							}		
						@Override
						public void onFail() {
							errorLabel.setVisible(true);
						}
					});	
				}
			}
		});
		
		// Login panel setup
		addWidget(emailBox);
		addWidget(passwordBox);
		addWidget(retypePasswordBox);
		addWidget(registerButton);
		addWidget(errorLabel);
		addWidget(successLabel);
	}
	
	private boolean checkCredentials() {
		if (!passwordBox.getText().equals(retypePasswordBox.getText())) {
			errorLabel.setVisible(true);
			errorLabel.setText("Failed to register. The passwords do not match.");
			return false;
		} else if (!(emailBox.getText().endsWith("@jhu.edu") || emailBox.getText().endsWith("@johnshopkins.edu"))) {
			errorLabel.setVisible(true);
			errorLabel.setText("Failed to register. Must use a JHU email.");
			return false;
		} else if (!(emailBox.getText().length() > 5)) {
			errorLabel.setVisible(true);
			errorLabel.setText("Failed to register. Email must be longer than five characters.");
			return false;
		}
		return true;
	}
}