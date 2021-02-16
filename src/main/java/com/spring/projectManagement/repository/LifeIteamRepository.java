package com.spring.projectManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projectManagement.model.LifeIteam;

@Repository
public interface LifeIteamRepository extends JpaRepository<LifeIteam, Long> {

}
