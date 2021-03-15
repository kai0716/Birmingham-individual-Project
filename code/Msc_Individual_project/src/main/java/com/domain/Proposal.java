package com.domain;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Proposal")
public class Proposal {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Proposal_ID")
	private int proposalID=-1;

	@Column(name="Project_Type")
    private String project_type;
	
	@Column(name="Project_Title")
    private String project_title;
	
	@Column(name="Project_Aim",columnDefinition = "LONGBLOB")
    private String project_aim;
	
	@Column(name="Project_Relative_Work",columnDefinition = "LONGBLOB")
    private String project_relativeWork;
	
	@Column(name="Project_Objective",columnDefinition = "LONGBLOB")
    private String project_objective;
	
	@Column(name="Project_Methodology",columnDefinition = "LONGBLOB")
    private String project_methodology;
	
	@Column(name="Preliminary_Architecture",columnDefinition = "LONGBLOB")
    private String preliminary_architecture;
	 
	@Column(name="Project_Plan",columnDefinition = "LONGBLOB")
    private String project_plan;
	
	@Column(name="Project_risk",columnDefinition = "LONGBLOB")
    private String project_risk;
	
	@Column(name="Project_data",columnDefinition = "LONGBLOB")
    private String project_data;
    
    @Column(name="Superviosr_Comment",columnDefinition = "LONGBLOB")
    private String spComment;
    
    @Column(name="SecondMarker_Comment",columnDefinition = "LONGBLOB")
    private String smComment;
    
    @Column(name="Supervisor_Approve")
    private int spApprove;
    
    @Column(name="SecondMarker_Approve")
    private int smApprove;
    
    private int version;
    
    @Column(name="Submit_OR_Save")
    private String savetype;
    
    @Column(name="Submit_Blooean")
    private int submit;
    
    @Column(name="Submit_Time")
	private Date time;
	
	//Relationship
	@ManyToOne
	@JoinColumn(name = "Student_Id", referencedColumnName = "STUDENT_ID")
	private Students student;
	
	
	public Proposal(String project_title, String project_type, String project_aim, String project_relativeWork, 
			String project_objective, String project_methodology, String preliminary_architecture, 
			String project_risk, String project_data, Date time) {
		
		this.project_title = project_title;
		this.project_type = project_type;
		this.project_aim = project_aim;
		this.project_relativeWork =project_relativeWork;
		this.project_objective =project_objective;
		this.project_methodology =project_methodology;
		this.preliminary_architecture =preliminary_architecture;
		this.project_risk =project_risk;
		this.project_data =project_data;
		this.time =time;
	}
	
	public Proposal() {
		
	}
	
	//------Getter Setter -----
	public int getProposalID() {
		return proposalID;
	}

	public void setProposalID(int proposalID) {
		this.proposalID = proposalID;
	}

	public String getProject_type() {
		return project_type;
	}


	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}

	public String getProject_title() {
		return project_title;
	}

	public void setProject_title(String project_title) {
		this.project_title = project_title;
	}

	public String getProject_aim() {
		return project_aim;
	}

	public void setProject_aim(String project_aim) {
		this.project_aim = project_aim;
	}

	public String getProject_objective() {
		return project_objective;
	}

	public void setProject_objective(String project_objective) {
		this.project_objective = project_objective;
	}

	public String getProject_plan() {
		return project_plan;
	}

	public void setProject_plan(String project_plan) {
		this.project_plan = project_plan;
	}

	public String getProject_risk() {
		return project_risk;
	}

	public void setProject_risk(String project_risk) {
		this.project_risk = project_risk;
	}

	public String getProject_data() {
		return project_data;
	}

	public void setProject_data(String project_data) {
		this.project_data = project_data;
	}

	public Students getStudent() {
		return student;
	}
	
	public void setProject_relativeWork(String project_relativeWork) {
		this.project_relativeWork = project_relativeWork;
	}

	public String getProject_methodology() {
		return project_methodology;
	}

	public void setProject_methodology(String project_methodology) {
		this.project_methodology = project_methodology;
	}

	public String getPreliminary_architecture() {
		return preliminary_architecture;
	}

	public void setPreliminary_architecture(String preliminary_architecture) {
		this.preliminary_architecture = preliminary_architecture;
	}

	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	
	public String getSpComment() {
		return spComment;
	}

	public void setSpComment(String spComment) {
		this.spComment = spComment;
	}

	public String getSmComment() {
		return smComment;
	}

	public void setSmComment(String smComment) {
		this.smComment = smComment;
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

	
	public String getSavetype() {
		return savetype;
	}

	public void setSavetype(String savetype) {
		this.savetype = savetype;
	}

	//Relation
	public void setStudent(Students student) {
		this.student = student;
	}

	public String getProject_relativeWork() {
		return project_relativeWork;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
