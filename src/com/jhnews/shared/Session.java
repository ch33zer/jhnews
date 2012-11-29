package com.jhnews.shared;

import java.io.Serializable;
import java.util.Date;

/**Holds details of the current users session
 * @author Group 8
 *
 */
public class Session implements Serializable {
	/**
	 * For serialization
	 */
	private static final long serialVersionUID = 257714321022849090L;
	private String SessionID;
	private Date expireDate;
	private User user;
	private int ID;

	/**
	 * Construct an empty Session
	 */
	public Session() {
		SessionID = null;
		expireDate = null;
	}

	/**Gets the ID associated with this session
	 * @return The session, or null if none
	 */
	public String getSessionID() {
		return SessionID;
	}

	/**Sets the session ID
	 * @param sessionID The desired sessionID
	 */
	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((SessionID == null) ? 0 : SessionID.hashCode());
		result = prime * result
				+ ((expireDate == null) ? 0 : expireDate.hashCode());
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
		Session other = (Session) obj;
		if (SessionID == null) {
			if (other.SessionID != null)
				return false;
		} else if (!SessionID.equals(other.SessionID))
			return false;
		if (expireDate == null) {
			if (other.expireDate != null)
				return false;
		} else if (!expireDate.equals(other.expireDate))
			return false;
		return true;
	}

	/** Returns if the session is still valid. AKA, the current new Date() < getExpireDate()
	 * @return Whether or not the session is still
	 */
	public boolean isStillValid() {
		return expireDate == null ? true : expireDate.before(new Date());
	}

	/** Get the date the session expires
	 * @return The expiration date
	 */
	public Date getExpireDate() {
		return expireDate;
	}

	/**Sets the date the session will expire
	 * @param expireDate The desired expire date
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

}
