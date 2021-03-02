package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Professor;
import com.uniovi.entities.User;

public interface ProfessorRepository extends CrudRepository<Professor, Long>{
	
	Professor findByDni(String dni);
	
	Page<Professor> findAll(Pageable pageable);
	
	@Query("SELECT p FROM Professor p WHERE (LOWER(p.nombre) LIKE LOWER(?1) OR"
			+ " LOWER(p.apellidos) LIKE LOWER(?1))")
	Page<Professor> searchByNameAndSurname(Pageable pageable,String searchText);

}
