package com.perennial.patientapp.handler;

import com.perennial.patientapp.DAO.PatientAppDAOImpl;
import com.perennial.patientapp.bean.SignUpPatient;
import com.perennial.patientapp.exception.VCare;
import com.perennial.patientapp.util.KeyValue;
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
import java.util.*;

@Component
public class DataHandler implements IDataHandler {

    @Autowired
    private PatientAppDAOImpl<IGenericVO> salesDAO;

    private static ScheduleVO initMedicineSchedule(long patientId, JSONObject jsonObject, PatientAppDAOImpl<IGenericVO> salesDAO) throws ParseException, VCare {
        long medicineId = jsonObject.getLong("medicineId");
        int scheduledQuantity = jsonObject.getInt("scheduledQuantity");
        DateFormat df = new SimpleDateFormat("2019-01-01 HH:mm:ss");
        Date scheduledTime = df.parse(jsonObject.getString("scheduledTime"));

        MedicineVO medicineVo = (MedicineVO) salesDAO.getById(MedicineVO.class, medicineId);
        PatientVO patientVO = (PatientVO) salesDAO.getById(PatientVO.class, patientId);

        return new ScheduleVO(medicineVo, scheduledQuantity, 0, 0, patientVO, scheduledTime);
    }

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
            vo.setPid(patient.getPid());
            long id = salesDAO.save(vo);
            responseData.put("pid", patient.getPid());
            responseData.put("message", "added successfully");
        } else {
            return null;
        }
        return responseData;
    }

    public String addMedicineSchedule(String schedule, long patientId) throws VCare {


        JSONObject jsonObject = new JSONObject(schedule);
        try {
            ScheduleVO scheduleVO = initMedicineSchedule(patientId, jsonObject, salesDAO);
            salesDAO.save(scheduleVO);
            jsonObject = new JSONObject();
            jsonObject.put("Result", "Record added successfully");
        } catch (ParseException | org.json.JSONException e) {
            jsonObject = new JSONObject();
            jsonObject.put("Result", "ERROR");
            throw new VCare("MISSING REQUIRED FIELDS");
        }
        return jsonObject.toString();
    }

    public String updateMedicineSchedule(String schedule, long patientId) throws VCare {
        JSONObject jsonObject = new JSONObject(schedule);
        try {
            long id = jsonObject.getLong("id");
            ScheduleVO scheduleVO = initMedicineSchedule(patientId, jsonObject, salesDAO);
            scheduleVO.setId(id);
            salesDAO.merge(scheduleVO);
            jsonObject = new JSONObject();
            jsonObject.put("Result", "Record added successfully");
        } catch (ParseException | org.json.JSONException e) {
            jsonObject = new JSONObject();
            jsonObject.put("Result", "ERROR");
            throw new VCare("MISSING REQUIRED FIELDS");
        }
        return jsonObject.toString();
    }

    public String removeSchedule(long scheduleId) throws VCare {
        salesDAO.deleteById(ScheduleVO.class, scheduleId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Result", "Record deleted successfully");
        return jsonObject.toString();
    }

    public List<ScheduleVO> getUserSchedule(long id) throws VCare {
        List<KeyValue> conditions = new ArrayList<>();
        List<KeyValue> orderBy = new ArrayList<>();
        conditions.add(new KeyValue("patient.id", id));
        orderBy.add(new KeyValue("scheduledTime", "asc"));
        List<IGenericVO> objectList = salesDAO.getObjectList(ScheduleVO.class, conditions, null, 0, 0);
        List<ScheduleVO> list = new ArrayList<>();
        if (objectList != null && !objectList.isEmpty()) {
            for (IGenericVO obj : objectList) {
                ScheduleVO scheduleVO = (ScheduleVO) obj;
                scheduleVO.setPatient(null);
                list.add(scheduleVO);
            }
        }
        return list;
    }

    public List<MedicineVO> getAllMedicines() throws VCare {

        List<KeyValue> orderBy = new ArrayList();
        orderBy.add(new KeyValue("name", "asc"));

        List<IGenericVO> objectList = salesDAO.getObjectList(MedicineVO.class, null, orderBy, null, null);
        List<MedicineVO> list = new ArrayList<>();
        for (IGenericVO obj : objectList) {
            list.add((MedicineVO) obj);
        }
        return list;
    }
}