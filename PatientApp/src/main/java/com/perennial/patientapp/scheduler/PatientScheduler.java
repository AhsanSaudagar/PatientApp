package com.perennial.patientapp.scheduler;

import com.perennial.patientapp.DAO.PatientAppDAOImpl;
import com.perennial.patientapp.exception.VCare;
import com.perennial.patientapp.twilio.PhoneCall;
import com.perennial.patientapp.util.KeyValue;
import com.perennial.patientapp.vo.IGenericVO;
import com.perennial.patientapp.vo.PatientVO;
import com.perennial.patientapp.vo.TrackerVO;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@EnableScheduling
@Configurable
public class PatientScheduler {
    @Autowired
    public PatientAppDAOImpl<IGenericVO> patientAppDAO;

    @Scheduled(fixedDelay = 1000 * 60 * 1)
    public void scheduleFixedRateReminder() throws VCare{
        List<Object[]> scheduleList=null;
        int hour= LocalDateTime.now().getHour();
        int minit= LocalDateTime.now().getMinute();
        String ampm= hour>12?"pm":"am";
        String query="select PATIENT_ID, ID from PATIENTS_SCHEDULE where minute(TIME)=:minit and hour(TIME)=:hour";
        List<KeyValue> list=new ArrayList<KeyValue>();
        list.add(new KeyValue("minit",minit));
        list.add(new KeyValue("hour",hour));
        try {
            scheduleList=patientAppDAO.executeSqlQuery(query,list);
            if(scheduleList!=null && !scheduleList.isEmpty()){
                for(Object[] arr :scheduleList){
                    long patientId = ((BigInteger) arr[0]).intValue();
                    long scheduleId = ((BigInteger) arr[1]).intValue();
                    PatientVO patient = (PatientVO) patientAppDAO.getById(PatientVO.class, patientId);
                    String time = String.valueOf(hour)+":"+String.valueOf(minit+1)+ampm;
                    Map<String,String> param = new HashMap<>();
                    param.put("name",patient.getName());
                    param.put("schedule_id",String.valueOf(scheduleId));
                    param.put("time",time);
                    param.put("to_number","+91"+patient.getMobileNo());
                    String link ="http://52.55.222.177/patient_app/makeCall.php";
                    URIBuilder uriBuilder = new URIBuilder(link);
                    for (Map.Entry<String, String> queryParam : param.entrySet()) {
                        uriBuilder.addParameter(queryParam.getKey(), queryParam.getValue());
                    }
                    URI uri = uriBuilder.build();
                    HttpRequestBase request = new HttpGet(uri);
                    HttpClientPool.getClient().execute(request);
                    TrackerVO trackerVO = new TrackerVO(scheduleId,new Date());
                    patientAppDAO.save(trackerVO);
                    //PhoneCall phoneCall = new PhoneCall();
                    //phoneCall.giveACall(patient.getName(),time,scheduleId,patient.getMobileNo(),patient.getGuardianMobileNumber());
                }
            }

        }catch (VCare vCare) {
            vCare.getErrMsg();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
