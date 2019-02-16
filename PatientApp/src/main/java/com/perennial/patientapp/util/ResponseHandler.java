package com.perennial.patientapp.util;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_ERROR = "ERROR";

    public static Map<String, Object> success(Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("statusCode", HttpStatus.OK.value());
        map.put("status", STATUS_SUCCESS);
        map.put("data", data);
        return map;
    }

    public static Map<String, Object> success(Object data, String successMessage) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("statusCode", HttpStatus.OK.value());
        map.put("status", STATUS_SUCCESS);
        map.put("data", data);
        map.put("msg", successMessage);
        return map;
    }

    public static Map<String, Object> error(Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", STATUS_ERROR);
        map.put("data", data);
        return map;
    }

    public static Map<String, Object> error(String errCode, String errMsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("statusCode", errCode);
        map.put("status", STATUS_ERROR);
        map.put("data", errMsg);
        return map;
    }

    public static Map<String, Object> customError(String errMsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", STATUS_ERROR);
        map.put("message", errMsg);
        map.put("data", errMsg);
        return map;
    }
}