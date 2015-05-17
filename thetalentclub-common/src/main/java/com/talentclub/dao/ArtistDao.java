package com.talentclub.dao;

import java.util.List;

import com.talentclub.domain.ArtistLocation;
import com.talentclub.domain.SkillSet;

public interface ArtistDao {

public List<SkillSet> getSkillSet();
	
	public List<ArtistLocation> getAllLocations();
	public List<Object[]> searchArtist(String locationId, String skillSet,int budgetLower,int budgetUpper);
}
