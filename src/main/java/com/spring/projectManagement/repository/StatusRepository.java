package com.spring.projectManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projectManagement.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
