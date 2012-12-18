package com.jhnews.shared;

import java.io.Serializable;
import java.util.List;

import javax.persistence.OneToMany;

import com.jhnews.server.UserTagsHibernate;

/**
 * User is the class that represents a user of our application and holds the user's preferences and settings.
 * @author Group 8
 */
public class User implements Serializable{


	private int ID;
	private String firstName;
	private String lastName;
	private String email;
	private boolean isAdmin;
	private String username;
	private String hash;
	private static final long serialVersionUID = 1L;
	private List<UserTags> tags;
	
	/**
	 * The default constructor of the User class.
	 */
	public User() {
	}

	/**
	 * Gets the of ID the user
	 * @return the ID of the user
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Sets the ID of the user
	 * @param iD the ID of the user
	 */
	public void setID(int iD) {
		ID = iD;
	}
	
	/**
	 * Gets the first name of the user
	 * @return the first name of the user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the user
	 * @param firstName the first name of the user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name of the user
	 * @return the last name of the user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the user
	 * @param lastName the last name of the user
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the email address of the user
	 * @return the email address of the user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the user
	 * @param email the email of the user
	 */
	public void setEmail(String email) {
		this.email = email;
	}
		
	/** Gets the user's username
	 * @return the user's username
	 */
	public String getUsername() {
		return username;
	}
	
	/** Sets the user's username
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/** Gets the hash of the user's password
	 * @return the hash of the user's password
	 */
	public String getHash() {
		return hash;
	}
	
	/** Sets the hash of the user's password
	 * @param hash the hash of the user's password
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * Checks if the user is an admin
	 * @return true if the user is an admin, false otherwise
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * Sets whether or not the user is an admin
	 * @param isAdmin true if the user is an admin, false if not
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the tags
	 */
	public List<UserTags> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<UserTags> tags) {
		this.tags = tags;
	}
}