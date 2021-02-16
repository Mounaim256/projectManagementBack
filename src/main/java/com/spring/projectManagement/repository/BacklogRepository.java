package com.spring.projectManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projectManagement.model.Backlog;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Long> {

}
