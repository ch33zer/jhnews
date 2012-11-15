package com.jhnews.shared;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;


public class Audience {
	private Set<AudienceEnum> audienceList;
	public Audience(List<AudienceEnum> audienceEnums) {
		audienceList = EnumSet.copyOf(audienceEnums);
		for (AudienceEnum e : audienceEnums) {
			audienceList.add(e);
		}
	}
	
	public boolean isInAudience(AudienceEnum audienceMember) {
		return audienceList.contains(audienceMember);
	}
	
	public Collection<AudienceEnum> getAudience() {
		return audienceList;
	}
	
	public void addAudienceMember(AudienceEnum audienceMember) {
		audienceList.add(audienceMember);
	}
	
	public void removeAudienceMember(AudienceEnum audienceMember) {
		audienceList.remove(audienceMember);
	}
	
}
