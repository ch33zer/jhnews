package com.jhnews.server;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usertags")
public class UserTagsHibernate {
	private int usertagsID;
	private UserHibernate user;
	private TagsHibernate tags;

	@ManyToOne
	@JoinColumn(name="userforeignkey")
	public UserHibernate getUser() {
		return user;
	}
	public void setUser(UserHibernate userHibernate) {
		this.user = userHibernate;
	}
	
	@ManyToOne
	@JoinColumn(name="tagforeignkey")
	public TagsHibernate getTags() {
		return tags;
	}
	public void setTags(TagsHibernate tagsHibernate) {
		this.tags = tagsHibernate;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "usertagsID")
	public int getUsertagsID() {
		return usertagsID;
	}
	public void setUsertagsID(int usertagsID) {
		this.usertagsID = usertagsID;
	}

	
	
}
