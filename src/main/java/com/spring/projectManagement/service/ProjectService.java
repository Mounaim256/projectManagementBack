package com.spring.projectManagement.service;

import java.util.List;
import java.util.Optional;

import com.spring.projectManagement.model.Project;

public interface ProjectService {
	public List<Project> getProjects();
	public Optional<Project> getProject(Long id);
	public Optional<Project> getProjectByName(String name);
	public Project addProject(Project project);
	public Project updateProject(Project project);
	public void deleteProject(Long id);
	public boolean isEndDateGreaterThanStartDate(Project project);
}
