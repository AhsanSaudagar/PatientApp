package com.perennial.patientapp.handler;

import com.perennial.patientapp.DAO.PatientAppDAOImpl;
import com.perennial.patientapp.exception.VCare;
import com.perennial.patientapp.vo.PatientVO;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class DataHandler implements IDataHandler {

    @Autowired
    private PatientAppDAOImpl salesDAO;


    public String getUserDetails(int id) throws IOException, VCare {

        PatientVO patient = (PatientVO) salesDAO.getById(PatientVO.class, id);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(patient);
        return result;
    }
}
