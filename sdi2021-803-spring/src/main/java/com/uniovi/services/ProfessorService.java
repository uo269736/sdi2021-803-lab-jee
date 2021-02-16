package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.entities.Professor;
import com.uniovi.repositories.ProfessorRepository;

public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository; 
	

	public Professor getProfessor(Long id) {
		return professorRepository.findById(id).get();
	}

	public void addProfessor(Professor professor) {
		professorRepository.save(professor);
	}

	public void deleteProfessor(Long id) {
		professorRepository.deleteById(id);
	}
}
