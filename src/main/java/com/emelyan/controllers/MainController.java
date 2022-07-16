package com.emelyan.controllers;

import com.emelyan.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PatientService patientService;

    @GetMapping("/")
    public String get( Model model){
        model.addAttribute("", null);
        return "index";
    }

    @PostMapping("/")
    public String save(){
        return "redirect:";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("patients", patientService.patientList());
        return "treatments/edit";
    }

    @PatchMapping("/{id}")
    public String update(){
        return "redirect:/therapies";
    }

    @GetMapping("/about_program")
    public String getAboutProgram(){
        return "about_program/index";
    }
}
