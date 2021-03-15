package com.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.domain.Dto;
import com.domain.Proposal;
import com.domain.Students;
import com.domain.Supervisors;
import com.domain.Tags;
import com.domain.Week;
import com.repositories.PlanRepository;
import com.repositories.StudentRepository;
import com.repositories.SupervisorRepository;
import com.repositories.TagRepository;
import com.repositories.WeekRepository;

@Controller
@RequestMapping("/")
public class PeopleController { 
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private SupervisorRepository spRepo;
	@Autowired
	private PlanRepository planRepo;
	@Autowired
	private WeekRepository weekRepo;
	@Autowired
	private TagRepository tagRepo;
	
	@RequestMapping(value ="/student/people", method= RequestMethod.GET)
	public String OpenPeoplePage(@ModelAttribute("People") Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
		
		List<Students> studentList = (List<Students>) studentRepo.findAll();
		List<Supervisors> supertList = (List<Supervisors>) spRepo.findAll();
		List<Tags> tagList = (List<Tags>) tagRepo.findAll();
		
	    Collections.sort(tagList, new Comparator<Tags>() {
			@Override
			public int compare(Tags t1, Tags t2) {
				// TODO Auto-generated method stub
				return Integer.compare(t2.getStudentList().size(), t1.getStudentList().size());
			}
	    });
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);
		model.addAttribute("StudentList", studentList);
		model.addAttribute("SupervisorList", supertList);
		model.addAttribute("TagList", tagList);
		return "student-people";
	}
	@RequestMapping(value ="/student/people/{id}", method= RequestMethod.GET)
	public String search(@PathVariable int id, @ModelAttribute("People") Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
		
		List<Students> studentList = (List<Students>) studentRepo.findAll();
		List<Supervisors> supertList = (List<Supervisors>) spRepo.findAll();
		List<Tags> tagList = (List<Tags>) tagRepo.findAll();
		
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);

		model.addAttribute("SupervisorList", supertList);
		model.addAttribute("TagList", tagList);
		
		Tags tag = tagRepo.findByTagID(id);
		model.addAttribute("StudentList", tag.getStudentList());
		return "student-people";
	}
	@RequestMapping(value ="/student/others/proposal/{id}", method= RequestMethod.GET)
	public String othersProposal(@PathVariable int id, @ModelAttribute("People") Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
		
		Students findstudent =  studentRepo.findByStudentID(id);
		Proposal proposal = new Proposal();
		for(Proposal p: findstudent.getProposal()) {
			if(p.getSmApprove()==1 && p.getSpApprove()==1) {
				proposal=p;
				break;
			}
		}
		
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);
		model.addAttribute("OtherStudent", findstudent);
		model.addAttribute("Proposal", proposal);
		
		return "student-other-proposal";
	}
	@RequestMapping(value ="/student/others/plan/{id}", method= RequestMethod.GET)
	public String othersPlan(@PathVariable int id, @ModelAttribute("People") Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
		
		Students findstudent =  studentRepo.findByStudentID(id);
		PlanController pc= new PlanController();
		if(findstudent.getWeeks().size()>1) {
		model.addAttribute("Weeks", pc.sortWeekList(findstudent.getWeeks()));
		}
		
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);
		model.addAttribute("OtherStudent", findstudent);

		return "student-other-plan";
	}
	
}
