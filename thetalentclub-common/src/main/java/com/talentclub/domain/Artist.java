package com.talentclub.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



/**
 * This class encapsulates the details
 * about an artist and is used as a
 * carrier of information to and fro
 * between application and persistence layer.
 * 
 * @author mickey
 * @since 1.0
 */
@Entity
@Table(name="artist",uniqueConstraints={@UniqueConstraint(columnNames={"artist_id"})})
public class Artist {
	
	public Artist(){}
	
	public Artist(String artistId, String firstName, String lastName,
			String stageName,String gender ,String skillSet, String quote,
			String description, String email, String phone, String address,
			String profilePhotoId, String status, boolean featured,
			int feeLower, int feeUpper, int commission) {
		super();
		this.artistId = artistId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.stageName = stageName;
		this.gender=gender;
		this.skillSet = skillSet;
		this.quote = quote;
		this.description = description;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.profilePhotoId = profilePhotoId;
		this.status = status;
		this.featured = featured;
		this.feeLower = feeLower;
		this.feeUpper = feeUpper;
		this.commission = commission;
	}

	@Id
	@Column(name="artist_id",nullable=false,unique=true,length=32)
	private String artistId;
	
	@Column(name="first_name",nullable=false,length=50)
	private String firstName;
	
	@Column(name="last_name",nullable=true,length=50)
	private String lastName;
	
	@Column(name="stage_name",nullable=true,length=50)
	private String stageName;
	
	@Column(name="gender",nullable=true)
	private String gender;
	
	@Column(name="skill_set",nullable=true,length=200)
	private String skillSet;
	
	@Column(name="quote",nullable=true,length=160)
	private String quote;
	
	@Column(name="description",nullable=true)
	private String description;
	
	@Column(name="email",nullable=false,length=50)
	private String email;
	
	@Column(name="phone_no",nullable=false,length=12)
	private String phone;
	
	@Column(name="address",nullable=true,length=500)
	private String address;
	
	@Column(name="profile_photo_id",nullable=true,length=500)
	private String profilePhotoId;
	
	@Column(name="status",nullable=false)
	private String status;
	
	@Column(name="creation_date")
	private Date createdAt;
	
	@Column(name="update_date")
	private Date updatedAt;
	
	@Column(name="featured",nullable=false)
	private boolean featured;
	
	@Column(name="fee_lower",nullable=true)
	private int feeLower;
	
	@Column(name="fee_upper",nullable=true)
	private int feeUpper;
	
	@Column(name="commission",nullable=true)
	private int commission;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="profile_photo_id",insertable=false,updatable=false)
	private ArtistImage profilePhoto;
		
	@Transient
	List<ArtistLocation> locationList;
	
	@OneToMany(mappedBy="artist",fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ArtistImage> imagesList;
	
	@OneToMany(mappedBy="artist",fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ArtistMedia> mediaList;
	
	@Transient
    private List<ArtistMedia> videoList;

	@Transient
    private List<ArtistMedia> audioList;
	
	@OneToMany(mappedBy="artist",fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ArtistSocialHandle> socialHandleList;
	
	/**
	 * 
	 * @return artist pseudo price upper bound
	 */
	public int getPriceIndicator(){
		//Configuration config= ApplicationContextBeanProvider.getBean(Configuration.class);
		int rangeMin=0;
		int rangeMax=500000;
		int rangeDivisionCount=5;//config.getRangeDivisionCount();
		int priceIndicator = ((rangeDivisionCount*(feeUpper-rangeMin))/(rangeMax-rangeMin))+1;
		return priceIndicator;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artistId == null) ? 0 : artistId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		if (artistId == null) {
			if (other.artistId != null)
				return false;
		} else if (!artistId.equals(other.artistId))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Artist [artistId=" + artistId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", skillSet=" + skillSet
				+ ", stageName=" + stageName + ", quote=" + quote
				+ ", description=" + description + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", status="
				+ status + ", featured=" + featured + ", feeLower=" + feeLower
				+ ", feeUpper=" + feeUpper + ", commission=" + commission
				+ ", locationLst=" + locationList + "]"+" priceIndicator"+getPriceIndicator();
	}
	
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public boolean isFeatured() {
		return featured;
	}
	public void setFeatured(boolean featured) {
		this.featured = featured;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getFeeLower() {
		return feeLower;
	}
	public void setFeeLower(int feeLower) {
		this.feeLower = feeLower;
	}
	public int getFeeUpper() {
		return feeUpper;
	}
	public void setFeeUpper(int feeUpper) {
		this.feeUpper = feeUpper;
	}
	public int getCommission() {
		return commission;
	}
	public void setCommission(int commission) {
		this.commission = commission;
	}
	public String getSkillSet() {
		return skillSet;
	}
	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}
	public List<ArtistLocation> getLocationList() {
		return locationList;
	}
	public void setLocationList(List<ArtistLocation> locationList) {
		this.locationList = locationList;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public ArtistImage getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(ArtistImage profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getProfilePhotoId() {
		return profilePhotoId;
	}

	public void setProfilePhotoId(String profilePhotoId) {
		this.profilePhotoId = profilePhotoId;
	}

	public List<ArtistImage> getImagesList() {
		return imagesList;
	}

	public void setImagesList(List<ArtistImage> imagesList) {
		this.imagesList = imagesList;
	}

	public List<ArtistMedia> getMediaList() {
		return mediaList;
	}

	public void setMediaList(List<ArtistMedia> mediaList) {
		this.mediaList = mediaList;
	}

	public List<ArtistMedia> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<ArtistMedia> videoList) {
		this.videoList = videoList;
	}

	public List<ArtistMedia> getAudioList() {
		return audioList;
	}

	public void setAudioList(List<ArtistMedia> audioList) {
		this.audioList = audioList;
	}

	/**
	 * @return the socialHandleList
	 */
	public List<ArtistSocialHandle> getSocialHandleList() {
		return socialHandleList;
	}

	/**
	 * @param socialHandleList the socialHandleList to set
	 */
	public void setSocialHandleList(List<ArtistSocialHandle> socialHandleList) {
		this.socialHandleList = socialHandleList;
	}





}
