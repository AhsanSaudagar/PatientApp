package com.perennial.patientapp.handler;

import com.perennial.patientapp.DAO.PatientAppDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class DataHandler implements IDataHandler {

    @Autowired
    private PatientAppDAOImpl salesDAO;


}
