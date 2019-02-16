package com.perennial.patientapp.bean;

import java.util.Date;

public class MedicineShedule {
    private String diseaseName;
    private short scheduledQuantity;
    private short executedQuantity;
    private short missedQuantity;
    private Date scheduledTime;

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public short getScheduledQuantity() {
        return scheduledQuantity;
    }

    public void setScheduledQuantity(short scheduledQuantity) {
        this.scheduledQuantity = scheduledQuantity;
    }

    public short getExecutedQuantity() {
        return executedQuantity;
    }

    public void setExecutedQuantity(short executedQuantity) {
        this.executedQuantity = executedQuantity;
    }

    public short getMissedQuantity() {
        return missedQuantity;
    }

    public void setMissedQuantity(short missedQuantity) {
        this.missedQuantity = missedQuantity;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
