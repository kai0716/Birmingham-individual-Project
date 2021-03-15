//package com.domain;
//
//import java.util.Set;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="roles")
//public class Role {
//	@Id
//	private int id;
//	@Column(nullable=false)
//	private String role;
//	
////---relationship----	
//	@OneToMany(mappedBy="role_student")
//	private Set<Students> students;
//	
//	@OneToMany(mappedBy="role_supervisor")
//	private Set<Supervisors> supervisors;
//	
//	
//// ---Constructor----	
//	public Role() { }
//	
//	public Role(int id, String role) {
//		this.id = id; 
//		this.role = role; 
//	}
//	
//// ---Getter Setter---
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//	
//	
//// ---Getter Setter for relational data---
//
//	public Set<Students> getStudents() {
//		return students;
//	}
//
//	public void setStudents(Set<Students> students) {
//		this.students = students;
//	}
//
//	public Set<Supervisors> getSupervisors() {
//		return supervisors;
//	}
//
//	public void setSupervisors(Set<Supervisors> supervisors) {
//		this.supervisors = supervisors;
//	}
//	
//	
//}