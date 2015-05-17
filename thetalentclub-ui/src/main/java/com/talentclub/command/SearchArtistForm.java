package com.talentclub.command;

/**
 * This class encapsulates ui form
 * elements for capturing artist 
 * location form
 * 
 * @author mickey
 */
public class SearchArtistForm {
	
	private String skillId;
	private String locationId;
	private int budgetLower;
	private int budgetUpper;
	private int  pageNum;
	
	public String getSkillId() {
		return skillId;
	}
	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getBudgetLower() {
		return budgetLower;
	}
	public void setBudgetLower(int budgetLower) {
		this.budgetLower = budgetLower;
	}
	public int getBudgetUpper() {
		return budgetUpper;
	}
	public void setBudgetUpper(int budgetUpper) {
		this.budgetUpper = budgetUpper;
	}
}
