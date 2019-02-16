package com.perennial.patientapp.controller;

import com.perennial.patientapp.bean.MedicineShedule;
import com.perennial.patientapp.handler.DataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="patient")
public class DataController {


    @Autowired
    private DataHandler dataHandler;
    private MedicineShedule medicineShedule;

    @RequestMapping(value = "/addSchedule", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addMedicineSchedule(@RequestHeader long patientId, @RequestBody MedicineShedule medicineShedule){

    }


    @RequestMapping(value = "getUserDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String saveKpiData(@RequestParam("id") int id, HttpServletRequest request) {

        return null;
    }

    @RequestMapping(value = "/signUpPatient", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void signUpPatient(@RequestHeader long patientId, @RequestHeader String username, String email){


    }
}
