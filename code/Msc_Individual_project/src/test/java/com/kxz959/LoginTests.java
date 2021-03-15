package com.kxz959;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import com.controller.ProposalController;
import com.domain.Plan;
import com.domain.Proposal;
import com.domain.SecondMarker;
import com.domain.Students;
import com.domain.Supervisors;
import com.repositories.PlanRepository;
import com.repositories.ProposalRepository;
import com.repositories.SecondMarkerRepository;
import com.repositories.StudentRepository;
import com.repositories.SupervisorRepository;
import com.repositories.TagRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginTests{

	@Autowired WebApplicationContext webApplicationContext; 
	private MockMvc mockMvc;
	
	@Autowired 
	ProposalRepository proposalRepo;
	@Autowired 
	StudentRepository studentRepo;
	@Autowired 
	SupervisorRepository superRepo;
	@Autowired 
	SecondMarkerRepository smRepo;
	@Autowired 
	PlanRepository planRepo;
	@Autowired 
	TagRepository tagRepo;
	@Test
	public void student_supervisor_create() throws Exception{
	    long pre = studentRepo.count() ;
	    long pre2 = superRepo.count();
	    long pre3 = smRepo.count();
	BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
	    // given
    Students student = new Students();
    String password ="Password";
    student.setFirstName("kai");
    student.setEmail("3@qq.com");
    student.setPassword(bcpe.encode(password));
    
    Supervisors s1 = new Supervisors();
    s1.setFirstName("S1");
    s1.setEmail("2@qq.com");
    s1.setPassword(bcpe.encode(password));
    
    Supervisors s2 = new Supervisors();
    s2.setFirstName("S2");
    s2.setEmail("1@qq.com");
    s2.setPassword(bcpe.encode(password));
    
	SecondMarker relation1 =  new SecondMarker(student, s1, "Supervisor");
	SecondMarker relation2 =  new SecondMarker(student, s2, "SecondMarker");
    
    Set<SecondMarker> studentlist = new HashSet<>();
    studentlist.add(relation1);
    studentlist.add(relation2);
    student.setSecondMarker(studentlist);
    
    Set<SecondMarker> s1list = new HashSet<>();
    s1list.add(relation1);
    s1.setSecondMarker(s1list);
    
    Set<SecondMarker> s2list = new HashSet<>();
    s2list.add(relation2);
    s2.setSecondMarker(s2list);
    
    smRepo.save(relation1);
    smRepo.save(relation2);
    
    // when
    long studentNumber = studentRepo.count() -pre;
    long supervisorNumber = superRepo.count() -pre2;
    long sm_repo =smRepo.count() -pre3;
    // then
    assertThat(studentNumber)
    .isEqualTo(1);
    assertThat(supervisorNumber)
    .isEqualTo(2);
    assertThat(sm_repo)
    .isEqualTo(2);
	}
	
	@Test
	public void StudentRepositoryTest() throws Exception{
	BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
	    // given
    Students kai = new Students();
    String password ="Password";
    kai.setFirstName("kai");
    kai.setEmail("abc@qq.com");
    kai.setPassword(bcpe.encode(password));
    studentRepo.save(kai);
 
    // when
    Students found = studentRepo.findByEmail(kai.getEmail());
 
    // then
    assertThat(found.getEmail())
    .isEqualTo(kai.getEmail());
	}
	
	@Test
	public void ProposalRepoTest() throws Exception{
	    // given
	long pre = proposalRepo.count() ;
	Proposal p = new Proposal();
	p.setProject_title("Project A");
	proposalRepo.save(p);
 
    // when
    Proposal found = proposalRepo.findByProposalID(1);
 
    // then
    assertThat(found.getProject_title())
    .isEqualTo(p.getProject_title());
    assertThat(proposalRepo.count()-pre)
    .isEqualTo(1);
	
	}
	@Test
	public void SupervisorRepoTest() throws Exception{
	    // given
		int pre =(int) superRepo.count() ;
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
	    Supervisors s = new Supervisors();
	    String password ="Password";
	    s.setFirstName("kai");
	    s.setEmail("4@qq.com");
	    s.setPassword(bcpe.encode(password));
	    superRepo.save(s);
	 
	    // when
	    Supervisors found = superRepo.findByEmail(s.getEmail());
	 
	    // then
	    assertThat(found.getEmail())
	    .isEqualTo(s.getEmail());
	    assertThat(superRepo.count()-pre)
	    .isEqualTo(1);
	}

@Test
public void PlanRepoTest() throws Exception{
		// given
		Plan p = new Plan();
		p.setDescription("Plan1");
		planRepo.save(p);
		
		// when
		Plan found = planRepo.findByPlanID(1);
		
		// then
		assertThat(found.getDescription())
		.isEqualTo(p.getDescription());
		assertThat(planRepo.count())
		.isEqualTo(1);

}
	
//	@Test
//	public void NoProposal() throws Exception{
//		Students student = studentRepo.findByEmail("001@qq.com");
//
//		Proposal proposal = new Proposal();
//		proposal.setSavetype("save");
//		proposal.setStudent(student);
//		List<Proposal> list= new ArrayList<>();
//		list.add(proposal);
//		student.setProposal(list);
//		proposalRepo.save(proposal);
//		
//		mockMvc.perform(get("/student/proposal"))
//		.andExpect(model().attribute("Empty", 1))
//		.andExpect(view().name("student-proposal"))
//		.andExpect(status().isOk());
//	}
//	@Test
//	public void HasProposal() throws Exception{
//		 mockMvc.perform(get("/")).andExpect(status().isOk())
//		.andExpect(view().name("welcome"));
//	}
}
