package com.jhnews.shared;

import java.io.Serializable;

/** This class represents the database mapping from Users to Tags (via the intersection table UserTags) 
 * @author group 8
 *
 */
public class UserTags implements Serializable{
	private static final long serialVersionUID = 2524033010461921665L;
	private int usertagsID;
	private User user;
	private Tags tags;

	/** Gets the user from the UserTag
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/** Sets the user in the UserTag pairing
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/** Gets the tags in the UserTag pairing
	 * @return the tags
	 */
	public Tags getTags() {
		return tags;
	}
	
	
	/** Sets the tags in this UserTag pairing
	 * @param tags the tags to set 
	 */
	public void setTags(Tags tags) {
		this.tags = tags;
	}
	
	/** Gets the ID of this UserTag pairing
	 * @return the ID
	 */
	public int getUsertagsID() {
		return usertagsID;
	}
	
	
	/** Sets the ID of this UserTag
	 * @param usertagsID the ID to set
	 */
	public void setUsertagsID(int usertagsID) {
		this.usertagsID = usertagsID;
	}

	
	
}
