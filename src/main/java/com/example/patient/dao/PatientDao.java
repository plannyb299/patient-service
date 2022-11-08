package com.example.patient.dao;

import com.example.patient.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientDao extends JpaRepository<Patient, Integer> {
	Patient findByPatientIDNumber(String patientIDNumber);

	Patient findByEmail(String email);

	Patient findByPatientId(Integer id);
}