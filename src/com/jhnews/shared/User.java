package com.jhnews.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User is the class that represents a user of our application and holds the user's preferences and settings.
 * @author Group 8
 */
public class User implements Serializable {

	private int ID;
	private String firstName;
	private String lastName;
	private String email;
	
	List<String> tags = new ArrayList<String>();
	private static final long serialVersionUID = 1L;
	
	/**
	 * The default constructor of the User class.
	 */
	public User() {
		//change to pull preferred tags from the database once it's implemented
		this.tags.add("Sorority");
		this.tags.add("Food");
		this.tags.add("Sandy");
	}
	
	/**
	 * Gets the tags preferred by this user for announcement submission
	 * @return the tags preferred by this user
	 */
	public List<String> getTags()
	{
		return tags;
	}
	
	/**
	 * Sets a new set of tags preferred by this user for announcement submission
	 * 
	 * @param inTags the new set of tags for this user
	 */
	
	public void setTags(ArrayList<String> inTags)
	{
		this.tags = inTags;
	}
}
