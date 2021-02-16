package com.spring.projectManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projectManagement.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

}
