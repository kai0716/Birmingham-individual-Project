package com.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Forum")
public class Forum {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ForumID", nullable=false)
	private int forumID=-1;
	
	private String title;
	@Column(columnDefinition = "LONGBLOB") 
	private String detail;
	private Date createdDate;
	
	@ManyToOne
    @JoinColumn(name = "Questioner", referencedColumnName = "STUDENT_ID")
    private Students student;
	
	@OneToMany(mappedBy ="forum",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Content> contentList;
	
	public Forum() {
		
	}
	public Forum(String title, String detail, Date date,Students student) {
		this.title=title;
		this.detail =detail;
		this.createdDate =date;
		this.student =student;
	}
	public int getForumID() {
		return forumID;
	}
	public void setForumID(int forumID) {
		this.forumID = forumID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Students getStudent() {
		return student;
	}
	public void setStudent(Students student) {
		this.student = student;
	}
	public List<Content> getContentList() {
		return contentList;
	}
	public void setContentList(List<Content> contentList) {
		this.contentList = contentList;
	}
	
	
}
