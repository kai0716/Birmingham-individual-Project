package com.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.Students;

public interface StudentRepository extends CrudRepository<Students, String>{
	
	Students findByEmail(String email);
	Students findByStudentID(int id);
}
