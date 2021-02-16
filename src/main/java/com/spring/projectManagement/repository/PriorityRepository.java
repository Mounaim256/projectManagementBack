package com.spring.projectManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projectManagement.model.Priority;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

}
