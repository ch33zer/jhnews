package com.jhnews.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;

/**
 * This page is the header at the top each view
 * @author Group 8
 *
 */
public class HeaderPage extends Page implements LoginListener {
	
	private Hyperlink logIn;
	private Hyperlink register;
	private Anchor logOut;
	private Hyperlink preferences;
	private HorizontalPanel masterPanel;
	
	/**
	 * Default constructor creates the header depending on whether or not the user is logged in 
	 */
	public HeaderPage() {
		logIn = new Hyperlink("Log in", "LOGIN");
		register = new Hyperlink("Register", "REGISTER");
		logOut = new Anchor("Log out");
		
		logOut.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				History.newItem("HOME");
				LoginManager man = LoginManager.getInstance();
				man.logOut(man.getSessionID(), null);			
			}
		});
		preferences = new Hyperlink("Preferences", "PREFERENCES");
		masterPanel = new HorizontalPanel();
		masterPanel.addStyleName("horizontalPanel");
		
		LoginManager loginManager = LoginManager.getInstance();
		loginManager.addLoginListener(this);
		loginManager.isLoggedOn(loginManager.getSessionID(), new LoginManagerCallback<Boolean>() {	
			@Override
			public void onSuccess(Boolean result) {
				onLogin();
			}	
			@Override
			public void onFail() {
				onLogout();
			}
		});
		
	    initWidget(masterPanel);
	}

	/**
	 * Sets the display for a logged in user
	 */
	@Override
	public void onLogin() {
		masterPanel.clear();
		masterPanel.add(new Label("Hello, " + LoginManager.getInstance().getUsername()));
		masterPanel.add(new Label("|"));
		masterPanel.add(preferences);
		masterPanel.add(new Label("|"));
		masterPanel.add(logOut);
	}

	/**
	 * Sets the display for a logged out user
	 */
	@Override
	public void onLogout() {
		masterPanel.clear();
		masterPanel.add(register);
		masterPanel.add(new Label("|"));
		masterPanel.add(logIn);
	}
	
	public HorizontalPanel getMasterPanel()
	{
		return masterPanel;
	}
}