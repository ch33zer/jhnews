package com.jhnews.client;

import java.util.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.ui.Label;

/**
 * This page shows the current date
 * @author Group 8
 *
 */
public class DatePage extends Page {
	
	/**
	 * Default constructor which builds the date
	 */
	DatePage() {
		Date date = new Date();
		DateTimeFormat formatter = DateTimeFormat.getFormat("EEEE, MMMM d, yyyy");
		initWidget(new Label(formatter.format(date)));
	}
	
}
