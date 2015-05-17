package com.talentclub.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="artist_location",uniqueConstraints={@UniqueConstraint(columnNames={"artist_location_id"})})
public class ArtistLocation {
	
	public ArtistLocation() {}
	
	public ArtistLocation(String locationId,String locationName) {
		this.locationId=locationId;
		this.locationName=locationName;
	}
     
	public ArtistLocation(String artistLocationId, String locationId,
			String locationName, String artistId, Date createdAt, Date updatedAt) {
		super();
		this.artistLocationId = artistLocationId;
		this.locationId = locationId;
		this.locationName = locationName;
		this.artistId = artistId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Id
	@Column(name="artist_location_id",nullable=false,unique=true,length=32 )	
	private String artistLocationId;
	
	@Column(name="location_id",nullable=false,length=32)
	private String locationId;
	
	@Column(name="location_name",nullable=false,length=32)
	private String locationName;
	
	@Column(name="artist_id",nullable=false,length=32)
	private String artistId;
	
	@Column(name="creation_date")
	private Date   createdAt;
	
	@Column(name="update_date")
	private Date   updatedAt;

	@Override
	public String toString() {
		return "ArtistLocation [artistLocationId=" + artistLocationId
				+ ", locationId=" + locationId + ", locationName="
				+ locationName + ", artistId=" + artistId + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}

	public String getArtistLocationId() {
		return artistLocationId;
	}

	public void setArtistLocationId(String artistLocationId) {
		this.artistLocationId = artistLocationId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
