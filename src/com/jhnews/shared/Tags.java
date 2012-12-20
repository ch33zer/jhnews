package com.jhnews.shared;

import java.io.Serializable;

/** Tags is the class that represents a tag for a subject. 
 * @author group8
 *
 */
public class Tags implements Serializable{


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + tagID;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tags other = (Tags) obj;
		if (active != other.active)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tagID != other.tagID)
			return false;
		return true;
	}
	private static final long serialVersionUID = 2716995868590228712L;
	private int tagID;
	private String name;
	private boolean active;

	/** Gets the tag's ID
	 * @return this tag's ID
	 */
	public int getTagID() {
		return tagID;
	}
	
	/** Sets the tag's ID
	 * @param tagID the ID to set to
	 */
	public void setTagID(int tagID) {
		this.tagID = tagID;
	}

	/** Gets the tag's name
	 * @return the tag's name
	 */
	public String getName() {
		return name;
	}
	
	/** Sets the name of the tag
	 * @param name the new name to set 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}
	
	/** Gets whether or not the tag is active
	 * @return true if active, false otherwise
	 */
	public boolean isActive() {
		return active;
	}
	
	/** Sets the active state of the tag 
	 * @param active the state to set to
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}