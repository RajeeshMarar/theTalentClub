package com.talentclub.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talentclub.command.SearchArtistForm;
import com.talentclub.dao.Configuration;
import com.talentclub.domain.Artist;
import com.talentclub.domain.ArtistLocation;
import com.talentclub.domain.SkillSet;
import com.talentclub.service.ArtistService;

@Controller
@RequestMapping("/artist")
public class ArtistController {

	@Autowired
	ArtistService artistService;
	
	@Autowired
	Configuration config;
	@RequestMapping(value="/search",method=RequestMethod.GET)
    public String getAllLocationsSkills(ModelMap model) {
            try {
                    SearchArtistForm artistLocForm = new SearchArtistForm();
                    Map <String, List<?>> mpData = artistService.getLocationsAndSkills();
                    
                   
                    model.put("skills",config.getSkillSet());
                    model.put("locations",config.getLocations());
                    model.put("skillLocModel", artistLocForm);
                    model.put("test", "here in model");
                    
            } catch(Exception e) {
                    e.printStackTrace();
            }
            return "home";
    }
	
	@RequestMapping(value="/search-results",method=RequestMethod.GET)
	public String searchArtist(@ModelAttribute("skillLocModel")SearchArtistForm artistLocForm ,ModelMap model) {
		try {
            
			//int perPageCount=config.getPerPageCount();
			List<Artist> artistList=artistService.searchArtist(artistLocForm.getLocationId(), artistLocForm.getSkillId(), artistLocForm.getBudgetLower(),artistLocForm.getBudgetUpper());
			model.put("artistList", artistList);
			// parameters necessary for more request start
			model.put("location", artistLocForm.getLocationId());
			model.put("skillId", artistLocForm.getSkillId());
			model.put("budgetUpper", artistLocForm.getBudgetUpper());
			model.put("budgetLower", artistLocForm.getBudgetLower());
			model.put("pageNum", artistLocForm.getPageNum());
			// parameters necessary for more request end
			model.put("locations", config.getLocations());
			model.put("skills", config.getSkillSet());
			model.put("message", "this msg is from searchArtist method model map...");
			System.out.println("artist search size "+artistList.size());
		} catch(Exception e) {
			System.out.println("some error in fetching artists");
		}
		return "artistSearchResult";
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
    public String privacyPolicy(ModelMap model)
    {
            return "artist_details";
    }

}
