package com.perennial.patientapp.bean;

import java.util.Date;

public class MedicineShedule {
    private String diseaseName;
    private int scheduledQuantity;
    private int executedQuantity;
    private int missedQuantity;
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

    public int getExecutedQuantity() {
        return executedQuantity;
    }

    public void setExecutedQuantity(int executedQuantity) {
        this.executedQuantity = executedQuantity;
    }

    public int getMissedQuantity() {
        return missedQuantity;
    }

    public void setMissedQuantity(int missedQuantity) {
        this.missedQuantity = missedQuantity;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
