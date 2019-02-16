package com.perennial.patientapp.vo;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "MEDICINE")
public class MedicineVO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	@Column(name = "name")
	private String name;

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
}
