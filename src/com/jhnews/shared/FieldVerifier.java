package com.jhnews.shared;

/** Features static methods for verifying the varios methods
 * @author Group 8
 *
 */
public class FieldVerifier {
	/**Determines if a username/password combo is valid (client side validation)
	 * @param username Desired username
	 * @param password Desired password
	 * @return If the username and password are valid
	 */
	public static boolean isValidUserNameAndPassword(String username, String password) {
		if (username != null && password != null) {
			if (username.contains("@") && username.length() > 2 && password.length() > 5 && !password.equals(username)) {
				return true;
			}
		}
		return false;
	}
	/**Determines if an announcement is valid
	 * @param announcement The announcement to check
	 * @return If the announcement is valid
	 */
	public static boolean isValidAnnouncement(Announcement announcement) {
		return true;
	}
}
