package com.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.domain.SecondMarker;
import com.domain.Students;

public interface SecondMarkerRepository extends CrudRepository<SecondMarker, String> {
	SecondMarker findById(int id);
	List<SecondMarker> findAllByStudent(Students student);
}
