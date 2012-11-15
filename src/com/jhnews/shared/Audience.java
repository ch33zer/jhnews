package com.jhnews.shared;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;


/** Represents the audience an announcement will reach
 * @author Group 8
 *
 */
public class Audience {
	private Set<AudienceEnum> audienceList;
	/** Creates a new Audience 
	 * @param audienceEnums the list of AudienceEnums
	 */
	public Audience(List<AudienceEnum> audienceEnums) {
		audienceList = EnumSet.copyOf(audienceEnums);
		for (AudienceEnum e : audienceEnums) {
			audienceList.add(e);
		}
	}
	
	/** Determine if this Audience contains audienceMember
	 * @param audienceMember The audience member to check
	 * @return Whether or not the element is contained
	 */
	public boolean isInAudience(AudienceEnum audienceMember) {
		return audienceList.contains(audienceMember);
	}
	
	/**Return a list of this audience
	 * @return The audience
	 */
	public Collection<AudienceEnum> getAudience() {
		return audienceList;
	}
	
	/**Add an audience member to this audience
	 * @param audienceMember The audience
	 */
	public void addAudienceMember(AudienceEnum audienceMember) {
		audienceList.add(audienceMember);
	}
	
	/**Remove an audience member
	 * @param audienceMember the member to remove
	 */
	public void removeAudienceMember(AudienceEnum audienceMember) {
		audienceList.remove(audienceMember);
	}
	
}
