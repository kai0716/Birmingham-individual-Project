package com.domain;

import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="Project_Type") 
public class ProjectType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false)
	private int id=-1;
	
	@Column(name="Type", nullable=false)
	private String type;
	
	//relationship
    @OneToMany(mappedBy = "projectType")
    private Set<Students> student;
    
    @OneToMany(mappedBy = "projectType")
    private Set<Supervisors> supervisor;
    
    public ProjectType() {
    	
    }
    
    // Getter/ Setter
    public ProjectType(String type) {
    	this.type = type;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	//Relationship 
	public Set<Students> getStudent() {
		return student;
	}
	public void setStudent(Set<Students> student) {
		this.student = student;
	}
	public Set<Supervisors> getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Set<Supervisors> supervisor) {
		this.supervisor = supervisor;
	}
    
    
}
