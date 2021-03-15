package com.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.domain.Dto;
import com.domain.Students;
import com.domain.Supervisors;
import com.domain.Tags;
import com.repositories.StudentRepository;
import com.repositories.SupervisorRepository;
import com.repositories.TagRepository;

@Controller
@RequestMapping("/")
public class TagController {
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private SupervisorRepository spRepo;
	@Autowired
	private TagRepository tagRepo;

	@RequestMapping(value = "/student/tags", method = RequestMethod.GET)
	public String StudentTags(@ModelAttribute("GetTags") Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());

		List<Tags> tagList = student.getTagList();
		if(tagList.isEmpty()) {
			if(student.getProjectType()!=null) {
			Tags tag =tagRepo.findByTag(student.getProjectType().getType());
			student.getTagList().add(tag);
			tag.getStudentList().add(student);
			studentRepo.save(student); 
			}
		}
		model.addAttribute("Tags", tagList);
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);
		model.addAttribute("ProjectType", student.getProjectType());

		return "student-tags";
	}

	@RequestMapping(value = "/student/tags", params = "addTag", method = RequestMethod.POST)
	public String AddTag(@ModelAttribute("GetTags") Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());

		List<String> splited = Arrays.asList(dto.getTag().split(" "));

		List<Tags> tagList = (List<Tags>) tagRepo.findAll();
		List<Tags> tempList = new ArrayList<>();
		if (tagList.size()>0) {
			for (String t : splited) {
				int has = 0;
				for (Tags check : tagList) {
					if (check.getTag().equals(t)) {
						has++;
						if(!check.getStudentList().contains(student)) {
						check.getStudentList().add(student);
						student.getTagList().add(check);
						}
						model.addAttribute("Repeat", "true");
						break;
					}
				}
				if(has==0) {
					Tags tag = new Tags();
					tag.getStudentList().add(student);
					tag.setTag(t);
					tempList.add(tag);
					model.addAttribute("Repeat", "false");
				}
			}
			studentRepo.save(student);
			tagRepo.saveAll(tempList);
		} else {
			for (String t : splited) {
				Tags tag = new Tags();
				tag.getStudentList().add(student);
				tag.setTag(t);
				tagRepo.save(tag);
			}
		}
		model.addAttribute("Tags", getTagList(student));
		return "redirect:/student/tags";
	}

	@RequestMapping(value = "/student/tags", params = "deleteTag", method = RequestMethod.POST)
	public String DeleteTag(@ModelAttribute("GetTags") Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());

		List<Tags> tagList = new ArrayList<>();
		List<Tags> tagRemove = new ArrayList<>();
		for (Integer id : dto.getTagID()) {
			Tags tag = tagRepo.findByTagID(id);
			student.getTagList().remove(tag);
			tag.getStudentList().remove(student);
			
			if(tag.getStudentList().isEmpty()) {
				tagRemove.add(tag);
				System.out.print("No other student under to this tag, delete tag");
			}
			else {
				tagList.add(tag);
				System.out.print("Keep Tag");
			}
		}
		studentRepo.save(student);
		tagRepo.saveAll(tagList);
		tagRepo.deleteAll(tagRemove);
		
		model.addAttribute("Tags", student.getTagList());

		return "redirect:/student/tags";
	}
	
	public List<Tags> getTagList(Students student) {
		return student.getTagList();
	}
}
