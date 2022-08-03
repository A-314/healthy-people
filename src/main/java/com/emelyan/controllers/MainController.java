package com.emelyan.controllers;

import com.emelyan.repositories.TherapyRepository;
import com.emelyan.services.PatientService;
import com.emelyan.services.TherapyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.emelyan.util.DataTimeUtil.dateFormatYearMonthDay;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PatientService patientService;
    private final TherapyRepository therapyRepository;
    private final TherapyService therapyService;

    @GetMapping("/")
    public String get( Model model){
        StringBuffer doctorsPieChart = new StringBuffer();
        StringBuffer percentagesPieChart = new StringBuffer();
        StringBuffer periodBarChart = new StringBuffer();
        StringBuffer amountPatientBarChart = new StringBuffer();

        therapyRepository.getAll().forEach(x -> {
            doctorsPieChart.append(x[0]).append(",");
            percentagesPieChart.append(getPercentForPieChart(x[1])).append(",");
        });

        therapyService.getNumberOfPatientsByLast7Day().forEach( x -> {
            periodBarChart.append(dateFormatYearMonthDay.format(x[0])).append(",");
            amountPatientBarChart.append(x[1]).append(",");
        });

        doctorsPieChart.setLength(doctorsPieChart.length() -1);
        percentagesPieChart.setLength(percentagesPieChart.length() -1);

        model.addAttribute("amountPatientBarChart", amountPatientBarChart);
        model.addAttribute("periodBarChart",        periodBarChart);
        model.addAttribute("doctorsPieChart",       doctorsPieChart);
        model.addAttribute("percentagesPieChart",   percentagesPieChart);
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

    public static String getPercentForPieChart(String amountPatient){
        final int maxAmountDoctorForPieChart = 4;
        return Integer.toString(Integer.valueOf(amountPatient) * 100 / maxAmountDoctorForPieChart );
    }
}
