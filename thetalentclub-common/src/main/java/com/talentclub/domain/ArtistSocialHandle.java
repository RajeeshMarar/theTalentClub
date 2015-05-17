package com.talentclub.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="artist_social_handles",uniqueConstraints={@UniqueConstraint(columnNames={"social_handle_id"})})
public class ArtistSocialHandle {
	
	//private Artist artist;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="artist_id",nullable=false)
	private Artist artist;
	
	
	public ArtistSocialHandle(){
		
	}
	
	public ArtistSocialHandle(String socialHandleId, String platform,
			String platformHandle, String profileUrl, String artist_id) {
		super();
		this.socialHandleId = socialHandleId;
		this.platform = platform;
		this.platformHandle = platformHandle;
		this.profileUrl = profileUrl;
		
	}

	@Id
	@Column(name="social_handle_id",nullable=false,unique=true,length=32 )
	private String socialHandleId;
	
	@Column(name="platform",nullable=false,length=30)
	private String platform;
	
	@Column(name="platform_handle",nullable=false,length=20)
	private String platformHandle;
	
	@Column(name="profile_url",nullable=false,length=100)
	private String profileUrl;
	

	public String getSocialHandleId() {
		return socialHandleId;
	}

	public void setSocialHandleId(String socialHandleId) {
		this.socialHandleId = socialHandleId;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPlatformHandle() {
		return platformHandle;
	}

	public void setPlatformHandle(String platformHandle) {
		this.platformHandle = platformHandle;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArtistSocialHandle [socialHandleId=" + socialHandleId
				+ ", platform=" + platform + ", platformHandle="
				+ platformHandle + ", profileUrl=" + profileUrl
				+ "]";
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
	
}
