package com.spring.projectManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projectManagement.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

}
