package com.emelyan.controllers;

import com.emelyan.models.Doctor;
import com.emelyan.models.Patient;
import com.emelyan.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping()
    public String getAll(Model model, @RequestParam(required = false, defaultValue = "") String filter){
        model.addAttribute("doctors", doctorService.doctorList(filter));
        model.addAttribute("filter", filter);
        return "people/doctors/index";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model){
        model.addAttribute("doctor", doctorService.getOne(id));
        return "people/doctors/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("doctor")@Valid Doctor doctor, @PathVariable("id") Long id){
        doctorService.update(doctor, id);
        return "redirect:/doctors";
    }

    @GetMapping("/new")
    public String createNew(@ModelAttribute("doctor")@Valid Patient patient){
        return "people/doctors/new";
    }

    @PostMapping
    public String saveNew(@ModelAttribute("doctor")@Valid Doctor doctor){
        doctorService.save(doctor);
        return "redirect:/doctors";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")Long id){
        doctorService.delete(id);
        return "redirect:/doctors";
    }

}
