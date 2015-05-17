package com.talentclub.domain.http;

public class RestResponse {
	
	

	private Status status;
	
	private Error error;
	
	public RestResponse(Status status) {
		this.status = status;
	}
	
	public RestResponse(Status status, Error error) {
		this(status);
		this.error = error;
	}
	
	public Status getStatus() {
		return status;
	}

	public Error getError() {
		return error;
	}
	
public static enum Status {
		
		OK(1200, "OK"),
		CREATED(1201, "Created"),
		INTERNAL_ERROR(1500, "Internal Server Error"), 
		AUTHENTICATION_ERROR(1401,"Authentication Error"),
		NOT_FOUND(1404, "Resource not Found");
		
		private int code;
		private String message;
		
		
		private Status(int code, String message) {
			this.code = code;
			this.message = message;
		}
		
		public int getCode() {
			return code;
		}
		public String getMessage() {
			return message;
		}
		
	}
	
	public static enum Error {
		INTERNAL_SERVER_ERROR(1500, "Internal Server Error"),
		ARTIST_NOT_FOUND(1404, "Artist not found"),
		AUTHENTICATION_ERROR(401,"Authentication Error"),
		SKILL_SET_NOT_FOUND(1405,"Artist skills not found"),
		LOCATIONS_NOT_FOUND(1406,"Locations not found"),
		LOCATIONS_ARTIST_NOT_FOUND(1407,"Locations or Artists are not available"),
		ARTISTS_NOT_FOUND(1407,"Artists not found for given search parameters"),
		FEATURED_ARTISTS_NOT_FOUND(1408,"Artists not found for given search parameters"),
		UNABLE_TO_SAVE_QUOTATION(1409,"Quotation request could be processed right now");
		
		
		private int code;
		private String message;
		
		private Error(int code, String message) {
			this.code = code;
			this.message = message;
		}

		public int getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}
	}
}
