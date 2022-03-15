package com.emelyan.controllers;

import com.emelyan.models.Therapy;
import com.emelyan.services.DoctorService;
import com.emelyan.services.PatientService;
import com.emelyan.services.TherapyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("therapies")
public class TherapyController {

    TherapyService therapyService;
    DoctorService  doctorService;
    PatientService patientService;

    TherapyController(TherapyService therapyService,DoctorService doctorService,PatientService patientService){
        this.therapyService= therapyService;
        this.doctorService = doctorService;
        this.patientService= patientService;
    }
    @GetMapping
    public String getListTherapy(Model model){
        model.addAttribute("therapies", therapyService.findAll());
        return "therapies/index";
    }
    @GetMapping("new")
    public String index( @ModelAttribute("therapy")@Valid Therapy therapy,Model model){
        model.addAttribute("doctors",doctorService.findAll());
        model.addAttribute("patients",patientService.findAll());
        return "therapies/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("therapy")@Valid Therapy therapy){
        therapyService.save(therapy);
        return "redirect:/therapies";
    }
    @GetMapping("/{id}/edit")
    public String edit( @PathVariable("id")Long id,Model model){
        model.addAttribute("therapy",  therapyService.show(id));
        model.addAttribute("doctors",  doctorService.findAll());
        model.addAttribute("patients", patientService.findAll());
        return "therapies/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("therapy")@Valid Therapy therapy,@PathVariable("id") Long id){
        therapyService.update(therapy);
        return "redirect:/therapies";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        therapyService.delete(id);
        return "redirect:/therapies";
    }
}
