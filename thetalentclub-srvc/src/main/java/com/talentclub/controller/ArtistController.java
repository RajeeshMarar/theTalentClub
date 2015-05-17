package com.talentclub.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talentclub.domain.Artist;
import com.talentclub.domain.ArtistLocation;
import com.talentclub.domain.SkillSet;
import com.talentclub.domain.http.EntityRestResponse;
import com.talentclub.domain.http.RestResponse;
import com.talentclub.domain.http.RestResponse.Status;
import com.talentclub.service.ArtistService;
@RestController
@RequestMapping("/artist")
public class ArtistController {

	@Autowired
	private ArtistService artistService;

	@RequestMapping(value="/skillsAndLocations",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public RestResponse getAllLocations(){
		RestResponse response = null;
		try {
			List<ArtistLocation> locationLst = artistService.getAllLocations();
			List<SkillSet> skillSetLst = artistService.getSkillSet();
			Map<String,List<?>> skillLocationHm = new HashMap<String,List<?>>();
			skillLocationHm.put("skills", skillSetLst);
			skillLocationHm.put("locations", locationLst);

			if(locationLst != null && skillLocationHm!=null) {
				if(locationLst.size()==0 || locationLst.size()==0)
				{
					response = new RestResponse(Status.INTERNAL_ERROR, RestResponse.Error.LOCATIONS_ARTIST_NOT_FOUND);
				}
				else{
					response = new EntityRestResponse<Map<String,List<?>>>(Status.OK, skillLocationHm);
				}
			} else {

				response = new RestResponse(Status.INTERNAL_ERROR, RestResponse.Error.INTERNAL_SERVER_ERROR);
			}

		} catch(Exception e) {
			response = new RestResponse(Status.INTERNAL_ERROR, RestResponse.Error.INTERNAL_SERVER_ERROR);
		}

		return response;
	}	
	
	@RequestMapping(value="/search", method={RequestMethod.GET,RequestMethod.POST},produces=MediaType.APPLICATION_JSON_VALUE)
	public  RestResponse searchArtist(@RequestParam("location") String location, @RequestParam("skillSet") String skillSet, 
			@RequestParam("budgetLower") int budgetLower, @RequestParam("budgetUpper") int budgetUpper) {

		List<Artist> artistList;
		RestResponse response = null;
		System.out.println("Location:"+location +"skillset"+skillSet+"budget"+budgetLower+" up "+budgetUpper);
		try{
			artistList = artistService.searchArtist(location, skillSet, budgetLower,budgetUpper);
			if(artistList !=null)
			{
				response = new EntityRestResponse<List<Artist>>(Status.OK, artistList);
			}
			else
			{
				response = new RestResponse(Status.INTERNAL_ERROR, RestResponse.Error.ARTISTS_NOT_FOUND);
			}
		} catch(Exception e) {
			response = new RestResponse(Status.INTERNAL_ERROR, RestResponse.Error.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	}

