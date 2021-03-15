package com.repositories;

import org.springframework.data.repository.CrudRepository;

import com.domain.ProjectType;

public interface TypeRepository  extends CrudRepository<ProjectType, String>{
	ProjectType findById(int id);
}
