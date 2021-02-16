package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Mark;
import com.uniovi.entities.Professor;
import com.uniovi.services.ProfessorService;

@RestController
public class ProfessorController {
	
	@Autowired //Inyectar el servicio
	private ProfessorService professorService;
	
	@RequestMapping(value = "/professor/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Professor professor) {
		professor.setId(id);
		professorService.addProfessor(professor);
		return "Edit professor with id:" + id;
	}
	
	@RequestMapping(value = "/professor/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("professor", professorService.getProfessor(id));
		return "Editing";
	}

	@RequestMapping(value="/profesor/add",method=RequestMethod.POST)
	public String setProfesor(@ModelAttribute Professor p) {
		professorService.addProfessor(p);
		return "Added: "+" "+p.getId()+" "+p.getDni()+", "+p.getNombre()+" "+p.getApellidos()+", "+p.getCategoria();
	}

	@RequestMapping("/professor/details/{id}")
	public String getDetail(@PathVariable Long id) {
		return professorService.getProfessor(id).toString();
	}
	
	@RequestMapping("/professor/delete/{id} ")
	public String deleteProfessor(@PathVariable Long id) {
		professorService.deleteProfessor(id);
		return "Deleting Professor";
	}

}
