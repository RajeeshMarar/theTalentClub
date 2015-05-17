package com.talentclub.http;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class HttpUtils {

	public static <T> T convertJsonToObject(String json, Class<T> type) {
		Gson gson =  getGson();
		return gson.fromJson(json, type);
	}

	public static <T> T convertJsonToObject(String json, Type genericType) {
		Gson gson =  getGson();
		return gson.fromJson(json, genericType);
	}

	public static String convertObjectToJson(Object o) {
		Gson gson = getGson();
		return gson.toJson(o);
	}

	private static Gson getGson() {
		GsonBuilder builder = new GsonBuilder(); 

	// Register an adapter to manage the date types as long values 
	    builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() { 
	    	public Date deserialize(JsonElement json, Type typeOfT, 
	    			JsonDeserializationContext context) throws JsonParseException {
	    		return new Date(json.getAsJsonPrimitive().getAsLong()); 
	    	} 
	    });

	    Gson gson = builder.create();
	    return gson;
	}

	public static String uriHelper(Map<String,String> urlParamMap)
	{
		StringBuilder sb = new StringBuilder("?");
		int paramCnt=0;
		for(String paramName : urlParamMap.keySet())
		{
			sb.append(paramName).append("=").append(urlParamMap.get(paramName));
			if(paramCnt++<(urlParamMap.size()-1))
			{
				sb.append("&");	
			}
		}
		return sb.toString();
	}
}
