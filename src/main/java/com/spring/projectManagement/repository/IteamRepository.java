package com.spring.projectManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.projectManagement.model.Iteam;

@Repository
public interface IteamRepository extends JpaRepository<Iteam, Long> {

}
