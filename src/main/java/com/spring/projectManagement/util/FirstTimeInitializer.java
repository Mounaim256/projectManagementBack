package com.spring.projectManagement.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.projectManagement.model.Project;
import com.spring.projectManagement.repository.ProjectRepository;

@Component
public class FirstTimeInitializer implements CommandLineRunner {
	
	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Optional<Project> project = projectRepository.findById(1L);
		if(!project.isPresent()) {
			
			Project project1 = new Project(null,"Project 1","This is the first project created",LocalDateTime.now(),LocalDateTime.now().plusMonths(1),"Pro1",null,null,null);
			Project project2 = new Project(null,"Project 2","This is the second project created",LocalDateTime.now(),LocalDateTime.now().plusMonths(2),"Pro2",null,null,null);
			Project project3 = new Project(null,"Project 3","This is the third project created",LocalDateTime.now(),LocalDateTime.now().plusMonths(3),"Pro3",null,null,null);
			
			projectRepository.saveAll(Arrays.asList(project1,project2,project3));
		}
		
	}

}
