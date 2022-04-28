package com.emelyan.controllers;

import com.emelyan.models.Treatment;
import com.emelyan.services.PatientService;
import com.emelyan.services.TreatmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    TreatmentService treatmentService;
    PatientService patientService;

    MainController(TreatmentService treatmentService,PatientService patientService){
        this.treatmentService=treatmentService;
        this.patientService = patientService;
    }

    @GetMapping("/")
    public String get(@ModelAttribute Treatment treatment, Model model){
       model.addAttribute("patients",patientService.patientList(null));
       model.addAttribute("treatments",treatmentService.findAll());
        return "index";
    }
    @PostMapping("/")
    public String save(@ModelAttribute Treatment treatment){
        treatmentService.save(treatment);
        return "redirect:";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("patients",patientService.patientList(null));
        model.addAttribute("treatment",treatmentService.getOne(id));
        return "treatments/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute Treatment treatment){
        treatmentService.update(treatment);
        return "redirect:/";
    }

    @GetMapping("/about_program")
    public String getAboutProgram(){
        return "about_program/index";
    }
}
