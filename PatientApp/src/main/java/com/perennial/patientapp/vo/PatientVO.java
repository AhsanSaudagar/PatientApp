package com.perennial.patientapp.vo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PATIENTS")
public class PatientVO implements IGenericVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PATIENT_ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MOBILE")
    private String mobileNo;

    @Column(name = "EMAIL")
    private String emailAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "DISEASE_ID")
    private List<DiseaseVO> diseases;

    @Column(name = "GUARDIAN_NAME")
    private String guardianName;

    @Column(name = "GUARDIAN_NO")
    private String guardianMobileNumber;


    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "patient")
//    private List<ScheduleVO> scheduledMedicines;

    @Column(name = "AGE")
    private short age;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "pid")
    private String pid;

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<DiseaseVO> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<DiseaseVO> diseases) {
        this.diseases = diseases;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianMobileNumber() {
        return guardianMobileNumber;
    }

    public void setGuardianMobileNumber(String guardianMobileNumber) {
        this.guardianMobileNumber = guardianMobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
