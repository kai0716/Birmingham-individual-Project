package com.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.MscIndividualProjectApplication;
import com.domain.Dto;
import com.domain.Response;
import com.domain.Students;
import com.domain.Supervisors;
import com.repositories.StudentRepository;
import com.repositories.SupervisorRepository;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	SupervisorRepository supervisorRepo;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String openLogin(@ModelAttribute("loadLogin")Dto dto, Model model, Principal Users ) {
		
		if(Users !=null) {
			return "loginError";
		}
		return "login";
	}
	
    @RequestMapping(value="/loginValidate", method = RequestMethod.POST)
	public  @ResponseBody Response LoginValidate(@ModelAttribute("userLogin")Dto dto, BindingResult result, Model model) {
        Response res = new Response();
      
   	 	BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();
   	 	
   	 	Students student = studentRepo.findByEmail(dto.getEmail());
   	 	Supervisors supervisor= supervisorRepo.findByEmail(dto.getEmail());

   	 	if(student != null || supervisor != null) {
   	 		if(student != null) {
	   	 		if(!pe.matches(dto.getPassword(), student.getPassword()) && !dto.getPassword().isEmpty()) {
	   	   	 		result.reject("password is incorrect");
	   	   	 	}
	   		   	if(dto.getPassword().isEmpty()) {
	   	   	 		result.reject("Please enter your password");
	   		   	}	
   	 		}
   	 		if(supervisor != null) {
	   	 		if(!pe.matches(dto.getPassword(), supervisor.getPassword()) && !dto.getPassword().isEmpty()) {
	   	   	 		result.reject("password is incorrect");
	   	   	 	}
	   		   	if(dto.getPassword().isEmpty()) {
	   	   	 		result.reject("Please enter your password");
	   		   	}	
   	 		}
   	 	}
   	 	else {
   	 		result.reject("Invalide email.");
   	 	}
   	 	if(dto.getEmail().isEmpty()) {
   	 		result.reject("Please enter an email");
   	 	}
   	 	
   	 	
   	 	
		if(!result.hasErrors()) {
			res.setStatus("SUCCESS");
		}
		else{
			res.setStatus("FAIL");
			res.setResult(result.getAllErrors());
		}

		return res;
    }
	
    @RequestMapping(value = "/success-login", method = RequestMethod.GET)
    public String authenticate() {

    	User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		authUser.getAuthorities().stream().forEach(c -> System.out.println (c));

		System.out.println("logging in as " + authUser.getUsername());
        
		String view = "welcome";
        
		if(studentRepo.findByEmail(authUser.getUsername()) !=null) {
			view = "redirect:/student/";
		}
		if(supervisorRepo.findByEmail(authUser.getUsername()) !=null) {
			view = "redirect:/supervisor/myStudent";
		}
		
		
		return view;
		}
    
    @RequestMapping("/access-denied")
    public String accessDenied() {
    	return "login";
    }
    

}
