package com.jhnews.server;

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
	private UserHibernate userHibernate;
	private TagsHibernate tagsHibernate;

	@ManyToOne
	@JoinColumn(name="userforeignkey")
	public UserHibernate getUserHibernate() {
		return userHibernate;
	}
	public void setUserHibernate(UserHibernate userHibernate) {
		this.userHibernate = userHibernate;
	}
	
	@OneToOne
	@JoinColumn(name="tagforeignkey")
	public TagsHibernate getTagsHibernate() {
		return tagsHibernate;
	}
	public void setTagsHibernate(TagsHibernate tagsHibernate) {
		this.tagsHibernate = tagsHibernate;
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
