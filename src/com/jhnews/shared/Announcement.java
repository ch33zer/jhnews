package com.jhnews.shared;

import java.io.Serializable;
import java.util.ArrayList;
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
	private boolean hasEventTime;
	private ArrayList<String> tags;
	private Boolean[] audiences;
	//private Date submissionDate;
	//private Date announcementDate;

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor of the Announcement class.
	 */
	public Announcement(){
		this.audiences = new Boolean[6];//each Boolean represents a different target group 
		this.submitter = null;
		this.title = null;
		this.location = null;
		this.briefDescription = null;
		this.longDescription = null;
		this.eventDate = null;
		this.hasEventTime = false;
	}
	
	/**
	 * Gets the list of audiences the announcement is intended for.
	 * @return the list of audiences
	 */
	public Boolean[] getAudiences(){
		return this.audiences;
	}
	
	/**
	 * Sets a new list of audiences that the announcement is intended for.
	 * @param inAudiences the new audiences the announcement's intended for
	 */
	public void setAudiences(Boolean[] inAudiences){
		this.audiences = inAudiences; 
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
	 * Gets the list of tags the announcement is flagged for.
	 * @return the list of tags this announcement is flagged for
	 */
	public ArrayList<String> getTags(){
		return this.tags;
	}
	
	/**
	 * Sets the tags the announcement is associated with.
	 * @param inTags the new tags to set this announcement for
	 */
	public void setTags(ArrayList<String> inTags){
		this.tags = inTags;
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
}
