package com.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.domain.Content;
import com.domain.Dto;
import com.domain.Forum;
import com.domain.Students;
import com.domain.Supervisors;
import com.domain.Tags;
import com.repositories.ContentRepository;
import com.repositories.ForumRepository;
import com.repositories.StudentRepository;
import com.repositories.SupervisorRepository;
import com.repositories.TagRepository;

@Controller
@RequestMapping("/")
public class DiscussionController {
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private TagRepository tagRepo;
	@Autowired
	private ForumRepository forumRepo;
	@Autowired
	private ContentRepository contentRepo;
	
	@RequestMapping(value = "/student/discussion", method = RequestMethod.GET)
	public String LoadForum(@ModelAttribute("Forums") Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());

		List<Forum> forumList =(List<Forum>) forumRepo.findAll();
		
		model.addAttribute("AllForum", forumList);
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);
		
		model.addAttribute("TagList", tagRepo.findAll());
		return "student-forum";
	}
	
	@RequestMapping(value = "/student/discussion/add", method = RequestMethod.POST)
	public String AddForum(@ModelAttribute("Forums") Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());

		Forum forum = new Forum(dto.getForum_title(),dto.getForum_detail(),new Date(),student);
		student.getForumList().add(forum);
		System.out.println("AAA");
		studentRepo.save(student);
		return "redirect:/student/discussion";
	}

	@RequestMapping(value = "/student/discussion/forum/{id}", method = RequestMethod.GET)
	public String EnterContent(@PathVariable int id, @ModelAttribute("Forums") Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());

		Forum forum =forumRepo.findByForumID(id);
		model.addAttribute("Forum", forum);
		model.addAttribute("Content", forum.getContentList());
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);
		return "student-forum-content";
	}
	
	@RequestMapping(value = "/student/discussion/addContent", method = RequestMethod.POST)
	public String addComment(@ModelAttribute("Forums") Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());

		Forum forum =forumRepo.findByForumID(dto.getId());
		Content content = new Content(dto.getContent(),new Date());
		content.setStudent(student);
		content.setForum(forum);
		forum.getContentList().add(content);
		forumRepo.save(forum);
		
		model.addAttribute("Forum", forum);
		model.addAttribute("Content", forum.getContentList());
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);
		return "redirect:/student/discussion/forum/"+dto.getId();
	}
	
	@RequestMapping(value ="/student/discussion/tag/{id}", method= RequestMethod.GET)
	public String search(@PathVariable int id, @ModelAttribute("Forums") Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());

		List<Forum> forumList =(List<Forum>) forumRepo.findAll();
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);
		
		model.addAttribute("TagList", tagRepo.findAll());
		
		Tags tag = tagRepo.findByTagID(id);
		List<Forum> fl = new ArrayList<>();
		for(Students s: tag.getStudentList()) {
			fl.addAll(s.getForumList());
		}
		model.addAttribute("AllForum", fl);
		return "student-forum";
	}
}
