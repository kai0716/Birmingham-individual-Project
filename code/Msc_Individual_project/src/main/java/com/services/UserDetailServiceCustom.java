package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.MscIndividualProjectApplication;
import com.domain.Students;
import com.repositories.StudentRepository;
import com.repositories.SupervisorRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly=true)
public class UserDetailServiceCustom implements UserDetailsService{
	
	@Autowired
	private StudentRepository studentRepo;	

	@Autowired
	private SupervisorRepository supervisorRepo;	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		com.domain.Students domainStudent = studentRepo.findByEmail(email);
		com.domain.Supervisors domainSupervisor = supervisorRepo.findByEmail(email);
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		if(domainStudent !=null) {
			return new User(
					domainStudent.getEmail(), 
					domainStudent.getPassword(), 
					enabled, 
					accountNonExpired, 
					credentialsNonExpired, 
					accountNonLocked,
					getAuthorities(domainStudent.getRole())
			);
		}
		else {
			return new User(
					domainSupervisor.getEmail(), 
					domainSupervisor.getPassword(), 
					enabled, 
					accountNonExpired, 
					credentialsNonExpired, 
					accountNonLocked,
					getAuthorities(domainSupervisor.getRole())
			);
		}
//		String e_mail = null;
//		String password = null;
//		int roles = 0;
//		if(domainStudent !=null) {
//			e_mail = domainStudent.getEmail();
//			password = domainStudent.getPassword();
//			roles = domainStudent.getRole();
//		}
//		if(domainSupervisor !=null) {
//			e_mail = domainSupervisor.getEmail();
//			password = domainSupervisor.getPassword();
//			roles = domainSupervisor.getRole();
//		}
//		return new User(
//				e_mail, 
//				password, 
//				enabled, 
//				accountNonExpired, 
//				credentialsNonExpired, 
//				accountNonLocked,
//				getAuthorities(roles)
//		);
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}
	
	private List<String> getRoles(Integer role) {

		List<String> roles = new ArrayList<String>();

		switch (role.intValue()) {
			case MscIndividualProjectApplication.STUDENT: 
				roles.add("ROLE_STUDENT");
				break;	
			case MscIndividualProjectApplication.SUPERVISOR: 
				roles.add("ROLE_SUPERVISOR");
				break;
		}
		return roles;
	}

	private static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}
