package com.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.domain.ProjectType;
import com.domain.Proposal;
import com.domain.SecondMarker;
import com.domain.Students;
import com.domain.Tags;
import com.repositories.ProjectTypeRepository;
import com.repositories.ProposalRepository;
import com.repositories.SecondMarkerRepository;
import com.repositories.StudentRepository;
import com.repositories.SupervisorRepository;
import com.repositories.TagRepository;
import com.repositories.TypeRepository;

@Controller
@RequestMapping("/")
public class ProposalController {
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private ProposalRepository proposalRepo;
	@Autowired
	private TypeRepository typeRepo;
	@Autowired
	private SecondMarkerRepository smRepo;
	@Autowired
	private SupervisorRepository spRepo;
	@Autowired
	private ProjectTypeRepository ptRepo;
	@Autowired
	private TagRepository tagRepo;
	
	@RequestMapping(value ="/student/proposal", method= RequestMethod.GET)
	public String ViewProposalPage(@ModelAttribute("Student_Proposal_Page") Students stud, Model model) {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
		
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		
		//proposal already exist
		List <Proposal>proposalList =proposalRepo.findAllByStudent(student);
		if(!proposalList.isEmpty()) {
			for(Proposal save: proposalList) {
				if(save.getSavetype().equals("save")) {
					model.addAttribute("Proposal", save);
					model.addAttribute("Empty", 0);
					break;
				}
			}

		}			
		//not exist
		else {
			model.addAttribute("Empty", 1);
		}
		
		return "student-proposal";
	}
	
	@RequestMapping(value ="/student/proposal/edit", method= RequestMethod.GET)
	public String LoadEditProposalPage(@ModelAttribute("Edit_Propsal") Proposal ps, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());	
		
		String rt = "student-proposal-write";
		List<ProjectType> type = (List<ProjectType>) typeRepo.findAll();
		List<Proposal> proposalList = proposalRepo.findAllByStudent(student);		
		//Proposal exist, go to edit
		if(!proposalList.isEmpty()) {
			Proposal proposal = new Proposal();
			Proposal submittedProposal = new Proposal();
			for(Proposal save: proposalList) {
				if(save.getSavetype().equals("save")) {
					proposal = proposalRepo.findByProposalID(save.getProposalID());
					break;
				}
			}	
			 
			
			 for(ProjectType p: type) {
				 if(p.getType().equals(proposal.getProject_type())) {
					 type.remove(p);
					 type.add(0, p);
					 break;
				 }
			 }
			 
			for(Proposal p: proposalList){
				if(p.getVersion() - student.getSubmitVersion()==0) {
					submittedProposal=p;
					break;
				}
			} 
			model.addAttribute("ProjectType", type);
			model.addAttribute("Proposal", proposal);
			model.addAttribute("SubmitProposal", submittedProposal);
			model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
			rt = "student-proposal-edit";
			System.out.println("Edit Proposal");
			
		}
		//not exist go to write
		else {
			Proposal proposal = new Proposal();
			proposal.setSavetype("save");
			proposal.setStudent(student);
			proposalRepo.save(proposal);
			
			model.addAttribute("ProjectType", type);
			model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
			rt= "student-proposal-write";
			System.out.println("Write Proposal");
		}
		
		return rt;
	}
	
	@RequestMapping(value ="/student/proposal/update", params = "submit_proposal", method= RequestMethod.POST) 
	public String SubmitProposal(@ModelAttribute("Edit_Propsal") Proposal ps, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
		
		List <Proposal>proposalList =proposalRepo.findAllByStudent(student);
		
		//Store Proposal
		Proposal proposal = new Proposal(ps.getProject_title(),ps.getProject_type(),ps.getProject_aim().replace("\n", "&").replace("\r", "%"),ps.getProject_relativeWork().replace("\n", "&").replace("\r", "%"),
				ps.getProject_objective().replace("\n", "&").replace("\r", "%"),ps.getProject_methodology().replace("\n", "&").replace("\r", "%"),
				ps.getPreliminary_architecture().replace("\n", "&").replace("\r", "%"), ps.getProject_risk().replace("\n", "&").replace("\r", "%"), ps.getProject_data().replace("\n", "&").replace("\r", "%"), new Date());
		
		
		//Get the latest proposal
		int temp =0;
		if(!proposalList.isEmpty()) {			
			
			temp = student.getSubmitVersion()+1;
			proposal.setVersion(temp);
			
			//Data of View proposal page (for redirection)
			//proposal exist
			model.addAttribute("Proposal", proposal);
			model.addAttribute("Empty", 0);
		}
		else {
			proposal.setVersion(0);
			temp=0;
			System.out.println("Error: Line 144 proposalController");
			model.addAttribute("Empty", 0);
		}
		
		
		for(Proposal save: proposalList) {
			if(save.getSavetype().equals("save")) {
				save.setProject_title(ps.getProject_title());
				save.setProject_aim(ps.getProject_aim().replace("\n", "&").replace("\r", "%"));
				save.setProject_type(ps.getProject_type());
				save.setProject_relativeWork(ps.getProject_relativeWork().replace("\n", "&").replace("\r", "%"));
				save.setProject_objective(ps.getProject_objective().replace("\n", "&").replace("\r", "%"));
				save.setProject_methodology(ps.getProject_methodology().replace("\n", "&").replace("\r", "%"));
				save.setPreliminary_architecture(ps.getPreliminary_architecture().replace("\n", "&").replace("\r", "%"));
				save.setProject_risk(ps.getProject_risk().replace("\n", "&").replace("\r", "%"));
				save.setProject_data(ps.getProject_data().replace("\n", "&").replace("\r", "%"));
				save.setTime(new Date());
				proposalRepo.save(save);
				break;
			}
		}
		
		proposal.setSubmit(1);
		proposal.setSmApprove(0);
		proposal.setSpApprove(0);
		student.setSubmit(1);
		student.setProjectType(ptRepo.findByType(proposal.getProject_type()));
		student.setSubmitVersion(temp);
		student.setSmApprove(0);
		student.setSpApprove(0);
		proposal.setStudent(student);
		proposalRepo.save(proposal); 
		

		Tags tag =tagRepo.findByTag(student.getProjectType().getType());
		Tags newtag =tagRepo.findByTag(ps.getProject_type());
		if(student.getTagList().contains(tag)) {
			student.getTagList().remove(tag);
			tag.getStudentList().remove(student);
			student.getTagList().add(newtag);
			newtag.getStudentList().add(student);
			studentRepo.save(student);
		}
		else {
			student.getTagList().add(newtag);
			newtag.getStudentList().add(student);
			studentRepo.save(student);
		}
		
		
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		return "student-proposal";
	}
	
	@RequestMapping(value ="/student/proposal/update", params = "save", method= RequestMethod.POST) 
	public String SaveProposal(@ModelAttribute("Edit_Propsal") Proposal ps, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
	
		List <Proposal>proposalList =proposalRepo.findAllByStudent(student);
		if(!proposalList.isEmpty()) {				

			for(Proposal save: proposalList) {
				if(save.getSavetype().equals("save")) {
					save.setProject_title(ps.getProject_title());
					save.setProject_aim(ps.getProject_aim().replace("\n", "&").replace("\r", "%"));
					save.setProject_type(ps.getProject_type());
					save.setProject_relativeWork(ps.getProject_relativeWork().replace("\n", "&").replace("\r", "%"));
					save.setProject_objective(ps.getProject_objective().replace("\n", "&").replace("\r", "%"));
					save.setProject_methodology(ps.getProject_methodology().replace("\n", "&").replace("\r", "%"));
					save.setPreliminary_architecture(ps.getPreliminary_architecture().replace("\n", "&").replace("\r", "%"));
					save.setProject_risk(ps.getProject_risk().replace("\n", "&").replace("\r", "%"));
					save.setProject_data(ps.getProject_data().replace("\n", "&").replace("\r", "%"));
					proposalRepo.save(save);
					
					//Data of View proposal page (for redirection)
					//proposal exist
					model.addAttribute("Proposal", save);
					model.addAttribute("Empty", 0);
					break;
				}
			}
		}
		Tags tag =tagRepo.findByTag(student.getProjectType().getType());
		Tags newtag =tagRepo.findByTag(ps.getProject_type());
		if(student.getTagList().contains(tag)) {
			student.getTagList().remove(tag);
			tag.getStudentList().remove(student);
			student.getTagList().add(newtag);
			newtag.getStudentList().add(student);
			studentRepo.save(student);
		}
		else {
			student.getTagList().add(newtag);
			newtag.getStudentList().add(student);
			studentRepo.save(student);
		}
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		return "student-proposal";
	}
	
	@RequestMapping(value ="/student/proposal/status", method= RequestMethod.GET)
	public String ViewMarking(@ModelAttribute("Student_Proposal_Page") Students stud, Model model) {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
		
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		
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
		
	    model.addAttribute("Proposal", proposal);
		return "student-proposal-markingStatus";
	}
	@RequestMapping(value ="/student/proposal/status/viewProposal/{id}", method= RequestMethod.GET)
	public String Proposals(@PathVariable int id, @ModelAttribute("Student_Proposal_Page") Students stud, Model model) {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
		
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		
		Proposal proposal = proposalRepo.findByProposalID(id);
		
	    model.addAttribute("Proposal", proposal);
		return "student-proposal-comment";
	}
}