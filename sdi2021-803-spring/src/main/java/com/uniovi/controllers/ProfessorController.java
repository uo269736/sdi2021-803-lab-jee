package com.uniovi.controllers;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Professor;
import com.uniovi.services.ProfessorService;
import com.uniovi.validators.ProfessorAddFormValidator;

@Controller
public class ProfessorController {
	
	@Autowired //Inyectar el servicio
	private ProfessorService professorService;
	
	@Autowired
	private ProfessorAddFormValidator professorAddFormValidator;
	
	
	@RequestMapping("/professor/list")
	public String getList(Model model, Pageable pageable) {
		Page<Professor> professors = new PageImpl<Professor>(new LinkedList<Professor>());
		professors=professorService.getProfessors(pageable);
		model.addAttribute("professorList", professors.getContent());
		model.addAttribute("page",professors);
		return "professor/list";
	}
	
	@RequestMapping(value = "/professor/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Professor professor) {
		professor.setId(id);
		professorService.addProfessor(professor);
		return "redirect:/professor/details/" + id;
	}
	
	@RequestMapping(value = "/professor/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("professor", professorService.getProfessor(id));
		return "professor/edit";
	}
	
	@RequestMapping(value = "/professor/add")
	public String getProfessor(Model model) {
		model.addAttribute("professor",new Professor());
		model.addAttribute("professorList", professorService.getProfessors());
		return "professor/add";
	}

	@RequestMapping(value="/professor/add",method=RequestMethod.POST)
	public String setProfesor(@Validated Professor professor, BindingResult result) {
		professorAddFormValidator.validate(professor, result);
		if(result.hasErrors()) {
			return "/professor/add";
		}
		professorService.addProfessor(professor);
		return "redirect:/professor/list";
	}

	@RequestMapping("/professor/details/{id}")
	public String getDetail(Model model,@PathVariable Long id) {
		model.addAttribute("professor",professorService.getProfessor(id));
		return "professor/details";
	}
	
	@RequestMapping("/professor/delete/{id}")
	public String deleteProfessor(@PathVariable Long id) {
		professorService.deleteProfessor(id);
		return "redirect:/professor/list";
	}

}
