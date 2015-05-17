package com.talentclub.domain.http;

/**
 * Tbis class encapsulates a rest response with a restful
 * entity
 * 
 * @author 
 * @since 1.0
 */
public class EntityRestResponse<E> extends RestResponse {
	
	private E responseBody;
	
	public EntityRestResponse(Status status, E responseBody){
		super(status);
		this.responseBody = responseBody;
	}

	public EntityRestResponse(Status status) {
		super(status);
	}

	public E getResponseBody() {
		return responseBody;
	}

}