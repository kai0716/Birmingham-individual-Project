package com.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.domain.Plan;
import com.domain.Students;

public interface PlanRepository extends CrudRepository<Plan, String>{
	Plan findByPlanID(int id);
}
