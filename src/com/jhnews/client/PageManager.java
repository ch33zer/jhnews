package com.jhnews.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.jhnews.shared.Announcement;

/**
 * This is a singleton class which manages changing of pages and hyperlink clicks
 * @author Group 8
 *
 */
public class PageManager implements ValueChangeHandler<String>  {
	
	private static PageManager instance;

	/**
	 * Private constructor for singleton pattern
	 */
	private PageManager() {
		History.addValueChangeHandler(this);
	}
	
	/** Sets up the start page and initializes the history (if there isn't a history). This does not actually load the page. That is done in {@link Jhnews#onModuleLoad()} when the app starts.
	 * 
	 */
	public void prepareStartPage() {
		RootPanel.get("header").add(new HeaderPage());
		RootPanel.get("date").add(new DatePage());
		//RootPanel.get("footer").add(new HomePage());
		RootPanel.get("sidebar").add(new SideBarPage());
	    if (History.getToken().length() == 0) {
	      History.newItem("HOME");
	    }
	}

	/**
	 * Gives the singleton instance if necessary
	 * @return The singleton instance
	 */
	public static PageManager getInstance() {
		if (instance == null) {
			instance = new PageManager();
		}
		return instance;
	}
	
	/**
	 * Sets the body of the website to a certain Page
	 * @param body The identifier of the Page
	 */
	public void setBody(PagesEnum body) {
		Page page;
		switch (body) {
		case HOME:
			page = new HomePage();
			break;
		case LOGIN:
			page = new LoginPage();
			break;
		case REGISTER:
			page = new RegistrationPage();
			break;
		case SUBMIT:
			page = new SubmissionPage();
			break;
		case SEARCH:
			page = new SearchPage();
			break;
		case PREFERENCES:
			page = new PreferencesPage();
			break;
		case PENDING:
			page = new PendingAnnouncementListPage();
			break;
		default:
			throw new RuntimeException("Unknown page in BodyEnum: " + body.toString());
		}

		RootPanel.get("body").clear();
		RootPanel.get("body").add(page);
	}

	/**
	 * Generates the Announcement detail Page and sets the body of the website to it
	 * @param announcement The Announcement details for the page
	 */
	public void generateAnnouncementPage(Announcement announcement) {
		RootPanel.get("body").clear();
		RootPanel.get("body").add(new AnnouncementPanel(announcement));
	}
	
	/**
	 * Generates the Announcement detail Page and sets the body of the website to it for a pending announcement
	 * @param announcement The Announcement details for the page
	 */
	public void generatePendingAnnouncementPage(Announcement announcement) {
		RootPanel.get("body").clear();
		RootPanel.get("body").add(new PendingAnnouncementPage(announcement));
	}

	/**
	 * Processes the hyperlink clicks
	 * @param event The event of the hyperlink click
	 */
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		PagesEnum page = PagesEnum.HOME;
		try {
			page = PagesEnum.valueOf(event.getValue());
		} catch(IllegalArgumentException e) {
			return;
		}
		setBody(page);
	}
}
