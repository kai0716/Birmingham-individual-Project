package com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.File;

public interface FileRepository extends JpaRepository<File, String> {


}
