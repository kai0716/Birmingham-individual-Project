package com.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.domain.Students;
import com.domain.Week;

public interface WeekRepository extends CrudRepository<Week, Integer>{
	Week findByWeekID(int id);
	Week findByStudent(Students student);
	List<Week> findAllByStudent(Students student);
	Week findByType(String type);
}