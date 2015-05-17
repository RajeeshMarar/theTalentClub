package com.talentclub.http;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import com.google.gson.reflect.TypeToken;
import com.talentclub.domain.Artist;
import com.talentclub.domain.http.EntityRestResponse;
import com.talentclub.domain.http.RestResponse.Error;
import com.talentclub.domain.http.RestResponse.Status;

public class ArtistRestClient {
private RestOperations restTemplate;
	
	@Value("${rest.srvc.host}")
	private String host;
	
	@Value("${rest.srvc.scheme}")
	private String scheme;
	
	@Value("${rest.srvc.context}")
	private String context;
	
	@Value("${rest.srvc.port}")
	private int port;
	
	
	public ArtistRestClient(RestOperations restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	//method to fetch data for first page ie all locations and skills for dropdown...
	public Map<String,List<?>> getAllLocationsSkills() {
		Map<String,List<?>> skillLocationHm = new HashMap<String,List<?>>();

		String url =  scheme+"://"+host+(port == 80?"":":"+port)+context+"/artist/skillsAndLocations";
		System.out.println("url -->"+url);
		HttpHeaders reqHeaders = new HttpHeaders();
		reqHeaders.set("Accept", "application/json");
		HttpEntity<HttpHeaders> requestEntity = new HttpEntity<HttpHeaders>(reqHeaders);
		HttpEntity<String> response = null;
		String jsonResp = null;
		
		
		try {
			response = restTemplate.exchange(url,HttpMethod.GET, requestEntity, String.class);
			jsonResp = response.getBody();
			System.out.println("jsonResp-->"+jsonResp);
			Type genericType = new TypeToken<EntityRestResponse<Map<String, List<?>>>>() {}.getType();
			 
			@SuppressWarnings("unchecked")
			EntityRestResponse<Map<String, List<?>>> skillLocationsResp =  (EntityRestResponse<Map<String, List<?>>>)HttpUtils.convertJsonToObject(jsonResp, genericType);
			System.out.println("skillLocationsResp -->"+skillLocationsResp);

			if(skillLocationsResp != null) {
				if(skillLocationsResp.getStatus().equals(Status.OK)) {
					skillLocationHm = ((EntityRestResponse<Map<String,List<?>>>)skillLocationsResp).getResponseBody();
						
				} else if(skillLocationsResp.getError() != null) {
					Error error = skillLocationsResp.getError();
					System.out.println("error-->"+error);
					//throw new Exception(error.getCode(), error.getMessage());
				}
			}
		}  catch(Exception exp){
			exp.printStackTrace();
		}
		return skillLocationHm;
	}
	
	public List<Artist> searchArtist(String locationId, String skillSet, int budgetLower, int budgetUpper) 
	{

Map<String, String> requestParam= new HashMap<String,String>();
requestParam.put("location", locationId);
requestParam.put("skillSet", skillSet);
requestParam.put("budgetLower", Integer.toString( budgetLower));
requestParam.put("budgetUpper", Integer.toString( budgetUpper));

String url = scheme+"://"+host+(port == 80?"":":"+port)+context+"/artist/search"+HttpUtils.uriHelper(requestParam);

System.out.println("URL is : "+url);
HttpHeaders reqHeaders = new HttpHeaders();
reqHeaders.set("Accept", "application/json");
HttpEntity<HttpHeaders> requestEntity = new HttpEntity<HttpHeaders>(reqHeaders);
HttpEntity<String> response = null;
String jsonResp = null;
List<Artist> returnedArtistLst = null;
try {
	response = restTemplate.exchange(url,HttpMethod.GET, requestEntity, String.class);
	jsonResp = response.getBody();
	Type genericType = new TypeToken<EntityRestResponse<List<Artist>>>() {}.getType();
	EntityRestResponse<List<Artist>> artistSearchResp =  HttpUtils.convertJsonToObject(jsonResp, genericType);
	if(artistSearchResp != null) {
		if(artistSearchResp.getStatus().equals(Status.OK)) {
			returnedArtistLst = ((EntityRestResponse<List<Artist>>)artistSearchResp).getResponseBody();
		} else if(artistSearchResp.getError() != null) {
			Error error = artistSearchResp.getError();
			System.out.println("error from client "+error);
		}
	}
} catch(Exception exp) {
	System.out.println("some error occurred in artist search results");
}

System.out.println("search result size is ::::: "+returnedArtistLst.size());
return returnedArtistLst;
}
	
}
