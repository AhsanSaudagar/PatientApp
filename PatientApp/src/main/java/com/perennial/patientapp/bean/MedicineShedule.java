package com.perennial.patientapp.bean;

import java.util.Date;

public class MedicineShedule {
    private String diseaseName;
    private int scheduledQuantity;
    private Date scheduledTime;

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public int getScheduledQuantity() {
        return scheduledQuantity;
    }

    public void setScheduledQuantity(int scheduledQuantity) {
        this.scheduledQuantity = scheduledQuantity;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
