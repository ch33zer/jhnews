package com.jhnews.server;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

/**
 * User is the class that represents a user of our application and holds the user's preferences and settings.
 * @author Group 8
 */
@Entity
@Table(name="user")
public class UserHibernate implements Serializable{

	private int ID;
	private String firstName;
	private String lastName;
	private String email;
	private boolean isAdmin;
	private String username;
	private String hash;
	private static final long serialVersionUID = 1L;
	private Set<UserTagsHibernate> tags;
	
	/**
	 * The default constructor of the User class.
	 */
	public UserHibernate() {
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "uID")
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	@Column(name = "firstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	 * @return the isAdmin
	 */
	@Column(name="isAdmin")
	public boolean isAdmin() {
		return isAdmin;
	}
	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the tags
	 */
	@OneToMany(mappedBy="tagsHibernate", fetch = FetchType.EAGER)
	public Set<UserTagsHibernate> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(Set<UserTagsHibernate> tags) {
		this.tags = tags;
	}
	
	
}
