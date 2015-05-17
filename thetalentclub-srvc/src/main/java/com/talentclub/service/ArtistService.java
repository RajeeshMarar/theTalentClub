package com.talentclub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.talentclub.domain.Artist;
import com.talentclub.domain.ArtistLocation;
import com.talentclub.domain.SkillSet;

@Service
public interface ArtistService {
	
	public List<ArtistLocation> getAllLocations();
	
	public List<SkillSet> getSkillSet();
	
	public List<Artist> searchArtist(String locationId, String skillSet, 
			int budgetLower, int budgetUpper);

}
