package com.spring.projectManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projectManagement.model.Epic;

@Repository
public interface EpicRepository extends JpaRepository<Epic, Long> {

}
