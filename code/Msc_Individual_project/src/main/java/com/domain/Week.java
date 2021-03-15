package com.domain;

import java.util.Date;
import java.util.List;

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
@Table(name="Week")
public class Week {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Week_ID")
	private int weekID=-1;
	
	@Column(name="Number_of_Weeks")
	private int numWeek;
	
	@Column(name="type")
	private String type;
	
	private Date startDate;
	private Date endDate;
	
	@OneToMany(mappedBy ="week",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Plan> plan;
	
	@ManyToOne
	@JoinColumn(name = "Student_ID_FK", referencedColumnName = "STUDENT_ID")
	private Students student;
	
	public Week() {
		
	}
	public Week(Students student,Date sd, Date ed) {
		this.student = student;
		this.startDate =sd;
		this.endDate = ed;
	}

	public int getWeekID() {
		return weekID;
	}

	public void setWeekID(int weekID) {
		this.weekID = weekID;
	}

	public int getNumWeek() {
		return numWeek;
	}

	public void setNumWeek(int numWeek) {
		this.numWeek = numWeek;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Plan> getPlan() {
		return plan;
	}

	public void setPlan(List<Plan> plan) {
		this.plan = plan;
	}
	
	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
