package com.jhnews.client;

import java.util.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

/**
 * This page shows the current date
 * @author Group 8
 *
 */
public class DatePanel extends Composite {
	
	private static final DateTimeFormat FORMATTER_NO_TIME = DateTimeFormat.getFormat("EEEE, MMMM d, yyyy");
	
	/**
	 * Default constructor which builds the date
	 */
	DatePanel() {
		Date date = new Date();
		initWidget(new Label(FORMATTER_NO_TIME.format(date)));
	}
	
}
