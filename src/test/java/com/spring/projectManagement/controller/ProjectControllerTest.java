package com.spring.projectManagement.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.projectManagement.exception.ConflictException;
import com.spring.projectManagement.exception.NotFoundElementException;
import com.spring.projectManagement.model.Project;
import com.spring.projectManagement.service.ProjectService;

@WebMvcTest
public class ProjectControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	ProjectService projectService;
	
	@Test
	public void get_allProjects_returnsOkWithListOfProjects() throws Exception {
		List<Project> projects = new ArrayList<Project>();
		
		Project project1 = new Project(null,"Project 1","This is the first project created",LocalDateTime.now(),LocalDateTime.now().plusMonths(1),"Pro1",null,null,null);
		Project project2 = new Project(null,"Project 2","This is the second project created",LocalDateTime.now(),LocalDateTime.now().plusMonths(2),"Pro2",null,null,null);
		Project project3 = new Project(null,"Project 3","This is the third project created",LocalDateTime.now(),LocalDateTime.now().plusMonths(3),"Pro3",null,null,null);
		
		projects.addAll(Arrays.asList(project1,project2,project3));
		
		Mockito.when(projectService.getProjects()).thenReturn(projects);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/projectManagement/projects").contentType(MediaType.APPLICATION_JSON_VALUE))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$", hasSize(3)))
			   .andExpect(jsonPath("$[0].name", is("Project 1"))).andExpect(jsonPath("$[0].description", is("This is the first project created")))
			   .andExpect(jsonPath("$[1].name", is("Project 2"))).andExpect(jsonPath("$[1].description", is("This is the second project created")))
			   .andExpect(jsonPath("$[2].name", is("Project 3"))).andExpect(jsonPath("$[2].description", is("This is the third project created")));
	}
	
	@Test
	public void get_allProjects_throwsNotFoundElementException() throws Exception {
		Mockito.when(projectService.getProjects()).thenReturn(new ArrayList<Project>());
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/projectManagement/projects").contentType(MediaType.APPLICATION_JSON_VALUE);
		
		ResultActions resultActions = mockMvc.perform(builder).andExpect(status().isNotFound());
		
		assertEquals(NotFoundElementException.class, resultActions.andReturn().getResolvedException().getClass());
		assertTrue(resultActions.andReturn().getResolvedException().getMessage().contains("No Project records were found"));
	}
	
	@Test
	public void post_project_returnsOkWithProjectObject() throws Exception {
		Project project1 = new Project(null,"Project 1","This is the first project created",LocalDateTime.now(),LocalDateTime.now().plusMonths(1),"Pro1",null,null,null);
		
		Mockito.when(projectService.getProjectByName(Mockito.anyString())).thenReturn(Optional.empty());
		Mockito.when(projectService.addProject(Mockito.any(Project.class))).thenReturn(project1);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/projectManagement/projects")
																	  .contentType(MediaType.APPLICATION_JSON_VALUE)
																	  .accept(MediaType.APPLICATION_JSON)
																	  .characterEncoding("UTF-8")
																	  .content(this.mapper.writeValueAsBytes(project1));
		mockMvc.perform(builder).andExpect(status().isCreated())
								.andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(project1)));
	}
	
	@Test
	public void post_project_submitWithExistingProject_ThrowsConflictException() throws Exception {
		Project project1 = new Project(null,"Project 1","This is the first project created",LocalDateTime.now(),LocalDateTime.now().plusMonths(1),"Pro1",null,null,null);
		
		Mockito.when(projectService.getProjectByName(Mockito.anyString())).thenReturn(Optional.of(project1));
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/projectManagement/projects")
				  .contentType(MediaType.APPLICATION_JSON_VALUE)
				  .accept(MediaType.APPLICATION_JSON)
				  .characterEncoding("UTF-8")
				  .content(this.mapper.writeValueAsBytes(project1));
		
		ResultActions resultActions = mockMvc.perform(builder).andExpect(status().isConflict());
		assertEquals(ConflictException.class, resultActions.andReturn().getResolvedException().getClass());
		assertTrue(resultActions.andReturn().getResolvedException().getMessage().contains("This element has being exist"));
	}
	
	@Test
	public void post_project_submitsWithInvalidProjectData_ThrowsMethodArgumentNotValidException() throws Exception {
		Project project1 = new Project(null,"","This is the first project created",LocalDateTime.now().plusMonths(1),LocalDateTime.now(),"Pro1",null,null,null);
				
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/projectManagement/projects")
				  .contentType(MediaType.APPLICATION_JSON_VALUE)
				  .accept(MediaType.APPLICATION_JSON)
				  .characterEncoding("UTF-8")
				  .content(this.mapper.writeValueAsBytes(project1));
		
		ResultActions resultActions = mockMvc.perform(builder).andExpect(status().isBadRequest());
		assertEquals(MethodArgumentNotValidException.class, resultActions.andReturn().getResolvedException().getClass());
		assertTrue(resultActions.andReturn().getResolvedException().getMessage().contains("This field of Name is required"));
		assertTrue(resultActions.andReturn().getResolvedException().getMessage().contains("this field of Name must be greater than three"));
		assertTrue(resultActions.andReturn().getResolvedException().getMessage().contains("the EndDate must greater than StartDate"));
	}
	
	@Test
	public void get_project_returnsOkWithProjectObject() throws Exception {
		Project project1 = new Project(1L,"Project 1","This is the first project created",LocalDateTime.now(),LocalDateTime.now().plusMonths(1),"Pro1",null,null,null);
		
		Mockito.when(projectService.getProject(Mockito.anyLong())).thenReturn(Optional.of(project1));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/projectManagement/projects/1").contentType(MediaType.APPLICATION_JSON_VALUE))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.id", is(1)))
			   .andExpect(jsonPath("$.name", is("Project 1")))
			   .andExpect(jsonPath("$.description", is("This is the first project created")));
		
	}
	
	@Test
	public void get_project_throwsNotFoundElementException() throws Exception {
		Mockito.when(projectService.getProject(Mockito.anyLong())).thenReturn(Optional.empty());
		
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/projectManagement/projects/1").contentType(MediaType.APPLICATION_JSON_VALUE))
											 .andExpect(status().isNotFound());
		assertEquals(NotFoundElementException.class, resultActions.andReturn().getResolvedException().getClass());
		assertTrue(resultActions.andReturn().getResolvedException().getMessage().contains("This Element for id = [1] is Not Found"));
		
	}
	
	@Test
	public void put_project_returnsUpdatedObjWith202() throws Exception {
		
	}
	
	@Test
 	public void delete_project_returnsOkWithProjectDeleted() throws Exception {
		Project project1 = new Project(1L,"Project 1","This is the first project created",LocalDateTime.now(),LocalDateTime.now().plusMonths(1),"Pro1",null,null,null);
		
		Mockito.when(projectService.getProject(Mockito.anyLong())).thenReturn(Optional.of(project1));
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/projectManagement/projects/1").contentType(MediaType.APPLICATION_JSON_VALUE))
			   .andExpect(status().isNoContent());
			   }
	
	@Test
	public void delete_project_throwsNotFoundElementException() throws Exception {
		Mockito.when(projectService.getProject(Mockito.anyLong())).thenReturn(Optional.empty());
		
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/projectManagement/projects/1").contentType(MediaType.APPLICATION_JSON_VALUE))
									   .andExpect(status().isNotFound());
		assertEquals(NotFoundElementException.class,resultActions.andReturn().getResolvedException().getClass());
		assertTrue(resultActions.andReturn().getResolvedException().getMessage().contains("This Element for id = [1] is Not Found"));
	}

}
