package com.talentclub.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class encapsulates the details about an 
 * artist's media(images/video/audio) and is used 
 * as a carrier of information to and fro between 
 * application and persistence layer.
 * 
 * @author akshay
 * @since 1.0
 */
@Entity
@Table(name="artist_media",uniqueConstraints={@UniqueConstraint(columnNames={"media_id"})})
public class ArtistMedia {
	
	@Id
	@Column(name="media_id",nullable=false, unique=true, length=32)
	private String mediaId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="artist_id", nullable=false)
	private Artist artist;
	
	@Column(name="media_type", nullable=false, length=32)
	@Enumerated(EnumType.STRING) 
	private MediaType mediaType;
	
	@Column(name="media_thumb_uri", length=200)
	private String mediaThumbUri;
	
	@Column(name="media_uri", nullable=false, length=200)
	private String mediaUri;
	
	@Column(name="media_provider_id", length=50)
	private String mediaProviderId;
	
	
	public ArtistMedia(String mediaId, Artist artist, MediaType mediaType) {
		super();
		this.mediaId = mediaId;
		this.artist = artist;
		this.mediaType = mediaType;
	}

	@Column(name="uploaded_at")
	private Date uploadedAt;
	
	public ArtistMedia(){}

	/**
	 * @return the mediaId
	 */
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @return the artist
	 */
	public Artist getArtist() {
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	/**
	 * @return the mediaType
	 */
	public MediaType getMediaType() {
		return mediaType;
	}

	/**
	 * @param mediaType the mediaType to set
	 */
	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * @return the mediaThumbUri
	 */
	public String getMediaThumbUri() {
		return mediaThumbUri;
	}

	/**
	 * @param mediaThumbUri the mediaThumbUri to set
	 */
	public void setMediaThumbUri(String mediaThumbUri) {
		this.mediaThumbUri = mediaThumbUri;
	}

	/**
	 * @return the mediaUri
	 */
	public String getMediaUri() {
		return mediaUri;
	}

	/**
	 * @param mediaUri the mediaUri to set
	 */
	public void setMediaUri(String mediaUri) {
		this.mediaUri = mediaUri;
	}
	
	public String getMediaProviderId() {
		return mediaProviderId;
	}

	public void setMediaProviderId(String mediaProviderId) {
		this.mediaProviderId = mediaProviderId;
	}

	/**
	 * @return the uploadedAt
	 */
	public Date getUploadedAt() {
		return uploadedAt;
	}

	/**
	 * @param uploadedAt the uploadedAt to set
	 */
	public void setUploadedAt(Date uploadedAt) {
		this.uploadedAt = uploadedAt;
	}
}
