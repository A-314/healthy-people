package com.emelyan.controllers;

import com.emelyan.models.Record;
import com.emelyan.services.IntervalService;
import com.emelyan.services.PatientService;
import com.emelyan.services.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

import static com.emelyan.util.DataTimeUtil.*;

@Controller
@RequestMapping("records")
public class RecordsController {

    protected static final Logger LOG = LoggerFactory.getLogger(RecordsController.class);

    RecordService receptionScheduleService;
    IntervalService receptionIntervalService;
    PatientService  patientService;

    public RecordsController(RecordService receptionScheduleService,
                             IntervalService receptionIntervalService, PatientService patientService) {
        this.receptionScheduleService = receptionScheduleService;
        this.receptionIntervalService = receptionIntervalService;
        this.patientService = patientService;
    }

    @GetMapping()
    public String getReceptionSchedule(Model model, @RequestParam(required = false, defaultValue = "") String startPeriod) throws ParseException {

        long startPeriodMillis;

        if(startPeriod.equals("")){startPeriodMillis = System.currentTimeMillis();
        }else{ startPeriodMillis = dateFormatYearMonthDay.parse(startPeriod).getTime();}

        LOG.info("Get schedule of reception on "+ (startPeriod.equals("")?dateFormatYearMonthDay.format(startPeriodMillis):startPeriod));

        String[] arrayPeriod = new String[7];
        for (int i = 0; i < 7; i++) {
            arrayPeriod[i] = dateFormatMonthDay.format(startPeriodMillis + i * MILLISECONDS_IN_DAY);
        }

        model.addAttribute("records",  receptionScheduleService.getSchedule(startPeriodMillis));
        model.addAttribute("intervals", receptionIntervalService.receptionIntervalList());

        model.addAttribute("startNextPeriod", dateFormatYearMonthDay.format(startPeriodMillis + 7 * MILLISECONDS_IN_DAY));
        model.addAttribute("startPreviousPeriod", dateFormatYearMonthDay.format(startPeriodMillis - 7 * MILLISECONDS_IN_DAY));

        model.addAttribute("periodDates", arrayPeriod);
        return "records/index";
    }

    @GetMapping("/new")
    public String getNew(@ModelAttribute("Record")@Valid Record record, Model model){
        model.addAttribute("intervals", receptionIntervalService.getAll());
        model.addAttribute("patients", patientService.patientList());
        return "records/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("Record")@Valid Record record){
        receptionScheduleService.save(record);
        return "redirect:/records";
    }

    @PatchMapping()
    public String update(@ModelAttribute("Record")@Valid Record record){
        receptionScheduleService.update(record);
        return "redirect:/records";
    }
}
