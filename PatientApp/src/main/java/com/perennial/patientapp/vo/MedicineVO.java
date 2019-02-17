package com.perennial.patientapp.vo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MEDICINE_MASTER")
public class MedicineVO implements IGenericVO {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "createdAt")
	private String createdAt;

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

	public Boolean isActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
