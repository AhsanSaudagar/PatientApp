package com.perennial.patientapp.controller;

import com.perennial.patientapp.bean.MedicineShedule;
import com.perennial.patientapp.bean.SignUpPatient;
import com.perennial.patientapp.exception.VCare;
import com.perennial.patientapp.handler.DataHandler;
import com.perennial.patientapp.util.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "patient")
public class DataController {


    @Autowired
    private DataHandler dataHandler;
    private MedicineShedule medicineShedule;

    @RequestMapping(value = "addSchedule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addMedicineSchedule(@RequestHeader long patientId, @RequestBody MedicineShedule medicineShedule) {

    }


    @RequestMapping(value = "getUserDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> getUserDetails(@RequestParam("id") int id, HttpServletRequest request) {
        try {
            return ResponseHandler.success(dataHandler.getUserDetails(id));
        } catch (VCare | IOException e) {
            return ResponseHandler.error(e);
        }
    }

    @RequestMapping(value = "addMedicineSchedule", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> addMedicine(HttpServletRequest request, @RequestBody String medicineSchedule, @RequestHeader long patientId) {
        try {
            return ResponseHandler.success(dataHandler.addMedicineSchedule(medicineSchedule, patientId));
        } catch (Exception e) {
            return ResponseHandler.error(e);
        }

    }

    @RequestMapping(value = "signUpPatient", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> SignUpPatient(HttpServletRequest request, @RequestBody SignUpPatient patient) {
        Map<String, Object> response = null;
        try {
            response = dataHandler.signUpPatient(patient);
        } catch (VCare e) {
            return ResponseHandler.error(e);
        }
        return ResponseHandler.success(response);
    }

}
