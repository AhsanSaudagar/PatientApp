package com.perennial.patientapp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="patient")
public class DataController {

    @RequestMapping(value = "/addSchedule", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addMedicineSchedule(long patientId){

    }

}
