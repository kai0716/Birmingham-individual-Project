package com.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.domain.Proposal;
import com.domain.Students;
public interface ProposalRepository extends CrudRepository<Proposal, String>  {
	
	Proposal findByProposalID(int id);
	Proposal findByStudent(Students student);
	List<Proposal> findAllByStudent(Students student);
	Proposal findByversion(int version);
}
