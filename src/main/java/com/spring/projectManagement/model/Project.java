package com.spring.projectManagement.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.projectManagement.validation.CompareDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@CompareDate
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="This field of Name is required")
	@Size(min = 3, message="this field of Name must be greater than three")
	private String name;
	private String description;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String prefix;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	private List<Sprint> sprints;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
	private Backlog backlog;

}
