package com.jhnews.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Announcement is the class that represents an announcement submission and
 * holds all its relevant information.
 * 
 * @author Group 8
 */
@Entity
@Table(name = "announcement")
public class AnnouncementHibernate implements Serializable {

	private int ID;
	private String title;
	private String location;
	private String briefDescription;
	private String longDescription;
	private Date eventDate;
	private boolean hasEventTime;
	// private Date submissionDate;
	// private Date announcementDate;

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor of the Announcement class.
	 */
	public AnnouncementHibernate() {
		this.ID = -1;
		this.title = null;
		this.location = null;
		this.briefDescription = null;
		this.longDescription = null;
		this.eventDate = null;
		this.hasEventTime = false;
	}


	/**
	 * Gets the ID number of the announcement
	 * 
	 * @return the ID number of the announcement
	 */
	@Id
	@GeneratedValue
	@Column(name = "aID")
	public int getID() {
		return ID;
	}

	/**
	 * Sets a new ID number for this announcement
	 * 
	 * @param ID
	 *            the new ID for the announcement
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * Gets the title of this announcement
	 * 
	 * @return the title of the announcement
	 */

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of this announcement
	 * 
	 * @param title
	 *            the new title for the announcement
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the location of this announcement
	 * 
	 * @return the location of this announcement.
	 */

	@Column(name = "location")
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the announcement of this announcement
	 * 
	 * @param location
	 *            the new location to set this announcement to
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets a brief description of the announcement
	 * 
	 * @return the brief description of the announcement
	 */

	@Column(name = "briefDescription")
	public String getBriefDescription() {
		return briefDescription;
	}

	/**
	 * Sets a new brief description for this announcement
	 * 
	 * @param briefDescription
	 *            the new brief description for this announcement.
	 */
	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}

	/**
	 * Gets the long description of this announcement
	 * 
	 * @return the long description of this announcement.
	 */

	@Column(name = "longDescription")
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * Set a new long description for this announcement
	 * 
	 * @param longDescription
	 *            the new long description for this announcement
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	/**
	 * Gets the date of this announcement
	 * 
	 * @return the date of the event
	 */
	@Column(name="eventDate")
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * Sets a new date for this announcement
	 * 
	 * @param eventDate
	 *            the new date for this announcement
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * Set whether or not the event in this announcement has a scheduled time.
	 * 
	 * @param hasEventTime
	 *            whether or not this event has a scheduled time
	 */
	public void setHasEventTime(boolean hasEventTime) {
		this.hasEventTime = hasEventTime;
	}

	/**
	 * Tells whether or not the event in this announcement has a scheduled time
	 * 
	 * @return true if there's a scheduled time, false if not
	 */
	@Column(name = "hasEventTime")
	public boolean hasEventTime() {
		return hasEventTime;
	}
}
