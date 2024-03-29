package com.jhnews.server;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + (approved ? 1231 : 1237);
		result = prime
				* result
				+ ((briefDescription == null) ? 0 : briefDescription.hashCode());
		result = prime * result
				+ ((eventDate == null) ? 0 : eventDate.hashCode());
		result = prime * result + (hasEventTime ? 1231 : 1237);
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((longDescription == null) ? 0 : longDescription.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + (toFaculty ? 1231 : 1237);
		result = prime * result + (toFreshman ? 1231 : 1237);
		result = prime * result + (toGraduate ? 1231 : 1237);
		result = prime * result + (toJunior ? 1231 : 1237);
		result = prime * result + (toSenior ? 1231 : 1237);
		result = prime * result + (toSophomore ? 1231 : 1237);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnnouncementHibernate other = (AnnouncementHibernate) obj;
		if (ID != other.ID)
			return false;
		if (approved != other.approved)
			return false;
		if (briefDescription == null) {
			if (other.briefDescription != null)
				return false;
		} else if (!briefDescription.equals(other.briefDescription))
			return false;
		if (eventDate == null) {
			if (other.eventDate != null)
				return false;
		} else if (!eventDate.equals(other.eventDate))
			return false;
		if (hasEventTime != other.hasEventTime)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (longDescription == null) {
			if (other.longDescription != null)
				return false;
		} else if (!longDescription.equals(other.longDescription))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (toFaculty != other.toFaculty)
			return false;
		if (toFreshman != other.toFreshman)
			return false;
		if (toGraduate != other.toGraduate)
			return false;
		if (toJunior != other.toJunior)
			return false;
		if (toSenior != other.toSenior)
			return false;
		if (toSophomore != other.toSophomore)
			return false;
		return true;
	}


	private UserHibernate submitter;
	private String title;
	private String location;
	private String briefDescription;
	private String longDescription;
	private Date eventDate;
	private boolean hasEventTime;
	private boolean approved;
	private TagsHibernate tag1;
	private TagsHibernate tag2;
	private TagsHibernate tag3;
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

	@Column(name="hasEventTime")
	public boolean isHasEventTime() {
		return hasEventTime;
	}


	public void setHasEventTime(boolean hasEventTime) {
		this.hasEventTime = hasEventTime;
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



	/**
	 * @return the tag1
	 */
	@OneToOne
	@JoinColumn(name="tag1")
	public TagsHibernate getTag1() {
		return tag1;
	}


	/**
	 * @param tag1 the tag1 to set
	 */
	public void setTag1(TagsHibernate tag1) {
		this.tag1 = tag1;
	}


	/**
	 * @return the tag2
	 */
	@OneToOne
	@JoinColumn(name="tag2")
	public TagsHibernate getTag2() {
		return tag2;
	}


	/**
	 * @param tag2 the tag2 to set
	 */
	public void setTag2(TagsHibernate tag2) {
		this.tag2 = tag2;
	}


	/**
	 * @return the tag3
	 */
	@OneToOne
	@JoinColumn(name="tag3")
	public TagsHibernate getTag3() {
		return tag3;
	}


	/**
	 * @param tag3 the tag3 to set
	 */
	public void setTag3(TagsHibernate tag3) {
		this.tag3 = tag3;
	}


	/**
	 * @return the approved
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
