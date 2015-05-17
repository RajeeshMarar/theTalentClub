package com.talentclub.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class encapsulates the details
 * about an artist's images and is used as a
 * carrier of information to and fro
 * between application and persistence layer.
 * 
 * @author akshay
 * @since 1.0
 */
@Entity
@Table(name="artist_photos",uniqueConstraints={@UniqueConstraint(columnNames={"photo_id"})})


public class ArtistImage {

	public ArtistImage() {}
	
	public ArtistImage(String photoId, Artist artist, String uri,
			Date uploadedAt) {
		super();
		this.photoId = photoId;
		this.artist = artist;
		this.uri = uri;
		this.uploadedAt = uploadedAt;
	}
	
	public ArtistImage(String photoId, Artist artist) {
		super();
		this.photoId = photoId;
		this.artist = artist;
	}

	@Id
	@Column(name="photo_id",nullable=false,unique=true,length=32)
	private String photoId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="artist_id",nullable=false)
	private Artist artist;
	
	@Column(name="photo_uri",nullable=false,length=200)
	private String uri;
	
	@Column(name="uploaded_at")
	private Date uploadedAt;
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="profilePhoto")
	private Artist profileArtist;

	/**
	 * @return the photoId
	 */
	public String getPhotoId() {
		return photoId;
	}

	/**
	 * @param photoId the photoId to set
	 */
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
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

	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "ArtistImage [photoId=" + photoId 
				+ ", uri=" + uri + ", uploadedAt=" + uploadedAt + "]";
	}

	/*
	public Artist getProfileArtist() {
		return profileArtist;
	}

	public void setProfileArtist(Artist profileArtist) {
		this.profileArtist = profileArtist;
	}*/
	
}
