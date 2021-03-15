package com.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="students")
public class Students {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STUDENT_ID", nullable=false)
	private int studentID=-1;
	
	@Column(name="Email", unique=true, nullable=false)
    private String email;

	@Column(name="Password", nullable=false)
    private String password;
	
	@Column(name="FirstName")
    private String firstName;
	
	@Column(name="LastName")
    private String lastName;

	@Column(name="Role")
    private int role;
	
    @Column(name="Supervisor_Approve")
    private int spApprove;
    
    @Column(name="SecondMarker_Approve")
    private int smApprove;
	
    private int submitVersion;
    private int submit;
    
	private int weeknumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="Start_Date")
	private Date selectedDate;
    
	//Relationship
	@OneToMany(mappedBy ="student",fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<SecondMarker> secondMarker;
	
	@OneToMany(mappedBy ="student",fetch=FetchType.LAZY)
	private List<Proposal> proposal;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Project_Type", referencedColumnName = "ID")
    private ProjectType projectType;

	@OneToMany(mappedBy ="student",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Week> weeks;
	
	@ManyToMany(mappedBy="studentList", cascade = CascadeType.ALL)
    private List<Tags> tagList = new ArrayList<Tags>();
	
	@OneToMany(mappedBy ="student",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Forum> forumList;
	
	@OneToMany(mappedBy ="student",fetch=FetchType.LAZY)
	private List<Content> contentList;
    
	public Students() {
	
	}	
	
	public Students(String email, String password, String firstName, String lastName, int role) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
	
	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	public int getSubmitVersion() {
		return submitVersion;
	}

	public void setSubmitVersion(int submitVersion) {
		this.submitVersion = submitVersion;
	}
	
	public int getSpApprove() {
		return spApprove;
	}

	public void setSpApprove(int spApprove) {
		this.spApprove = spApprove;
	}

	public int getSmApprove() {
		return smApprove;
	}

	public void setSmApprove(int smApprove) {
		this.smApprove = smApprove;
	}
	
	public int getSubmit() {
		return submit;
	}

	public void setSubmit(int submit) {
		this.submit = submit;
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
	// ---Getter Setter for relational data---
	public Set<SecondMarker> getSecondMarker() {
		return secondMarker;
	}

	public void setSecondMarker(Set<SecondMarker> secondMarker) {
		this.secondMarker = secondMarker;
	}

	public List<Proposal> getProposal() {
		return proposal;
	}

	public void setProposal(List<Proposal> proposal) {
		this.proposal = proposal;
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public List<Week> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<Week> weeks) {
		this.weeks = weeks;
	}

	public List<Tags> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tags> tagList) {
		this.tagList = tagList;
	}

	public List<Forum> getForumList() {
		return forumList;
	}

	public void setForumList(List<Forum> forumList) {
		this.forumList = forumList;
	}

	public List<Content> getContentList() {
		return contentList;
	}

	public void setContentList(List<Content> contentList) {
		this.contentList = contentList;
	}


}
