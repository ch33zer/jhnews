package com.jhnews.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Jhnews implements EntryPoint {
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		PageManager.getInstance().prepareStartPage();
	    History.fireCurrentHistoryState();
	}
	
}
