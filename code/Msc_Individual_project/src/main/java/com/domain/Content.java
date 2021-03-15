package com.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Disscisson_Content")
public class Content {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Content_ID", nullable=false)
	private int contentID=-1;
	
	@Column(columnDefinition = "LONGBLOB")
	private String content;
	private Date responseDate;
	
	@ManyToOne
    @JoinColumn(name = "Forum", referencedColumnName = "ForumID")
    private Forum forum;
	
	@ManyToOne
    @JoinColumn(name = "Student", referencedColumnName = "STUDENT_ID")
    private Students student;

	public Content() {
		
	}
	public Content(String content, Date date) {
		this.content =content;
		this.responseDate =date;
	}
	public int getContentID() {
		return contentID;
	}
	public void setContentID(int contentID) {
		this.contentID = contentID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public Students getStudent() {
		return student;
	}
	public void setStudent(Students student) {
		this.student = student;
	}
	
	
}
