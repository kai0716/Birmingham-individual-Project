package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.domain.Students;
import com.repositories.StudentRepository;


@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentRepository studentRepo;
	
	@RequestMapping("/")
	public String openStudentPage(@ModelAttribute("MainPage") Students stud, Model model) {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());

		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		return "student";
	}
}
