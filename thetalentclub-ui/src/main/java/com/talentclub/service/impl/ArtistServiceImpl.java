package com.talentclub.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentclub.dao.ArtistDao;
import com.talentclub.domain.Artist;
import com.talentclub.http.ArtistRestClient;
import com.talentclub.service.ArtistService;

@Service
public class ArtistServiceImpl implements ArtistService {
	
	@Autowired
	ArtistRestClient restClient;
	
	@Autowired
	ArtistDao artistDao;

	@Override
	public Map<String, List<?>> getLocationsAndSkills() {
		System.out.println("In the service");
		return restClient.getAllLocationsSkills();
	}
	
	
	@Override
	public List<Artist> searchArtist(String locationId, String skillSet, int budgetLower, 
										int budgetUpper) {
		try {
			return restClient.searchArtist(locationId, skillSet, budgetLower,budgetUpper);
		} catch (Exception e) {
			System.out.println("error in fetching artists");
			return null;
		}
	}
}
