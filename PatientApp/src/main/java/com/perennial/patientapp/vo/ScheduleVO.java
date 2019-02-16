package com.perennial.patientapp.vo;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "SCHEDULE")
public class ScheduleVO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private MedicineVO medicineVO;

    @Column(name = "SCHEDULED_QUANTITY")
    private short scheduledQuantity;

    @Column(name = "EXECUTED_QUANTITY")
    private short executedQuantity;

    @Column(name = "MISSED_QUANTITY")
    private short missedQuantity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PATIENT_ID")
    private PatientVO patient;

    @Column(name = "SCHEDULED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MedicineVO getMedicineVO() {
        return medicineVO;
    }

    public void setMedicineVO(MedicineVO medicineVO) {
        this.medicineVO = medicineVO;
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

    public PatientVO getPatient() {
        return patient;
    }

    public void setPatient(PatientVO patient) {
        this.patient = patient;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
