package com.talentclub.service;

import java.util.List;
import java.util.Map;

import com.talentclub.domain.Artist;


public interface ArtistService {
	
	public Map<String, List<?>> getLocationsAndSkills();
	public List<Artist> searchArtist(String locationId, String skillSet, int budgetLower,int budgetUpper);
}
