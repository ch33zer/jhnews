package com.jhnews.shared;

import java.io.Serializable;
import java.util.List;

import javax.persistence.OneToMany;

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
	private boolean tag1;
	private boolean tag2;
	private boolean tag3;
	private boolean tag4;
	private boolean tag5;
	private List<Announcement> announcements;
	private String username;
	private String hash;
	private static final long serialVersionUID = 1L;
	
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
	
	/**
	 * Gets the announcements submitted by the user
	 * @return the list of announcements submitted by the user
	 */
	@OneToMany(mappedBy="submitter")
	public List<Announcement> getAnnouncements() {
		return announcements;
	}
	
	/**
	 * Sets the user's announcements
	 * @param announcements the user's list of announcements 
	 */
	public void setAnnouncements(List<Announcement> announcements) {
		this.announcements = announcements;
	}
	
	/** Gets whether or not the user's first tag is set
	 * @return true if the tag is set, false otherwise
	 */
	public boolean isTag1() {
		return tag1;
	}
	
	/** Sets the user's first tag to be selected or not
	 * @param tag1 true if it's selected, false if it's not
	 */
	public void setTag1(boolean tag1) {
		this.tag1 = tag1;
	}
	
	/** Gets whether or not the user's second tag is set
	 * @return true if the tag is set, false otherwise
	 */
	public boolean isTag2() {
		return tag2;
	}
	
	/** Sets the user's second tag to be selected or not
	 * @param tag2 true if it's selected, false if it's not
	 */
	public void setTag2(boolean tag2) {
		this.tag2 = tag2;
	}
	
	/** Gets whether or not the user's third tag is set
	 * @return true if the tag is set, false otherwise
	 */
	public boolean isTag3() {
		return tag3;
	}
	
	/** Sets the user's third tag to be selected or not
	 * @param tag3 true if it's selected, false if it's not
	 */
	public void setTag3(boolean tag3) {
		this.tag3 = tag3;
	}
	
	/** Gets whether or not the user's fourth tag is set
	 * @return true if the tag is set, false otherwise
	 */
	public boolean isTag4() {
		return tag4;
	}
	
	/** Sets the user's fourth tag to be selected or not
	 * @param tag4 true if it's selected, false if it's not
	 */
	public void setTag4(boolean tag4) {
		this.tag4 = tag4;
	}
	
	/** Gets whether or not the user's fifth tag is set
	 * @return true if the tag is set, false otherwise
	 */
	public boolean isTag5() {
		return tag5;
	}
	
	/** Sets the user's fifth tag to be selected or not
	 * @param tag5 true if it's selected, false if it's not
	 */
	public void setTag5(boolean tag5) {
		this.tag5 = tag5;
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
}