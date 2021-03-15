package com.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Dto {

	public Dto() {
		
	}
	private String email;
	private String password;
	
	private String title;
	private String description;
	private String difficulty;
	private String link;
	private String status;
	private int planID;
	private String note;
	
	private int weeknumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date selectedDate;
	
	private List<String> tags;
	private String tag;
	private List<Integer> tagID;
	private int id;
	
	private int transferWeek;
	private List<Integer>checkBox;
	
	private String forum_title;
	private String forum_detail;
	private String content;
	
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
	public int getWeeknumber() {
		return weeknumber;
	}
	public void setWeeknumber(int weeknumber) {
		this.weeknumber = weeknumber;
	}
	public Date getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Integer> getCheckBox() {
		return checkBox;
	}
	public void setCheckBox(List<Integer> checkBox) {
		this.checkBox = checkBox;
	}
	public int getPlanID() {
		return planID;
	}
	public void setPlanID(int planID) {
		this.planID = planID;
	}

	public int getTransferWeek() {
		return transferWeek;
	}
	public void setTransferWeek(int transferWeek) {
		this.transferWeek = transferWeek;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<Integer> getTagID() {
		return tagID;
	}
	public void setTagID(List<Integer> tagID) {
		this.tagID = tagID;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getForum_title() {
		return forum_title;
	}
	public void setForum_title(String forum_title) {
		this.forum_title = forum_title;
	}
	public String getForum_detail() {
		return forum_detail;
	}
	public void setForum_detail(String forum_detail) {
		this.forum_detail = forum_detail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
