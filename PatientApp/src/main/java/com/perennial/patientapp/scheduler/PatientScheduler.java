package com.perennial.patientapp.scheduler;

import com.perennial.patientapp.DAO.PatientAppDAOImpl;
import com.perennial.patientapp.exception.VCare;
import com.perennial.patientapp.util.KeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@EnableScheduling
public class PatientScheduler {
    @Autowired
    public PatientAppDAOImpl patientAppDAO;

    @Scheduled(fixedRate = 1000)
    public List<Object[]> scheduleFixedRateReminder(){
        List<Object[]> scheduleList=new ArrayList<Object[]>();

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        String query="select PATIENT_ID from SCHEDULE where SCHEDULED_TIME=?";
        List<KeyValue> list=new ArrayList<KeyValue>();
        list.add(new KeyValue("SCHEDULED_TIME",timestamp));
        try {
            scheduleList=patientAppDAO.executeSqlQuery(query,list);
        }catch (VCare vCare){
            vCare.getErrMsg();
        }
        return scheduleList;
    }
}
