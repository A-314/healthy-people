package com.emelyan.controllers;

import com.emelyan.models.Patient;
import com.emelyan.services.PatientService;
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
    public String index(Model model,@RequestParam(required = false, defaultValue ="")String filter){
        model.addAttribute("patients", patientService.patientList(filter));
        model.addAttribute("filter",filter);
        return "people/patients/index";
    }
    @GetMapping("new")
    public String save(@ModelAttribute("patient")@Valid Patient patient){
        return "people/patients/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("patient")@Valid Patient patient){
        patientService.save(patient);
   return "redirect:/patients";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id")Long id){
        model.addAttribute("patient", patientService.show(id));
        return "people/patients/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("patient")@Valid Patient patient,@PathVariable("id") Long id){
           patientService.update(patient,id);
       return "redirect:/patients";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        patientService.delete(id);
        return "redirect:/patients";
    }

}