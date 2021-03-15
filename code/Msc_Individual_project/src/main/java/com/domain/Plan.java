package com.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Plan")
public class Plan {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Plan_ID")
	private int planID=-1;
	
	private String title;
	@Column(columnDefinition = "LONGBLOB")
	private String description;
	private String status;
	private String difficulty;
	private String link;
	@Column(columnDefinition = "LONGBLOB")
	private String notes;
	
	public Plan() {
		
	}
	@ManyToOne
	@JoinColumn(name = "Week_ID_FK", referencedColumnName = "Week_ID")
	private Week week;

	
	public Plan(String title, String description, String difficulty,String link) {
		this.title = title;
		this.description = description;
		this.difficulty =difficulty;
		this.link =link;
	}

	public int getPlanID() {
		return planID;
	}

	public void setPlanID(int planID) {
		this.planID = planID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
