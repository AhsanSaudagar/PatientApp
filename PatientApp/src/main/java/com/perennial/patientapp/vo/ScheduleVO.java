package com.perennial.patientapp.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SCHEDULE")
public class ScheduleVO implements IGenericVO {

    @Column(name = "SCHEDULED_QUANTITY")
    private int scheduledQuantity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private MedicineVO medicineVO;
    @Column(name = "EXECUTED_QUANTITY")
    private int executedQuantity;
    @Column(name = "MISSED_QUANTITY")
    private int missedQuantity;

    public ScheduleVO(MedicineVO medicineVO, int scheduledQuantity, int executedQuantity, int missedQuantity, PatientVO patient, Date scheduledTime) {
        this.medicineVO = medicineVO;
        this.scheduledQuantity = scheduledQuantity;
        this.executedQuantity = executedQuantity;
        this.missedQuantity = missedQuantity;
        this.patient = patient;
        this.scheduledTime = scheduledTime;
    }

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
