package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;
import com.uniovi.services.MarksService;
import com.uniovi.services.UsersService;

@Component
public class MarkAddFormValidator implements Validator {

	@Autowired
	private MarksService marksService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Mark.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Mark mark = (Mark) target;
		if (mark.getDescription().length() < 20 ) {
			errors.rejectValue("description", "Error.notas.agregar.description.length");
		}
		if (mark.getScore() < 0 ||  mark.getScore() > 10) {
			errors.rejectValue("score", "Error.notas.agregar.score.minmax");
		}
		
	}
}
