package com.jhnews.client;

/**
 * This listens for log ins or log outs
 * @author Group 8
 *
 */
public interface LoginListener {
	/**
	 * This is called when the user logs in
	 */
	public void onLogin();
	
	/**
	 * This is called when the user logs out
	 */
	public void onLogout();
}
