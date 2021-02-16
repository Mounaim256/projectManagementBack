package com.spring.projectManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projectManagement.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
