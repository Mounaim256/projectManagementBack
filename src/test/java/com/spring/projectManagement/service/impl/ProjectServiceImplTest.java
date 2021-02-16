package com.spring.projectManagement.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.projectManagement.model.Project;
import com.spring.projectManagement.repository.ProjectRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ProjectServiceImplTest {
	
	@MockBean
	ProjectRepository projectRepository;
	
	@InjectMocks
	ProjectServiceImpl projectServiceImpl;
	
	
	@Test
	public void whenFindAll_thenReturnProjectList() {
		List<Project> expectedProjects = new ArrayList<Project>();
		
		Project project1 = new Project(1L,"Project 1","This is the first project created",LocalDateTime.now(),LocalDateTime.now().plusMonths(1),"Pro1",null,null,null);
		Project project2 = new Project(2L,"Project 2","This is the second project created",LocalDateTime.now(),LocalDateTime.now().plusMonths(2),"Pro2",null,null,null);
		Project project3 = new Project(3L,"Project 3","This is the third project created",LocalDateTime.now(),LocalDateTime.now().plusMonths(3),"Pro3",null,null,null);
		
		expectedProjects.addAll(Arrays.asList(project1,project2,project3));
		
		Mockito.when(projectRepository.findAll()).thenReturn(expectedProjects);
		
		List<Project> actualProjects = projectServiceImpl.getProjects();
		assertEquals(expectedProjects, actualProjects);
	}

}
