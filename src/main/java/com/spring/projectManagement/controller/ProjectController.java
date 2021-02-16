package com.spring.projectManagement.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.projectManagement.exception.ConflictException;
import com.spring.projectManagement.exception.NotFoundElementException;
import com.spring.projectManagement.model.Project;
import com.spring.projectManagement.service.ProjectService;

@RestController
@CrossOrigin("*")
@RequestMapping(path="/projectManagement")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/projects")
	public ResponseEntity<List<Project>> getAllProjects(){
		List<Project> projects = projectService.getProjects();
		if(projects.isEmpty()) {
			throw new NotFoundElementException("No Project records were found");
		}
		return new ResponseEntity<List<Project>>(projects,HttpStatus.OK);
	}
	
	@GetMapping("/projects/{id}")
	public ResponseEntity<Project> getProject(@PathVariable Long id){
		Optional<Project> pro = projectService.getProject(id);
		if(!pro.isPresent()) {
			throw new NotFoundElementException(String.format("This Element for id = [%s] is Not Found", id));
		}
		return new ResponseEntity<Project>(pro.get(),HttpStatus.OK);
	}
	
	@PostMapping("/projects")
	public ResponseEntity<Project> addProject(@Valid @RequestBody Project project){
		Optional<Project> pro = projectService.getProjectByName(project.getName());
		if (pro.isPresent()) {
			throw new ConflictException("This element has being exist");
		}
		return new ResponseEntity<Project>(projectService.addProject(project),HttpStatus.CREATED);
	}
	
	@PutMapping("/projects")
	public ResponseEntity<Project> updateProject(@Valid @RequestBody Project project){
		return new ResponseEntity<Project>(projectService.updateProject(project),HttpStatus.OK);
	}
	
	@DeleteMapping("/projects/{id}")
	public ResponseEntity<Object> deleteProject(@PathVariable Long id){
		Optional<Project> project = projectService.getProject(id);
		if (!project.isPresent()) {
			throw new NotFoundElementException(String.format("This Element for id = [%s] is Not Found", id));
		}
		projectService.deleteProject(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
