package com.repositories;

import org.springframework.data.repository.CrudRepository;


import com.domain.Supervisors;

public interface SupervisorRepository extends CrudRepository<Supervisors, String>{
	Supervisors findByEmail(String email);
}
