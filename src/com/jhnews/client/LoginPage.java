package com.jhnews.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jhnews.shared.Session;

/**
 * This page is the user login page for the application. 
 * @author Group 8
 *
 */
public class LoginPage extends Page {

	private Label errorLabel;
	private Label successLabel;
	private TextBox emailBox;
	private HintPasswordTextBox passwordBox;

	/**
	 * This is the constructor for the login page class.
	 */
	public LoginPage() {
		VerticalPanel masterPanel = new VerticalPanel();
		Label pageTitleLabel = new Label("Log In");
		pageTitleLabel.addStyleDependentName("title");
		pageTitleLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		errorLabel = new Label("Failed to log in. Invalid username or password.");
		errorLabel.setStyleDependentName("error", true);
		errorLabel.setVisible(false);
		successLabel = new Label("Logged in. Redirecting to home page...");
		successLabel.setStyleDependentName("success", true);
		successLabel.setVisible(false);
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
						errorLabel.setVisible(false);
						successLabel.setVisible(true);
						History.newItem("HOME");
					}
					@Override
					public void onFail() {
						errorLabel.setVisible(true);
					}
				});
			}
		});

		// Login panel setup
		masterPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		masterPanel.add(pageTitleLabel);
		masterPanel.add(emailBox);
		masterPanel.add(passwordBox);
		masterPanel.add(loginButton);
		masterPanel.add(registerLink);
		masterPanel.add(errorLabel);
		masterPanel.add(successLabel);
		initWidget(masterPanel);
	}
}