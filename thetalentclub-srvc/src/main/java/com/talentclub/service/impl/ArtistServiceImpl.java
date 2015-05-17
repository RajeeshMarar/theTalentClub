package com.talentclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentclub.dao.ArtistDao;
import com.talentclub.domain.Artist;
import com.talentclub.domain.ArtistImage;
import com.talentclub.domain.ArtistLocation;
import com.talentclub.domain.SkillSet;
import com.talentclub.service.ArtistService;
@Service
public class ArtistServiceImpl implements ArtistService {

	@Autowired
	ArtistDao artistDao;
	
	@Override
	public List<ArtistLocation> getAllLocations() {
		return artistDao.getAllLocations();
	}

	@Override
	public List<SkillSet> getSkillSet() {
		
		return artistDao.getSkillSet();
	}
	
	/**
	 * here from DAO we are getting multiple rows for same artist in 
	 * case of multiple locations for same artist. So converting it to 
	 * single object even for multiple locations
	 * 
	 */
	public List<Artist> searchArtist(String locationId, String skillSet, int budgetLower,int budgetUpper) 
	{
		List<Object[]> artistObjList= artistDao.searchArtist(locationId,skillSet,budgetLower,budgetUpper);
		List<Artist> artistLst = new ArrayList<Artist>();
		Artist tmpArtist=null;
		ArtistLocation location;
		ArtistImage image;
		List<ArtistLocation> locationLst;
		int fromIndex=0;
		int toIndex=0;
		
		System.out.println("input params =====>  locationId "+locationId+" skillSet  "+skillSet+"  budget low "+budgetLower+" budget up "+budgetUpper);
		for(Object[] artistData:artistObjList) 
		{			
			tmpArtist = new Artist((String)artistData[0], (String)artistData[1], (String)artistData[2], (String)artistData[3],(String)artistData[4],(String)artistData[5], (String)artistData[6], (String)artistData[7], (String)artistData[8], (String)artistData[9], (String)artistData[10], (String)artistData[11], (String)artistData[12], (Boolean)artistData[15], (Integer)artistData[16],  (Integer)artistData[17], (Integer)artistData[18]);
			location = new ArtistLocation((String)artistData[19], (String)artistData[20]);
			String photoId = null;
			image = null;
			if(artistData[21] != null) {
				photoId = (String)artistData[21];
				image = new ArtistImage();
				image.setPhotoId(photoId);
				image.setUri((String)artistData[22]);
			}
			
            if(artistLst.contains(tmpArtist))	
            {
            	tmpArtist=artistLst.get(artistLst.indexOf(tmpArtist));
            	locationLst=(tmpArtist.getLocationList()==null?new ArrayList<ArtistLocation>():tmpArtist.getLocationList());
            	locationLst.add(location);
            	tmpArtist.setLocationList(locationLst);
            }
            else
            {
            	locationLst = new ArrayList<ArtistLocation>();
            	locationLst.add(location);
            	tmpArtist.setLocationList(locationLst);
            	artistLst.add(tmpArtist);
            	tmpArtist.setProfilePhoto(image);
            }
		}
		
		return artistLst;
	}

}
