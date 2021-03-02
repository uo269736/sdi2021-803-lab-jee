package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;
import com.uniovi.repositories.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository; 
	

	public List<Professor> getProfessors() {
		List<Professor> professors = new ArrayList<Professor>();
		professorRepository.findAll().forEach(professors::add);
		return professors;
	}
	
	public Page<Professor> getProfessors(Pageable pageable) {
		Page<Professor> professors = professorRepository.findAll(pageable);
		return professors;
	}
	
	public Professor getProfessor(Long id) {
		return professorRepository.findById(id).get();
	}

	public void addProfessor(Professor professor) {
		professorRepository.save(professor);
	}

	public void deleteProfessor(Long id) {
		professorRepository.deleteById(id);
	}
	
	public Professor getProfessorByDni(String dni) {
		return professorRepository.findByDni(dni);
	}
	
	public Page<Professor> searchProfessorByNameAndSurname(Pageable pageable,String searchText) {
		Page<Professor> professors = new PageImpl<Professor>(new LinkedList<Professor>());
		searchText = "%"+searchText+"%";
		professors = professorRepository.searchByNameAndSurname(pageable,searchText);
		return professors;
	}
}
