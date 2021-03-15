package com.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="supervisors") 
public class Supervisors {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SUPERVISOR_ID", nullable=false)
	private int supervisorID=-1;
	
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
	
	//Relationship
	@OneToMany(mappedBy ="supervisor",fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<SecondMarker> secondMarker;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Project_Type", referencedColumnName = "ID")
    private ProjectType projectType;
	
	public Supervisors() {
	
	}	
	
	public Supervisors(String email, String password, String firstName, String lastName, int role, ProjectType projecttype) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role; 
		this.projectType =projecttype;
	}
	
	public int getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(int supervisorID) {
		this.supervisorID = supervisorID;
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
	
	// Relationship Getter Setter
	public Set<SecondMarker> getSecondMarker() {
		return secondMarker;
	}

	public void setSecondMarker(Set<SecondMarker> secondMarker) {
		this.secondMarker = secondMarker;
	}

	public ProjectType getProjectType() { 
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}
	
}
