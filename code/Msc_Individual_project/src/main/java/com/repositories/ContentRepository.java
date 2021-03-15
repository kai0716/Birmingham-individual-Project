package com.repositories;

import org.springframework.data.repository.CrudRepository;

import com.domain.Content;

public interface ContentRepository extends CrudRepository<Content, String>{

}
