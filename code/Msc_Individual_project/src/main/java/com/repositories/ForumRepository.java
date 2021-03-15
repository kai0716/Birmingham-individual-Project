package com.repositories;

import org.springframework.data.repository.CrudRepository;

import com.domain.Content;
import com.domain.Forum;

public interface ForumRepository extends CrudRepository<Forum, String>{
	Forum findByForumID(int id);
}
