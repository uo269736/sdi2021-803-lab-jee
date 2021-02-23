package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Professor;
import com.uniovi.services.ProfessorService;

@Component
public class ProfessorAddFormValidator implements Validator {

	@Autowired
	private ProfessorService professorService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Professor.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Professor professor= (Professor) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		if (professor.getDni().length() != 9) {
			errors.rejectValue("dni", "Error.profesor.agregar.dni.length");
		}
		if (Character.isDigit(professor.getDni().charAt(professor.getDni().length()-1))) {
			errors.rejectValue("dni", "Error.profesor.agregar.dni.letra");
		}
		if (professorService.getProfessorByDni(professor.getDni()) != null) {
			errors.rejectValue("dni", "Error.signup.dni.duplicate");
		}
		if (professor.getNombre().length() < 5 || professor.getNombre().length() > 24) {
			errors.rejectValue("nombre", "Error.signup.name.length");
		}
		if (professor.getApellidos().length() < 5 || professor.getApellidos().length() > 24) {
			errors.rejectValue("apellidos", "Error.signup.lastName.length");
		}
		if (professor.getCategoria().length() < 5 || professor.getCategoria().length() > 24) {
			errors.rejectValue("categoria", "Error.profesor.agregar.categoria");
		}
	}
}
