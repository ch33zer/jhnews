package com.jhnews.server;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class AccountHibernate {
	private String username;
	private String hash;
	private int ID;
	private UserHibernate user;
	/**
	 * @return the uID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	public int getuID() {
		return ID;
	}
	/**
	 * @param ID the uID to set
	 */
	public void setuID(int ID) {
		this.ID = ID;
	}
	/**
	 * @return the hash
	 */
	@Column(name="hash")
	public String getHash() {
		return hash;
	}
	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}
	/**
	 * @return the username
	 */
	@Column(name="username")
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
	 * @return the user
	 */
	@OneToOne(cascade=CascadeType.ALL)
	public UserHibernate getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(UserHibernate user) {
		this.user = user;
	}
}
