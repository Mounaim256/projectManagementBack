package com.spring.projectManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projectManagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
