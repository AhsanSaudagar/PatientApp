package com.perennial.patientapp.handler;

import com.perennial.patientapp.DAO.PatientAppDAOImpl;
import com.perennial.patientapp.bean.SignUpPatient;
import com.perennial.patientapp.exception.VCare;
import com.perennial.patientapp.vo.IGenericVO;
import com.perennial.patientapp.vo.MedicineVO;
import com.perennial.patientapp.vo.PatientVO;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataHandler implements IDataHandler {

    @Autowired
    private PatientAppDAOImpl<IGenericVO> salesDAO;


    public String getUserDetails(int id) throws IOException, VCare {

        PatientVO patient = (PatientVO) salesDAO.getById(PatientVO.class, id);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(patient);
        return result;
    }

    @Override
    public Map<String, Object> signUpPatient(SignUpPatient patient) throws VCare {
        Map<String, Object> responseData = new HashMap<>();
        if (patient != null) {
            PatientVO vo = new PatientVO();
            vo.setName(patient.getName());
            vo.setEmailAddress(patient.getEmailAddress());
            vo.setMobileNo(patient.getMobileNo());
            vo.setGuardianName(patient.getGuardianName());
            vo.setGuardianMobileNumber(patient.getGuardianMobileNumber());
            vo.setAddress(patient.getAddress());
            vo.setAge(patient.getAge());
            long id = salesDAO.save(vo);
            responseData.put("id", id);
            responseData.put("message", "added successfully");
        } else {
            return null;
        }
        return null;
    }

    public String addMedicineSchedule(MedicineVO medicine) throws VCare {

        if (StringUtils.isBlank(medicine.getName())) {
            throw new VCare("Please Provide all the fields");
        }
        salesDAO.saveOrUpdate(medicine);
        JSONObject object = new JSONObject();
        object.put("Result", "Success");
        return object.toString();
    }
}
