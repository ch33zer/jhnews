package com.jhnews.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Override
	public String toString() {
		return "AnnouncementHibernate [ID=" + ID + ", title=" + title
				+ ", location=" + location + ", briefDescription="
				+ briefDescription + ", longDescription=" + longDescription
				+ ", eventDate=" + eventDate + ", hasEventTime=" + hasEventTime
				+ "]";
	}

	private int ID;
	private String title;
	private String location;
	private String briefDescription;
	private String longDescription;
	private Date eventDate;
	private boolean hasEventTime;
	private UserHibernate submitter;
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
	// private Date submissionDate;
	// private Date announcementDate;

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor of the Announcement class.
	 */
	public AnnouncementHibernate() {
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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


	/**
	 * @return the submitter
	 */
	@ManyToOne
	@JoinColumn(name="submitter")
	public UserHibernate getSubmitter() {
		return submitter;
	}


	/**
	 * @param submitter the submitter to set
	 */
	public void setSubmitter(UserHibernate submitter) {
		this.submitter = submitter;
	}


	@Column(name="aTag1")
	public boolean isTag1() {
		return tag1;
	}

	public void setTag1(boolean tag1) {
		this.tag1 = tag1;
	}


	@Column(name="aTag2")
	public boolean isTag2() {
		return tag2;
	}

	public void setTag2(boolean tag2) {
		this.tag2 = tag2;
	}


	@Column(name="aTag3")
	public boolean isTag3() {
		return tag3;
	}

	public void setTag3(boolean tag3) {
		this.tag3 = tag3;
	}


	@Column(name="aTag4")
	public boolean isTag4() {
		return tag4;
	}

	public void setTag4(boolean tag4) {
		this.tag4 = tag4;
	}


	@Column(name="aTag5")
	public boolean isTag5() {
		return tag5;
	}

	public void setTag5(boolean tag5) {
		this.tag5 = tag5;
	}


	@Column(name="toFreshman")
	public boolean isToFreshman() {
		return toFreshman;
	}

	public void setToFreshman(boolean toFreshman) {
		this.toFreshman = toFreshman;
	}


	@Column(name="toSophomore")
	public boolean isToSophomore() {
		return toSophomore;
	}

	public void setToSophomore(boolean toSophomore) {
		this.toSophomore = toSophomore;
	}


	@Column(name="toJunior")
	public boolean isToJunior() {
		return toJunior;
	}

	public void setToJunior(boolean toJunior) {
		this.toJunior = toJunior;
	}


	@Column(name="toSenior")
	public boolean isToSenior() {
		return toSenior;
	}

	public void setToSenior(boolean toSenior) {
		this.toSenior = toSenior;
	}


	@Column(name="toGraduate")
	public boolean isToGraduate() {
		return toGraduate;
	}

	public void setToGraduate(boolean toGraduate) {
		this.toGraduate = toGraduate;
	}


	@Column(name="toFaculty")
	public boolean isToFaculty() {
		return toFaculty;
	}

	public void setToFaculty(boolean toFaculty) {
		this.toFaculty = toFaculty;
	}
}
