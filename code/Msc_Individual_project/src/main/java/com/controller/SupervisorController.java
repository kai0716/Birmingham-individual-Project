package com.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.domain.Plan;
import com.domain.Proposal;
import com.domain.SecondMarker;
import com.domain.Students;
import com.domain.Supervisors;
import com.domain.Week;
import com.repositories.ProposalRepository;
import com.repositories.StudentRepository;
import com.repositories.SupervisorRepository;

@Controller
@RequestMapping("/supervisor")
public class SupervisorController {
	@Autowired
	private SupervisorRepository spRepo;
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private ProposalRepository proposalRepo;
	
//	@RequestMapping("/")
//	public String openSupervisorPage(@ModelAttribute("MainPage") Supervisors superv, Model model) {
//		
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		Supervisors supervisor = spRepo.findByEmail(user.getUsername());
//
//		model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
//		return "supervisor";
//	}

	@RequestMapping("/myStudent")
	public String openMyStudentPage(@ModelAttribute("myStudent") Supervisors superv, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Supervisors supervisor = spRepo.findByEmail(user.getUsername());
		List<Students> studentList_SP = new ArrayList<>();
		List<Students> studentList_SM = new ArrayList<>();
		
		Set<SecondMarker> relation = supervisor.getSecondMarker();
		for(SecondMarker r: relation) {
			if(r.getRole().equals("Supervisor")) {
				
				Students student = r.getStudent();
				studentList_SP.add(student);
			}
			else {
				Students student = r.getStudent();
				studentList_SM.add(student);
			}
		}
	    Collections.sort(studentList_SP, new Comparator<Students>() {
			@Override
			public int compare(Students w1, Students w2) {
				
				// TODO Auto-generated method stub
				return w1.getLastName().compareTo(w2.getLastName());
			}
	    });
	    Collections.sort(studentList_SM, new Comparator<Students>() {
			@Override
			public int compare(Students w1, Students w2) {
				
				// TODO Auto-generated method stub
				return w1.getLastName().compareTo(w2.getLastName());
			}
	    });
		model.addAttribute("StudentList_SP", studentList_SP);
		model.addAttribute("StudentList_SM", studentList_SM);
		model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
		return "supervisor-student-table";
	}
	
//---------------- Supervisor plan section-----------------------
	@RequestMapping("/myStudent/viewPlan/{id}")
	public String ViewStudentPlan_SP(@PathVariable int id, @ModelAttribute("viewPlan") Plan plan, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Supervisors supervisor = spRepo.findByEmail(user.getUsername());
		
		Students student = studentRepo.findByStudentID(id);
		model.addAttribute("StudentObject", student);
		
		PlanController pc= new PlanController();
		if(student.getWeeks().size()>1) {
		model.addAttribute("Weeks", pc.sortWeekList(student.getWeeks()));
		}

		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("marker", "SP");
		model.addAttribute("ID", id);
		model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
		return "supervisor-viewPlan";
	}
}
