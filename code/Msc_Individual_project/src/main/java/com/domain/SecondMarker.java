package com.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Second_Marker")
public class SecondMarker {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Pair_ID", nullable=false)
	private int id;
	 
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Supervisor_Id")
	private Supervisors supervisor;

	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Student_Id")
	private Students student;
	
	@Column(name="Role")
	private String role;

	public SecondMarker() {
		
	}
	
	public SecondMarker(Students student, Supervisors supervisor, String role) {
		this.student = student;
		this.supervisor = supervisor;
		this.role =role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Supervisors getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisors supervisor) {
		this.supervisor = supervisor;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}	
}