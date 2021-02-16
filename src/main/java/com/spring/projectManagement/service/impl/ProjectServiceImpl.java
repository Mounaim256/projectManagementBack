package com.spring.projectManagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.projectManagement.exception.ConflictException;
import com.spring.projectManagement.model.Project;
import com.spring.projectManagement.repository.ProjectRepository;
import com.spring.projectManagement.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<Project> getProjects() {
		return projectRepository.findAll();
	}

	@Override
	public Optional<Project> getProject(Long id) {
		return projectRepository.findById(id);
	}
	
	@Override
	public Optional<Project> getProjectByName(String name) {
		return projectRepository.findByName(name);
	}

	@Override
	public Project addProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public Project updateProject(Project projectUpdate) {
		Project orginalProject = projectRepository.findById(projectUpdate.getId()).get();
		Optional<Project> project = projectRepository.findByName(projectUpdate.getName());

		if (!projectUpdate.getName().equals(orginalProject.getName()) && project.isPresent()) {
			throw new ConflictException("This element has being exist");
		}
		
		BeanUtils.copyProperties(projectUpdate, orginalProject);
		return projectRepository.save(orginalProject);
	}

	@Override
	public void deleteProject(Long id) {
		projectRepository.deleteById(id);
	}

	@Override
	public boolean isEndDateGreaterThanStartDate(Project project) {
		return project.getEndDate() != null && project.getStartDate() != null && project.getEndDate().isAfter(project.getStartDate());
	}

}
