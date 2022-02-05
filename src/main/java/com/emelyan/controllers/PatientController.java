package com.emelyan.controllers;

import com.emelyan.model.Patient;
import com.emelyan.service.PatientService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("patients")
public class PatientController {

	PatientService patientService;

	PatientController(PatientService patientService){
		this.patientService = patientService;
	}

	@GetMapping()
	public String getListPatient(Model model) {
		model.addAttribute("listPatient", patientService.findAll());
		return "patients/index";}

	@GetMapping("new")
	public String save(@ModelAttribute("patient")@Valid Patient patient){
		return "patients/new";
	}

	@PostMapping()
	public String create(@ModelAttribute("patient") @Valid Patient patient){
		patientService.save(patient);
		return "patients/index";
	}
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id")Long id){
		model.addAttribute("patient", patientService.show(id));
		return "patients/edit";
	}
	@PatchMapping("/{id}")
	public String update(@ModelAttribute("patient")@Valid Patient patient,@PathVariable("id") Long id){
		patientService.update(patient,id);
		return "redirect:/patients";
	}
}
