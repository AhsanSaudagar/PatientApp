package com.perennial.patientapp.vo;

import javax.persistence.*;

@Entity
@Table(name = "DISEASE")
public class DiseaseVO implements IGenericVO{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;


    @Column(name = "DISEASE_NAME")
    private String diseaseName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }
}
