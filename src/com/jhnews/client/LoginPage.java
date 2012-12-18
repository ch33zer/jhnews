package com.jhnews.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.TextBox;
import com.jhnews.shared.Session;

/**
 * This page is the user login page for the application. 
 * @author Group 8
 *
 */
public class LoginPage extends Page {

	private TextBox emailBox;
	private HintPasswordTextBox passwordBox;

	/**
	 * This is the constructor for the login page class.
	 */
	public LoginPage() {
		setPageTitle("Log In");
		emailBox = new HintTextBox("Email");
		emailBox.setMaxLength(100);
		passwordBox = new HintPasswordTextBox("Password");
		passwordBox.setMaxLength(100);
		Hyperlink registerLink = new Hyperlink("New? Register here!", "REGISTER");
		registerLink.addStyleDependentName("small");

		Button loginButton = new Button("Log in", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
			LoginManager.getInstance().logIn(emailBox.getText(),
				passwordBox.getText(), new LoginManagerCallback<Session>() {
					@Override
					public void onSuccess(Session result) {
						setSuccess("Logged in. Redirecting to home page...");
						History.newItem("HOME");
					}
					@Override
					public void onFail() {
						setError("Failed to log in. Invalid username or password");
					}
				});
			}
		});

		// Login panel setup
		addWidget(emailBox);
		addWidget(passwordBox);
		addWidget(loginButton);
		addWidget(registerLink);
	}
}