package com.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.domain.Students;
import com.domain.Supervisors;
import com.domain.Tags;


public interface TagRepository  extends CrudRepository<Tags, String>{
	Tags findByTagID(int id);
	Tags findByTag(String tag);
}
