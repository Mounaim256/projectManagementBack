package com.spring.projectManagement.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Project> projects;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Epic> epics;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rapporteur")
	private List<Iteam> rapporteurs;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "responsable")
	private List<Iteam> responsables;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;

}
