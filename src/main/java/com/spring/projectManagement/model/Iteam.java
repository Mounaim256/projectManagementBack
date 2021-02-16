package com.spring.projectManagement.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Iteam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private String summry;
	private int storyPoints;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "epic_id")
	private Epic epic;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sprint_id")
	private Sprint sprint;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "priority_id")
	private Priority priority;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	private Type type;
	
	@OneToMany(fetch = FetchType.LAZY ,mappedBy = "iteam")
	private List<LifeIteam> iteams;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rapporteur_id")
	private User rapporteur;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "responsable_id")
	private User responsable;
	

}
