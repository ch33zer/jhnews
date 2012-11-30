package com.jhnews.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.jhnews.server.UserHibernate;

/**
 * Announcement is the class that represents an announcement submission and holds all its relevant information.
 * @author Group 8
 */

public class Announcement implements Serializable {
	
	private int ID;
	private User submitter;
	private String title;
	private String location;
	private String briefDescription;
	private String longDescription;
	private Date eventDate;
	private boolean hasEventTime;
	private boolean tag1;
	private boolean tag2;
	private boolean tag3;
	private boolean tag4;
	private boolean tag5;
	private boolean toFreshman;
	private boolean toSophomore;
	private boolean toJunior;
	private boolean toSenior;
	private boolean toGraduate;
	private boolean toFaculty;

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor of the Announcement class.
	 */
	public Announcement(){
		this.submitter = null;
		this.title = null;
		this.location = null;
		this.briefDescription = null;
		this.longDescription = null;
		this.eventDate = null;
		this.hasEventTime = false;
	}


	/**
	 * Gets the ID number of the announcement
	 * @return the ID number of the announcement
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Sets a new ID number for this announcement
	 * @param ID the new ID for the announcement
	 */
	public void setID(int ID) {
		this.ID = ID;
	}


	/**
	 * Gets the User who submitted this announcement.
	 * @return the User who sumitted the announcement.
	 */
	public User getSubmitter() {
		return submitter;
	}

	/**
	 * Sets the User who submitted the announcement.
	 * @param submitter the User who submitted this announcement.
	 */
	public void setSubmitter(User submitter) {
		this.submitter = submitter;
	}
	
	/**
	 * Gets the title of this announcement
	 * @return the title of the announcement
	 */
	
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of this announcement
	 * @param title the new title for the announcement
	 */	
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the location of this announcement
	 * @return the location of this announcement.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the announcement of this announcement
	 * @param location the new location to set this announcement to
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets a brief description of the announcement
	 * @return the brief description of the announcement
	 */
	public String getBriefDescription() {
		return briefDescription;
	}

	/**
	 * Sets a new brief description for this announcement
	 * @param briefDescription the new brief description for this announcement.
	 */
	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}

	/**
	 * Gets the long description of this announcement
	 * @return the long description of this announcement.
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * Set a new long description for this announcement
	 * @param longDescription the new long description for this announcement
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	
	/**
	 * Gets the date of this announcement
	 * @return the date of the event
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * Sets a new date for this announcement
	 * @param eventDate the new date for this announcement
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	/**
	 * Set whether or not the event in this announcement has a scheduled time.
	 * @param hasEventTime whether or not this event has a scheduled time
	 */
	public void setHasEventTime(boolean hasEventTime) {
		this.hasEventTime = hasEventTime;
	}
	
	/**
	 * Tells whether or not the event in this announcement has a scheduled time 
	 * @return true if there's a scheduled time, false if not
	 */
	public boolean hasEventTime() {
		return hasEventTime;
	}


	public boolean isTag1() {
		return tag1;
	}


	public void setTag1(boolean tag1) {
		this.tag1 = tag1;
	}


	public boolean isTag2() {
		return tag2;
	}


	public void setTag2(boolean tag2) {
		this.tag2 = tag2;
	}


	public boolean isTag3() {
		return tag3;
	}


	public void setTag3(boolean tag3) {
		this.tag3 = tag3;
	}


	public boolean isTag4() {
		return tag4;
	}


	public void setTag4(boolean tag4) {
		this.tag4 = tag4;
	}


	public boolean isTag5() {
		return tag5;
	}


	public void setTag5(boolean tag5) {
		this.tag5 = tag5;
	}


	public boolean isToFreshman() {
		return toFreshman;
	}


	public void setToFreshman(boolean toFreshman) {
		this.toFreshman = toFreshman;
	}


	public boolean isToSophomore() {
		return toSophomore;
	}


	public void setToSophomore(boolean toSophomore) {
		this.toSophomore = toSophomore;
	}


	public boolean isToJunior() {
		return toJunior;
	}


	public void setToJunior(boolean toJunior) {
		this.toJunior = toJunior;
	}


	public boolean isToSenior() {
		return toSenior;
	}


	public void setToSenior(boolean toSenior) {
		this.toSenior = toSenior;
	}


	public boolean isToGraduate() {
		return toGraduate;
	}


	public void setToGraduate(boolean toGraduate) {
		this.toGraduate = toGraduate;
	}


	public boolean isToFaculty() {
		return toFaculty;
	}


	public void setToFaculty(boolean toFaculty) {
		this.toFaculty = toFaculty;
	}


	public boolean isHasEventTime() {
		return hasEventTime;
	}
}
