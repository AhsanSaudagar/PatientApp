package com.perennial.patientapp.vo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class PatientVO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;


	@Column(name = "name")
	private String name;

	@Column(name = "MOBILE_NUMBER")
	private String mobileNo;

	@Column(name = "EMAIL_ADDRESS")
	private String emailAddress;

	@OneToMany
	@Column(name = "DISEASE_ID")
	private List<String> diseases;

	@Column(name = "GUARDIAN_NAME")
	private String guardianName;

	@Column(name = "GUARDIAN_MOBILE_NUMBER")
	private String guardianMobileNumber;


	@Column(name = "GUARDIAN_NAME")
	private String guardianName;



}
