package com.perennial.patientapp.handler;

import com.perennial.patientapp.bean.SignUpPatient;
import com.perennial.patientapp.exception.VCare;

import java.util.Map;

public interface IDataHandler {

    Map<String, Object> signUpPatient(SignUpPatient patient) throws VCare;
}
