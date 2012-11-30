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

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the announcements
	 */
	@OneToMany(mappedBy="submitter")
	public List<Announcement> getAnnouncements() {
		return announcements;
	}
	/**
	 * @param announcements the announcements to set
	 */
	public void setAnnouncements(List<Announcement> announcements) {
		this.announcements = announcements;
	}
	/**
	 * @return the tag1
	 */
	public boolean isTag1() {
		return tag1;
	}
	/**
	 * @param tag1 the tag1 to set
	 */
	public void setTag1(boolean tag1) {
		this.tag1 = tag1;
	}
	/**
	 * @return the tag2
	 */
	public boolean isTag2() {
		return tag2;
	}
	/**
	 * @param tag2 the tag2 to set
	 */
	public void setTag2(boolean tag2) {
		this.tag2 = tag2;
	}
	/**
	 * @return the tag3
	 */
	public boolean isTag3() {
		return tag3;
	}
	/**
	 * @param tag3 the tag3 to set
	 */
	public void setTag3(boolean tag3) {
		this.tag3 = tag3;
	}
	/**
	 * @return the tag4
	 */
	public boolean isTag4() {
		return tag4;
	}
	/**
	 * @param tag4 the tag4 to set
	 */
	public void setTag4(boolean tag4) {
		this.tag4 = tag4;
	}
	/**
	 * @return the tag5
	 */
	public boolean isTag5() {
		return tag5;
	}
	/**
	 * @param tag5 the tag5 to set
	 */
	public void setTag5(boolean tag5) {
		this.tag5 = tag5;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}
	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	
}
