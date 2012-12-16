package com.jhnews.shared;

import java.io.Serializable;
import java.util.Date;

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
	private boolean approved;
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

	@Override
	public String toString() {
		return "Announcement [ID=" + ID + ", submitter=" + submitter
				+ ", title=" + title + ", location=" + location
				+ ", briefDescription=" + briefDescription
				+ ", longDescription=" + longDescription + ", eventDate="
				+ eventDate + ", approved=" + approved + ", hasEventTime="
				+ hasEventTime + ", tag1=" + tag1 + ", tag2=" + tag2
				+ ", tag3=" + tag3 + ", tag4=" + tag4 + ", tag5=" + tag5
				+ ", toFreshman=" + toFreshman + ", toSophomore=" + toSophomore
				+ ", toJunior=" + toJunior + ", toSenior=" + toSenior
				+ ", toGraduate=" + toGraduate + ", toFaculty=" + toFaculty
				+ "]";
	}


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

/**
 * Checks if the first tag is set
 * @return true if it's set, false otherwise
 */
	public boolean isTag1() {
		return tag1;
	}

	/**
	 * Sets the first tag to true or false
	 * @param tag1 the Boolean value to set the tag to
	 */
	public void setTag1(boolean tag1) {
		this.tag1 = tag1;
	}

	/**
	 * Checks if the second tag is set
	 * @return true if it's set, false otherwise
	 */
	public boolean isTag2() {
		return tag2;
	}

	/**
	 * Sets the second tag to true or false
	 * @param tag2 the Boolean value to set the tag to
	 */
	public void setTag2(boolean tag2) {
		this.tag2 = tag2;
	}

	/**
	 * Checks if the third tag is set
	 * @return true if it's set, false otherwise
	 */
	public boolean isTag3() {
		return tag3;
	}

	/**
	 * Sets the third tag to true or false
	 * @param tag3 the Boolean value to set the tag to
	 */
	public void setTag3(boolean tag3) {
		this.tag3 = tag3;
	}

	/**
	 * Checks if the fourth tag is set
	 * @return true if it's set, false otherwise
	 */
	public boolean isTag4() {
		return tag4;
	}

	/**
	 * Sets the fourth tag to true or false
	 * @param tag4 the Boolean value to set the tag to
	 */
	public void setTag4(boolean tag4) {
		this.tag4 = tag4;
	}

	/**
	 * Checks if the fifth tag is set
	 * @return true if it's set, false otherwise
	 */
	public boolean isTag5() {
		return tag5;
	}

	/**
	 * Sets the fifth tag to true or false
	 * @param tag5 the Boolean value to set the tag to
	 */
	public void setTag5(boolean tag5) {
		this.tag5 = tag5;
	}

	/**
	 * Checks if the announcement is set to be sent to freshmen
	 * @return true if it is, false otherwise
	 */
	public boolean isToFreshman() {
		return toFreshman;
	}

	/**
	 * Sets the announcement to send to freshmen if true and not if false
	 * @param toFreshman true if yes, false if not
	 */
	public void setToFreshman(boolean toFreshman) {
		this.toFreshman = toFreshman;
	}

	/**
	 * Checks if the announcement is set to be sent to sophomores
	 * @return true if it is, false otherwise
	 */
	public boolean isToSophomore() {
		return toSophomore;
	}

	/**
	 * Sets the announcement to send to sophomores if true and not if false
	 * @param toSophomore true if yes, false if not
	 */
	public void setToSophomore(boolean toSophomore) {
		this.toSophomore = toSophomore;
	}

	/**
	 * Checks if the announcement is set to be sent to juniors
	 * @return true if it is, false otherwise
	 */
	public boolean isToJunior() {
		return toJunior;
	}

	/**
	 * Sets the announcement to send to juniors if true and not if false
	 * @param toJunior true if yes, false if not
	 */
	public void setToJunior(boolean toJunior) {
		this.toJunior = toJunior;
	}

	/**
	 * Checks if the announcement is set to be sent to seniors
	 * @return true if it is, false otherwise
	 */
	public boolean isToSenior() {
		return toSenior;
	}

	/**
	 * Sets the announcement to send to seniors if true and not if false
	 * @param toSenior true if yes, false if not
	 */
	public void setToSenior(boolean toSenior) {
		this.toSenior = toSenior;
	}

	/**
	 * Checks if the announcement is set to be sent to graduate students
	 * @return true if it is, false otherwise
	 */
	public boolean isToGraduate() {
		return toGraduate;
	}

	/**
	 * Sets the announcement to send to graduate students if true and not if false
	 * @param toGraduate true if yes, false if not
	 */
	public void setToGraduate(boolean toGraduate) {
		this.toGraduate = toGraduate;
	}

	/**
	 * Checks if the announcement is set to be sent to faculty members
	 * @return true if it is, false otherwise
	 */
	public boolean isToFaculty() {
		return toFaculty;
	}

	/**
	 * Sets the announcement to send to faculty members if true and not if false
	 * @param toFaculty true if yes, false if not
	 */
	public void setToFaculty(boolean toFaculty) {
		this.toFaculty = toFaculty;
	}

	/**
	 * Checks if the announcement has an event time associated with it
	 * @return true if it does, false otherwise
	 */
	public boolean isHasEventTime() {
		return hasEventTime;
	}


	/**
	 * Checks if the announcement has been approved
	 * @return true if it is, false otherwise
	 */
	public boolean isApproved() {
		return approved;
	}


	/**
	 * @param approved the approved to set
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
}
