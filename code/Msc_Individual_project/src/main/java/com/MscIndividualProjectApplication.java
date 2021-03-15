package com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.domain.ProjectType;
import com.domain.SecondMarker;
import com.domain.Students;
import com.domain.Supervisors;
import com.domain.Tags;
import com.repositories.SecondMarkerRepository;
import com.repositories.StudentRepository;
import com.repositories.SupervisorRepository;
import com.repositories.TagRepository;
import com.repositories.TypeRepository;

@SpringBootApplication
public class MscIndividualProjectApplication implements WebMvcConfigurer, CommandLineRunner{
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	SupervisorRepository superRepo;
	@Autowired
	SecondMarkerRepository smRepo;
	@Autowired
	TypeRepository typeRepo;
	@Autowired
	TagRepository tagRepo;
	
	public final static int STUDENT = 1;
	public final static int SUPERVISOR = 2;
	
	
	public static void main(String[] args) {
		SpringApplication.run(MscIndividualProjectApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
	
	ProjectType type1 = new ProjectType("Software Engineering");
	ProjectType type2 = new ProjectType("Computer Security and Cryptography");
	ProjectType type3 = new ProjectType("Theoretical Computer Science");

	
	Tags tag1 = new Tags("Software Engineering");
	Tags tag2 = new Tags("Computer Security and Cryptography");
	Tags tag3 = new Tags("Theoretical Computer Science");
	tagRepo.save(tag1);
	tagRepo.save(tag2);
	tagRepo.save(tag3);
	
	Students student1 = new Students("001@qq.com",bcpe.encode("1"),"Kai","Zhang",STUDENT);
	Students student2 = new Students("002@qq.com",bcpe.encode("1"),"Student","Two",STUDENT);
	Students student3 = new Students("003@qq.com",bcpe.encode("1"),"Student","Three",STUDENT);
	Students student4 = new Students("004@qq.com",bcpe.encode("1"),"Student","Four",STUDENT);
	Students student5 = new Students("005@qq.com",bcpe.encode("1"),"Student","Five",STUDENT);
	Students student6 = new Students("006@qq.com",bcpe.encode("1"),"Student","Six",STUDENT);

	Supervisors supervisor1 = new Supervisors("www@qq.com",bcpe.encode("1"),"Vincent","Rahil",SUPERVISOR, type3);
	Supervisors supervisor2 = new Supervisors("aaa@qq.com",bcpe.encode("1"), "Supervisor", "Two",SUPERVISOR, type2);
	Supervisors supervisor3 = new Supervisors("bbb@qq.com",bcpe.encode("1"),"Supervisor","Three",SUPERVISOR, type1);

	//supervisor's student list
	Set <SecondMarker> supervisor1_list = new HashSet<>();
	Set <SecondMarker> supervisor2_list = new HashSet<>();
	Set <SecondMarker> supervisor3_list = new HashSet<>();
	Set <SecondMarker> student1_list = new HashSet<>();
	Set <SecondMarker> student2_list = new HashSet<>();
	Set <SecondMarker> student3_list = new HashSet<>();
	Set <SecondMarker> student4_list = new HashSet<>();
	Set <SecondMarker> student5_list = new HashSet<>();
	Set <SecondMarker> student6_list = new HashSet<>();
	
// Setting student supervisors and second marker's relationship
	SecondMarker relation_sp1 =  new SecondMarker(student1, supervisor1, "Supervisor");
	SecondMarker relation_sp2 =  new SecondMarker(student2, supervisor3, "Supervisor");
	SecondMarker relation_sp3 =  new SecondMarker(student3, supervisor1, "Supervisor");
	SecondMarker relation_sp4 =  new SecondMarker(student4, supervisor2, "Supervisor");
	SecondMarker relation_sp5 =  new SecondMarker(student5, supervisor1, "Supervisor");
	SecondMarker relation_sp6 =  new SecondMarker(student6, supervisor2, "Supervisor");
	
	SecondMarker relation_sm1 =  new SecondMarker(student1, supervisor3, "SecondMarker");
	SecondMarker relation_sm2 =  new SecondMarker(student3, supervisor2, "SecondMarker");
	SecondMarker relation_sm3 =  new SecondMarker(student2, supervisor1, "SecondMarker");
	SecondMarker relation_sm4 =  new SecondMarker(student4, supervisor3, "SecondMarker");
	SecondMarker relation_sm5 =  new SecondMarker(student5, supervisor2, "SecondMarker");
	SecondMarker relation_sm6 =  new SecondMarker(student6, supervisor1, "SecondMarker");
	
	supervisor1_list.add(relation_sp1);
	supervisor1_list.add(relation_sp3);
	supervisor1_list.add(relation_sp5);
	supervisor1_list.add(relation_sm3);
	supervisor1_list.add(relation_sm6);
	supervisor1.setSecondMarker(supervisor1_list);

	supervisor2_list.add(relation_sp4);
	supervisor2_list.add(relation_sp6);
	supervisor2_list.add(relation_sm2);
	supervisor2_list.add(relation_sm5);
	supervisor2.setSecondMarker(supervisor2_list);
	
	supervisor3_list.add(relation_sp2);
	supervisor2_list.add(relation_sm4);
	supervisor3_list.add(relation_sm1);
	supervisor3.setSecondMarker(supervisor3_list);

	
	student1_list.add(relation_sp1);
	student1_list.add(relation_sm1);
	student1.setSecondMarker(student1_list);
	
	student2_list.add(relation_sp2);
	student2_list.add(relation_sm2);
	student2.setSecondMarker(student2_list);
	
	student3_list.add(relation_sp3);
	student3_list.add(relation_sm3);
	student3.setSecondMarker(student3_list);
	
	student4_list.add(relation_sp4);
	student4_list.add(relation_sm4);
	student4.setSecondMarker(student4_list);
	
	student5_list.add(relation_sp5);
	student5_list.add(relation_sm5);
	student5.setSecondMarker(student5_list);
	
	student6_list.add(relation_sp6);
	student6_list.add(relation_sm6);
	student6.setSecondMarker(student6_list);

	smRepo.save(relation_sp1);
	smRepo.save(relation_sp2);
	smRepo.save(relation_sp3);
	smRepo.save(relation_sp4);
	smRepo.save(relation_sp5);
	smRepo.save(relation_sp6);
	smRepo.save(relation_sm1);
	smRepo.save(relation_sm2);
	smRepo.save(relation_sm3);
	smRepo.save(relation_sm4);
	smRepo.save(relation_sm5);
	smRepo.save(relation_sm6);

	
// Save into repository
	System.out.println("T9:"+studentRepo.count());
	}
}

