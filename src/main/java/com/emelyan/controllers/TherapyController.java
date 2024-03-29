package com.emelyan.controllers;

import com.emelyan.models.Therapy;
import com.emelyan.services.DoctorService;
import com.emelyan.services.PatientService;
import com.emelyan.services.TherapyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("therapies")
@RequiredArgsConstructor
public class TherapyController {

    private final TherapyService therapyService;
    private final DoctorService  doctorService;
    private final PatientService patientService;

    @GetMapping
    public String getListTherapy(Model model){
        model.addAttribute("therapies", therapyService.findAll());
        return "therapies/index";
    }

    @GetMapping("new")
    public String index( @ModelAttribute("therapy")@Valid Therapy therapy, Model model){
        model.addAttribute("doctors", doctorService.doctorList());
        model.addAttribute("patients", patientService.patientList());
        return "therapies/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("therapy")@Valid Therapy therapy){
        therapyService.save(therapy);
        return "redirect:/therapies";
    }

    @GetMapping("/{id}/edit")
    public String edit( @PathVariable("id")Long id, Model model){
        model.addAttribute("therapy",   therapyService.show(id));
        model.addAttribute("therapies", therapyService.findAll());
        model.addAttribute("doctors",   doctorService.doctorList());
        model.addAttribute("patients",  patientService.patientList());
        return "therapies/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("therapy")@Valid Therapy therapy){
        therapyService.update(therapy);
        return "redirect:/therapies";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        therapyService.delete(id);
        return "redirect:/therapies";
    }

}
