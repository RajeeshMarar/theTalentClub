package com.talentclub.dao;

import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.talentclub.domain.ArtistLocation;
import com.talentclub.domain.SkillSet;

public class Configuration {

	private List<ArtistLocation> locations;
	private List<SkillSet> skillSet;
	
	
	@Autowired
	private ArtistDao artistDao;
	
	@PostConstruct
	public void init() {
		locations = artistDao.getAllLocations();
		skillSet = artistDao.getSkillSet();
		
	}

	public List<ArtistLocation> getLocations() {
		return locations;
	}

	public List<SkillSet> getSkillSet() {
		return skillSet;
	}

	
	
}
