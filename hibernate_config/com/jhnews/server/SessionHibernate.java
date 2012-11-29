package com.jhnews.server;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class SessionHibernate {
	private int ID;
	private String sessionID;
	private Date expireDate;
	private UserHibernate user;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	@Column(name="sessionID")
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	@Column(name="expireDate")
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	@OneToOne(cascade=CascadeType.ALL)
	public UserHibernate getUser() {
		return user;
	}
	public void setUser(UserHibernate user) {
		this.user = user;
	}
}
