package com.repositories;

import org.springframework.data.repository.CrudRepository;

import com.domain.ProjectType;
import com.domain.Proposal;

public interface  ProjectTypeRepository extends CrudRepository<ProjectType, String>{
	ProjectType findByType(String pt);
}
