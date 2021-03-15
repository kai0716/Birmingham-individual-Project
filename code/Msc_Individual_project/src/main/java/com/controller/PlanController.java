package com.controller;

import java.util.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.domain.Dto;
import com.domain.Plan;
import com.domain.Proposal;
import com.domain.Students;
import com.domain.Week;
import com.repositories.PlanRepository;
import com.repositories.StudentRepository;
import com.repositories.WeekRepository;

@Controller
@RequestMapping("/student")
public class PlanController {
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private PlanRepository planRepo;
	@Autowired
	private WeekRepository weekRepo;
	
	@RequestMapping(value ="/plan", method= RequestMethod.GET)
	public String OpenPlanMainPage(@ModelAttribute("Student_Plan_Page") Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
		
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);
		
		if(student.getWeeks().size()>1) {
		model.addAttribute("Weeks", sortWeekList(student.getWeeks()));
		}
		
		List<Week>weekList =weekRepo.findAllByStudent(student);
		if(weekList.isEmpty()) {
			Week icebox = new Week(); 
			icebox.setType("iceBox");
			icebox.setStudent(student);
			weekRepo.save(icebox);
		}
		model.addAttribute("icebox", getIcebox(student.getWeeks()));
		return "student-plan";
	}
	
	@RequestMapping(value ="/plan", params="week", method= RequestMethod.POST)
	public String PlanAddWeek(@Valid @ModelAttribute("Student_Plan_Page")Dto dto, Model model) {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername()); 
		
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student); 

		if(student.getSelectedDate()==null) {
			int i=0;
			Date selectedDate = dto.getSelectedDate();
		    Calendar c = Calendar.getInstance();
		    c.setTime(selectedDate);
			while(i<dto.getWeeknumber()) {
			    Date d = c.getTime();
				List<Date> sed =start_end_date(d);
				Date startdate =sed.get(0);
				Date enddate =sed.get(1);
			    c.add(Calendar.WEEK_OF_MONTH, 1);

				student.getWeeks().add(new Week(student, startdate, enddate));
				i++;
			}
			student.setWeeknumber(dto.getWeeknumber());
			student.setSelectedDate(dto.getSelectedDate());
			studentRepo.save(student);
		}
		else {
			if (student.getWeeknumber() < dto.getWeeknumber()) {
				int i = 0;
				int add = dto.getWeeknumber() - student.getWeeknumber();
				Calendar c = Calendar.getInstance();
				Date newDate = student.getWeeks().get(student.getWeeks().size() - 1).getStartDate();
				c.setTime(newDate);
				c.add(Calendar.WEEK_OF_MONTH, 1);
				Date d = c.getTime();
				while (i < add) {
					List<Date> sed = start_end_date(d);
					Date startdate = sed.get(0);
					Date enddate = sed.get(1);
					c.add(Calendar.WEEK_OF_MONTH, 1);
					d = c.getTime();

					student.getWeeks().add(new Week(student, startdate, enddate));
					i++;
				}

				student.setWeeknumber(dto.getWeeknumber());
				studentRepo.save(student);
			}
			
			if(student.getWeeknumber()>dto.getWeeknumber()){
				int i=0;
				int minus= student.getWeeknumber()-dto.getWeeknumber();
				while(i<minus) {
					int index = student.getWeeks().size()-1;
					Week removeWeek = weekRepo.findByWeekID(student.getWeeks().get(index).getWeekID());
					Week icebox = getIcebox(student.getWeeks());
					if(!removeWeek.getPlan().isEmpty()) {
						List <Plan> plan = new ArrayList<>();
						for(Plan p: removeWeek.getPlan()) {
							p.setStatus("Unstarted");
							p.setWeek(icebox);
							planRepo.save(p);
							plan.add(p);
							
						}
						removeWeek.getPlan().removeAll(plan);
						weekRepo.save(removeWeek);
					}
					
					student.getWeeks().remove(removeWeek);
					studentRepo.save(student); 
					weekRepo.delete(removeWeek);
					i++; 
					
				}
			}
			if(student.getSelectedDate()!= dto.getSelectedDate()) {
				Calendar c = Calendar.getInstance();
				c.setTime(dto.getSelectedDate());
				Date d = c.getTime();
				for(Week w: student.getWeeks()) {
				    
					List<Date> sed =start_end_date(d);
					w.setStartDate(sed.get(0));
					w.setEndDate(sed.get(1));
					
					d =c.getTime();
					c.add(Calendar.WEEK_OF_MONTH, 1);
				}
				weekRepo.saveAll(student.getWeeks());
			}
			student.setWeeknumber(dto.getWeeknumber());
			student.setSelectedDate(dto.getSelectedDate());
			studentRepo.save(student);
		}
		if(student.getWeeks().size()>1) {
		model.addAttribute("Weeks", sortWeekList(student.getWeeks()));
		}
		model.addAttribute("icebox", getIcebox(student.getWeeks()));
		return "student-plan"; 
	}
	
	@RequestMapping(value ="/plan", params="addplan", method= RequestMethod.POST)
	public String AddPlan(@ModelAttribute("Student_Plan_Page")Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
		
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);
		model.addAttribute("Weeks", sortWeekList(student.getWeeks()));

		
		Week icebox = getIcebox(student.getWeeks());
		Plan plan = new Plan(dto.getTitle(), dto.getDescription().replace("\n", "&").replace("\r", "%"), dto.getDifficulty(), dto.getLink());
		plan.setStatus("Unstarted");
		plan.setWeek(icebox);

		icebox.getPlan().add(plan);
		weekRepo.save(icebox);
		
		model.addAttribute("icebox", icebox);
		return "student-plan";
	}
	@RequestMapping(value ="/plan", params="edit", method= RequestMethod.POST)
	public String EditPlan(@ModelAttribute("Student_Plan_Page")Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
		
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);
		model.addAttribute("Weeks", sortWeekList(student.getWeeks()));
		

		Plan plan = planRepo.findByPlanID(dto.getPlanID());
		plan.setStatus(dto.getStatus());
		plan.setTitle(dto.getTitle());
		plan.setDescription(dto.getDescription().replace("\n", "&").replace("\r", "%"));
		plan.setNotes(dto.getNote().replace("\n", "&").replace("\r", "%"));
		plan.setLink(dto.getLink());
		plan.setDifficulty(dto.getDifficulty());
		planRepo.save(plan);
		
		model.addAttribute("icebox", getIcebox(student.getWeeks())); 
		return "redirect:/student/plan";
	}
	
	@RequestMapping(value ="/plan", params="delete", method= RequestMethod.POST)
	public String DeletePlan(@ModelAttribute("Student_Plan_Page")Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
		
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);
		model.addAttribute("Weeks", sortWeekList(student.getWeeks()));
		

		for(int id: dto.getCheckBox()) {
			Plan delete =planRepo.findByPlanID(id);
			planRepo.delete(delete);
		}
		System.out.print(dto.getCheckBox().size());
		
		model.addAttribute("icebox", getIcebox(student.getWeeks())); 
		return "student-plan";
	}
	
	@RequestMapping(value ="/plan", params="transfer", method= RequestMethod.POST)
	public String TransferPlan(@ModelAttribute("Student_Plan_Page")Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
		
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);
		model.addAttribute("Weeks", sortWeekList(student.getWeeks())); 

		Week week = weekRepo.findByWeekID(dto.getTransferWeek());
		if(week.getType()!=null) {
			for (int id : dto.getCheckBox()) {
				Plan plan = planRepo.findByPlanID(id);
				plan.setWeek(week);
				plan.setStatus("UnStarted");
				
				planRepo.save(plan);
			}
		}
		else {
			for (int id : dto.getCheckBox()) {
				Plan plan = planRepo.findByPlanID(id);
				plan.setWeek(week);
				
				planRepo.save(plan);
			}
		}
		
		System.out.print("Transfer");
		
		model.addAttribute("icebox", getIcebox(student.getWeeks())); 
		return "student-plan";
	}
	
	@RequestMapping(value ="/plan/note", method= RequestMethod.GET)
	public String Note(@ModelAttribute("Student_Plan_Page")Dto dto, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Students student = studentRepo.findByEmail(user.getUsername());
		
		model.addAttribute("Student", student.getFirstName() + " " + student.getLastName());
		model.addAttribute("StudentObject", student);
		model.addAttribute("Weeks", sortWeekList(student.getWeeks())); 

		return "student-note";
	}
//-----------------------------Shared Method-----------------------	
	public List<Week> sortWeekList(List<Week> list) {
		List<Week> weekList = new ArrayList<>();
		for(Week w:list) {
			if(w.getType()==null) {
				weekList.add(w); 
			}
		}
		
	    Collections.sort(weekList, new Comparator<Week>() {
			@Override
			public int compare(Week w1, Week w2) {
				// TODO Auto-generated method stub
				return Integer.compare(w1.getWeekID(), w2.getWeekID());
			}
	    });
		return weekList;
	}
	
	//reference: w3resource.com/java-exercises/datetime/java-datetime-exercise-9.php
	public List<Date> start_end_date(Date selectedDate) {
		List<Date> dateList = new ArrayList<>();
	     Calendar cal = Calendar.getInstance();
	     cal.setTime(selectedDate);

	     // Set the calendar to monday of the current week
	     cal.setFirstDayOfWeek(Calendar.MONDAY);
	     cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

	     // Print dates of the current week starting on Monday

	    Date startDate = cal.getTime();
	     for (int i = 0; i <6; i++) {
	    	 cal.add(Calendar.DATE, 1);
	     }
	     Date endDate =cal.getTime();
	     dateList.add(startDate);
	     dateList.add(endDate);
		return  dateList;
	}
	public Week getIcebox (List<Week> list) {
		Week week = new Week();
		for(Week w : list) {
			if(w.getType()!=null) {
				week = w;
				break;
			}
		}
		return week;
	}
}

