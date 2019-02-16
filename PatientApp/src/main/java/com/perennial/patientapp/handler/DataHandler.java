package com.perennial.patientapp.handler;

import com.perennial.patientapp.DAO.PatientAppDAOImpl;
import com.perennial.patientapp.bean.SignUpPatient;
import com.perennial.patientapp.exception.VCare;
import com.perennial.patientapp.vo.IGenericVO;
import com.perennial.patientapp.vo.MedicineVO;
import com.perennial.patientapp.vo.PatientVO;
import com.perennial.patientapp.vo.ScheduleVO;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        return responseData;
    }

    public String addMedicineSchedule(String schedule, long patientId) throws VCare {


        JSONObject jsonObject = new JSONObject(schedule);
        try {
            long medicineId = jsonObject.getLong("medicineId");
            int scheduledQuantity = jsonObject.getInt("scheduledQuantity");
            DateFormat df = new SimpleDateFormat("2019-01-01 HH:MM:00");
            Date scheduledTime = df.parse(jsonObject.getString("scheduledTime"));

            MedicineVO medicineVo = (MedicineVO) salesDAO.getById(MedicineVO.class, medicineId);
            PatientVO patientVO = (PatientVO) salesDAO.getById(PatientVO.class, patientId);


            ScheduleVO scheduleVO = new ScheduleVO(medicineVo, scheduledQuantity, 0, 0, patientVO, scheduledTime);
            if (jsonObject.has("id")) {
                long id = (long) jsonObject.get("id");
                scheduleVO.setId(id);
            }
            salesDAO.saveOrUpdate(scheduleVO);
            jsonObject = new JSONObject();
            jsonObject.put("Result", "Success");

        } catch (ParseException | org.json.JSONException e) {
            jsonObject = new JSONObject();
            jsonObject.put("Result", "ERROR");
            throw new VCare("MISSING REQUIRED FIELDS");
        }
        return jsonObject.toString();
    }
}
