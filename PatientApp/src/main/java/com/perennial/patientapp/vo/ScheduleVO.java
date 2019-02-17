package com.perennial.patientapp.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PATIENTS_SCHEDULE")
public class ScheduleVO implements IGenericVO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SCHEDULE_ID")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="MEDICINE_ID", nullable=false)
    private MedicineVO medicineVO;

    public Boolean getActive() {
        return isActive;
    }

    public int getScheduledQuantity() {
        return scheduledQuantity;
    }

    public void setScheduledQuantity(int scheduledQuantity) {
        this.scheduledQuantity = scheduledQuantity;
    }

    public ScheduleVO(MedicineVO medicineVO, int scheduledQuantity, PatientVO patient, Date scheduledTime) {
        this.medicineVO = medicineVO;
        this.patient = patient;
        this.scheduledTime = scheduledTime;
        this.scheduledQuantity = scheduledQuantity;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PATIENT_ID")
    private PatientVO patient;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduledTime;

    @Column(name = "QTY")
    private int scheduledQuantity;

    public ScheduleVO() {
    }

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

    public PatientVO getPatient() {
        return patient;
    }

    public void setPatient(PatientVO patient) {
        this.patient = patient;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
