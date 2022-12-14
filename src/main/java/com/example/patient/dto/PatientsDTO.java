package com.example.patient.dto;

import com.example.patient.models.Patient;

public class PatientsDTO {
	private Iterable<Patient> patients;

	public PatientsDTO() {

	}

	public PatientsDTO(Iterable<Patient> patients) {
		this.patients = patients;
	}

	public void setPatients(Iterable<Patient> patients) {
		this.patients = patients;
	}

	public Iterable<Patient> getPatients() {
		return this.patients;
	}
}