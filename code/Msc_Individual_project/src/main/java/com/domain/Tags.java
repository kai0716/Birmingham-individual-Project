package com.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Tags")
public class Tags {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TAG_ID")
	private int tagID=-1;

	@Column(name="Tag")
    private String tag;
	
	//Relationship
	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="Student_Tag",
			   joinColumns = {@JoinColumn(name ="tag_ID", referencedColumnName ="TAG_ID")},
			   inverseJoinColumns = {@JoinColumn(name ="student_ID", referencedColumnName ="STUDENT_ID")}
			   )
	private List<Students> studentList = new ArrayList<>();

	public Tags() {
		
	}
	
	public Tags(String tag) {
		this.tag = tag;
	}
	public int getTagID() {
		return tagID;
	}

	public void setTagID(int tagID) {
		this.tagID = tagID;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	public List<Students> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Students> studentList) {
		this.studentList = studentList;
	}
	
}
