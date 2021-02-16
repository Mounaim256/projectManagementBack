package com.spring.projectManagement.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.spring.projectManagement.model.Project;

public class CompareDateValidator implements ConstraintValidator<CompareDate, Project> {
		
	@Override
	public boolean isValid(Project value, ConstraintValidatorContext context) {
		return value.getEndDate() != null && value.getStartDate() != null && value.getEndDate().isAfter(value.getStartDate());
	}

}
