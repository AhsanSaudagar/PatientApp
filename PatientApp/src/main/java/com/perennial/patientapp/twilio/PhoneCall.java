package com.perennial.patientapp.twilio;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.perennial.patientapp.exception.VCare;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.http.HttpMethod;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.twiml.TwiML;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Gather;
import com.twilio.twiml.voice.Say;
import com.twilio.type.PhoneNumber;
public class PhoneCall {
    public static final String ACCOUNT_SID = "AC36543adf98523e0d2d60e07a8e9f5487";
    public static final String AUTH_TOKEN = "4c8f6aae0cc9e1164039c57423043bbd";
    public void giveACall(String name, String time, long sheduleId, String mobileNo, String guardianNo) throws VCare{
        //Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String from = "+16465534058";
        String to = "+91"+mobileNo;
        /*Call call = Call.creator(new PhoneNumber(to), new PhoneNumber(from),
                new URI("http://demo.twilio.com/docs/voice.xml")).create();*/
        try{
            Call call = Call.creator(new PhoneNumber(to), new PhoneNumber(from),
                    new URI("http://52.55.222.177/patient_app/test3.php?name="+name+"&time="+time+"&scheduleId="+sheduleId+"&patientMobNo"+mobileNo+"&guardianNo"+guardianNo)).create();

        }catch (URISyntaxException e){
            throw new VCare("Error in PhoneCall>>giveACall");
        }

    }
    public void gatherInputs(){
        Gather gather1=new Gather.Builder().action("http://52.55.222.177/patient_app/test3.php").method(HttpMethod.POST).build();
        List list=gather1.getInputs();
        System.out.println(list);
    }
//    public static void main(String[] args) throws URISyntaxException{
//        PhoneCall phoneCall=new PhoneCall();
//        //phoneCall.giveACall();
//        //phoneCall.gatherInputs();
//
//        Say say=new Say.Builder("Please enter any key").build();
//
//        Gather gather=new Gather.Builder().action("http://52.55.222.177/patient_app/test3.php").method(HttpMethod.GET).say(say).build();
//
//
//
//        System.out.println(gather.getInputs());
//        Say say2=new Say.Builder("We didnt get any inputs!Goodbye Shubhankar").build();
//        VoiceResponse response=new VoiceResponse.Builder().gather(gather).say(say2).build();
//
//        try {
//            System.out.println(response.toXml());
//            //System.out.println(response1.toXml());
//        }catch (TwiMLException t){
//            t.printStackTrace();
//        }
//        /*Message message = Message.creator(new PhoneNumber(to),
//                new PhoneNumber(from),
//                "This is the ship that made the Kessel Run in fourteen parsecs?").create();*/
//
//        //System.out.println(message.getSid());
//    }
}
