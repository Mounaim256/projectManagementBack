package com.spring.projectManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projectManagement.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	public Optional<Project> findByName(String name);
}
