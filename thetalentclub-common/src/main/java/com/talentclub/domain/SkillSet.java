package com.talentclub.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="skill_set",uniqueConstraints={@UniqueConstraint(columnNames={"skill_id"})})
public class SkillSet {
	
	public SkillSet(String skillId, String skillName) {
		super();
		this.skillId = skillId;
		this.skillName = skillName;
	}
	
	public SkillSet() {
		super();
	}

	@Id
	@Column(name="skill_id",nullable=false,unique=true,length=32)	
	private String skillId;
	
	@Column(name="skill_name",nullable=false,length=50)
    private String skillName;
	@Column(name="update_date")
	private Date updatedAt;
	@Column(name="creation_date")
	private Date createdAt;
	@Column(name="created_by",nullable=false,length=50)
	private String createdBy;
	@Column(name="updated_by",nullable=false,length=50)
	private String updatedBy;

	public String getSkillId() {
		return skillId;
	}
	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}
