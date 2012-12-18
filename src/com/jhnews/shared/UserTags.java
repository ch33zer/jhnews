package com.jhnews.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jhnews.server.UserHibernate;

public class UserTags implements Serializable{
	private static final long serialVersionUID = 2524033010461921665L;
	private int usertagsID;
	private User user;
	private Tags tags;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Tags getTags() {
		return tags;
	}
	public void setTags(Tags tags) {
		this.tags = tags;
	}
	
	public int getUsertagsID() {
		return usertagsID;
	}
	public void setUsertagsID(int usertagsID) {
		this.usertagsID = usertagsID;
	}

	
	
}
