package com.jhnews.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.jhnews.shared.User;

/**
 * This is the user registration page for the application.
 * @author Group 8
 */
public class RegistrationPage extends Page {

	private HintTextBox firstNameBox;
	private HintTextBox lastNameBox;
	private HintTextBox emailBox;
	private HintPasswordTextBox passwordBox;
	private HintPasswordTextBox retypePasswordBox;

	/**
	 * Default constructor creates the registration page for the user
	 */
	public RegistrationPage() {
		// Variables
		setPageTitle("Register");
		isLeftAlign();
		firstNameBox = new HintTextBox("First name");
		firstNameBox.setMaxLength(100);
		lastNameBox = new HintTextBox("Last name");
		lastNameBox.setMaxLength(100);
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
					User user = new User();
					user.setFirstName(firstNameBox.getText());
					user.setLastName(lastNameBox.getText());
					user.setUsername(emailBox.getText());
					LoginManager.getInstance().register(user, passwordBox.getText(),
						new LoginManagerCallback<Void>() {		
						@Override
						public void onSuccess(Void result) {
								History.newItem("CONFIRMATION");
						}		
						@Override
						public void onFail() {
							setError("Failed to register");
						}
					});	
				}
			}
		});
		
		// Login panel setup
		addWidget(new Label("Name"));
		addWidget(firstNameBox);
		addWidget(lastNameBox);
		addWidget(new Label("Email"));
		addWidget(emailBox);
		addWidget(new Label("Password"));
		addWidget(passwordBox);
		addWidget(retypePasswordBox);
		addWidget(registerButton);
	}
	
	private boolean checkCredentials() {
		if (!passwordBox.getText().equals(retypePasswordBox.getText())) {
			setError("Failed to register - passwords do not match");
			return false;
		} else if (!(emailBox.getText().endsWith("@jhu.edu") || emailBox.getText().endsWith("@johnshopkins.edu"))) {
			setError("Failed to register - must use a JHU email");
			return false;
		} else if (!(emailBox.getText().length() > 5)) {
			setError("Failed to register - email must be longer than five characters");
			return false;
		}
		return true;
	}
}