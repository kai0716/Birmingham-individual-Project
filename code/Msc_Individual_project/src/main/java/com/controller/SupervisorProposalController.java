package com.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.domain.Proposal;
import com.domain.SecondMarker;
import com.domain.Students;
import com.domain.Supervisors;
import com.repositories.ProposalRepository;
import com.repositories.StudentRepository;
import com.repositories.SupervisorRepository;

@Controller
@RequestMapping("/supervisor/")
public class SupervisorProposalController {
	@Autowired
	private SupervisorRepository spRepo;
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private ProposalRepository proposalRepo;
	
	@RequestMapping("/myStudent/viewProposal/SP/{id}")
	public String ViewStudentProposal_SP(@PathVariable int id, @ModelAttribute("viewProposal") Proposal proposal, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Supervisors supervisor = spRepo.findByEmail(user.getUsername());
		
		Students student = studentRepo.findByStudentID(id);
		List<Proposal> proposalList = proposalRepo.findAllByStudent(student);
		if(!proposalList.isEmpty()) {
			for(Proposal p: proposalList) {
				if(p.getVersion() == student.getSubmitVersion()) {
					model.addAttribute("Proposal", p);
					model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
					break;
				}
				else {
					model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
				}
			}
		}
		else {
			model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
		}

		model.addAttribute("Student", student);
		model.addAttribute("marker", "SP");
		model.addAttribute("ID", id);
		return "supervisor-student-proposal";
	}
	
	@RequestMapping("/myStudent/viewProposal/SM/{id}")
	public String ViewStudentProposal_SM(@PathVariable int id, @ModelAttribute("viewProposal") Proposal proposal, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Supervisors supervisor = spRepo.findByEmail(user.getUsername());
		
		Students student = studentRepo.findByStudentID(id);
		List<Proposal> proposalList = proposalRepo.findAllByStudent(student);
		if(!proposalList.isEmpty()) {
			for(Proposal p: proposalList) {
				if(p.getVersion() == student.getSubmitVersion()) {
					model.addAttribute("Proposal", p);
					model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
					break;
				}
				else {
					model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
				}
			}
		}
		else {
			model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
		}

		model.addAttribute("Student", student);
		model.addAttribute("marker", "SM");
		model.addAttribute("ID", id);
		return "supervisor-student-proposal";
	}
	
	@RequestMapping(value ="/myStudent/viewProposal/SP/{id}", params = "SP_comment", method= RequestMethod.POST)
	public String Store_SP_Comment_reject(@PathVariable int id, @ModelAttribute("comment") Proposal p, Model model) {
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
		
		model.addAttribute("StudentList_SP", studentList_SP);
		model.addAttribute("StudentList_SM", studentList_SM);
		model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
		
		Proposal proposal =proposalRepo.findByProposalID(id);
		proposal.setSpComment(p.getSpComment().replace("\n", "&").replace("\r", "%"));
		proposal.setSpApprove(2);
		proposal.getStudent().setSubmit(2);
		proposal.getStudent().setSpApprove(2);
		proposalRepo.save(proposal);
		return "supervisor-student-table";
	}
	
	@RequestMapping(value= "/myStudent/viewProposal/SM/{id}", params = "SM_comment", method= RequestMethod.POST)
	public String Store_SM_Comment_reject(@PathVariable int id, @ModelAttribute("comment") Proposal p, Model model) {
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
		model.addAttribute("StudentList_SP", studentList_SP);
		model.addAttribute("StudentList_SM", studentList_SM);
		model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
		
		Proposal proposal =proposalRepo.findByProposalID(id);
		
		proposal.setSmComment(p.getSmComment().replace("\n", "&").replace("\r", "%"));
		proposal.setSmApprove(2);
		proposal.getStudent().setSubmit(2);
		proposal.getStudent().setSmApprove(2);
		proposalRepo.save(proposal);
		
		return "supervisor-student-table";
	}
	
	@RequestMapping(value= "/myStudent/sp_accept/{id}")
	public String SP_Accept(@PathVariable int id, @ModelAttribute("comment") Proposal p, Model model) {
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
		model.addAttribute("StudentList_SP", studentList_SP);
		model.addAttribute("StudentList_SM", studentList_SM);
		model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
		
		Proposal proposal =proposalRepo.findByProposalID(id);
		proposal.setSpApprove(1);
		proposal.getStudent().setSpApprove(1);
		proposalRepo.save(proposal);

		return "supervisor-student-table";
	}
	
	@RequestMapping(value= "/myStudent/sm_accept/{id}")
	public String SM_Accept(@PathVariable int id, @ModelAttribute("comment") Proposal p, Model model) {
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
		model.addAttribute("StudentList_SP", studentList_SP);
		model.addAttribute("StudentList_SM", studentList_SM);
		model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
		
		
		Proposal proposal =proposalRepo.findByProposalID(id);
		proposal.setSmApprove(1);
		proposal.getStudent().setSmApprove(1);
		proposalRepo.save(proposal);
		return "supervisor-student-table";
	}
	
	// Proposal history	------------------------------------------------------------------------
		@RequestMapping(value ="/proposal/status/{id}", method= RequestMethod.GET)
		public String ViewMarking(@PathVariable int id, @ModelAttribute("Student_Proposal_Page") Students stud, Model model) {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Supervisors supervisor = spRepo.findByEmail(user.getUsername());
			
			Students student = studentRepo.findByStudentID(id);
			
			List<Proposal> proposal = proposalRepo.findAllByStudent(student);
			for(int i=0; i<proposal.size(); i++) {
				if (proposal.get(i).getSavetype().equals("save")) {
					proposal.remove(proposal.get(i));
					break;
				}
			}
		    Collections.sort(proposal, new Comparator<Proposal>() {
				@Override
				public int compare(Proposal p1, Proposal p2) {
					// TODO Auto-generated method stub
					return Integer.compare(p1.getVersion(), p2.getVersion());
				}
		    });
		    model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
		    model.addAttribute("Proposal", proposal);
		    model.addAttribute("Student", student);
			return "supervisor-proposal-history";
		}
		@RequestMapping(value ="/proposal/status/viewProposal/{id}", method= RequestMethod.GET)
		public String Proposals(@PathVariable int id, @ModelAttribute("Student_Proposal_Page") Students stud, Model model) {
			
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Supervisors supervisor = spRepo.findByEmail(user.getUsername());
			
		    model.addAttribute("Supervisor", supervisor.getFirstName() + " " + supervisor.getLastName());
			
			Proposal proposal = proposalRepo.findByProposalID(id);
			
		    model.addAttribute("Proposal", proposal);
		    model.addAttribute("Student", proposal.getStudent());
			return "supervisor-proposal-history-comment";
		}
}
