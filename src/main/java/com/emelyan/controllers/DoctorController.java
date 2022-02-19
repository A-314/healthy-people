package com.emelyan.controllers;

import com.emelyan.model.Doctor;
import com.emelyan.model.Patient;
import com.emelyan.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("doctorList",doctorService.findAll());
        return "doctors/index";
    }
    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") Long id,Model model){
        model.addAttribute("doctor",doctorService.getOne(id));
        return "doctors/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("doctor") @Valid Doctor doctor,@PathVariable("id") Long id){
       doctorService.update(doctor,id);
        return "redirect:/doctors";
    }
    @GetMapping("/new")
    public String addDoctor(@ModelAttribute("doctor")@Valid Patient patient){
        return "doctors/new";
    }

    @PostMapping
    public String create(@ModelAttribute("doctor")@Valid Doctor doctor, BindingResult bindingResult){
        if(bindingResult.hasErrors())return "doctors/new";
        doctorService.save(doctor);
        return "redirect:/doctors";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")Long id){
        doctorService.delete(id);
        return "redirect:/doctors";
    }

}
