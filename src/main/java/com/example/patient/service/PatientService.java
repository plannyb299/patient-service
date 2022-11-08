package com.example.patient.service;

import com.example.patient.dao.PatientDao;
import com.example.patient.models.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

	private final PatientDao patientDao;

	public PatientService(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	// General Patient Functions //

	public Patient createPatient(Patient patient) {
		return patientDao.save(patient);
	}


	public Patient getPatientById(Integer id) {
		return patientDao.findByPatientId(id);
	}
	public Patient getPatientByEmail(String email) {
		return patientDao.findByEmail(email);
	}

	public Patient getPatientByIdNumber(String patientIdNumber) {
		return patientDao.findByPatientIDNumber(patientIdNumber);
	}

	public Iterable<Patient> getAllPatients() {
		return patientDao.findAll();
	}

	public void deletePatient(Integer patientId) {
		patientDao.deleteById(patientId);
	}

	// Update Patient Information Functions //

	public Patient updatePatientName(Integer patientId, String newName) {
		Patient patientFromDb = patientDao.findByPatientId(patientId);
		if (patientFromDb == null) return null;
		patientFromDb.setPatientName(newName);
		Patient updatedPatient = patientDao.save(patientFromDb);
		return updatedPatient;
	}

	public Patient updatePatientIDNumber(Integer patientId, String newIDNumber) {
		Patient patientFromDb = patientDao.findByPatientId(patientId);
		if (patientFromDb == null) return null;
		patientFromDb.setPatientIDNumber(newIDNumber);
		Patient updatedPatient = patientDao.save(patientFromDb);
		return updatedPatient;
	}

	public Patient updatePatientInsuredStatus(Integer patientId, boolean newinsuredStatus) {
		Patient patientFromDb = patientDao.findByPatientId(patientId);
		if (patientFromDb == null) return null;
		patientFromDb.setPatientInsured(newinsuredStatus);
		Patient updatedPatient = patientDao.save(patientFromDb);
		return updatedPatient;
	}

	// Get All Patients With Same Diagnosis Function //

	public List<Patient> getAllPatientsWithSameDiagnosis(String diagnosis) {
		Iterable<Patient> allPatients = patientDao.findAll();
		List<Patient> patientsWithSameDiagnosis = new ArrayList<Patient>();
		for (Patient patient : allPatients) {
			if (patient != null) {
				if (patient.getPatientDiagnosis().equalsIgnoreCase(diagnosis)) {
					patientsWithSameDiagnosis.add(patient);
				}
			}
		}
		return patientsWithSameDiagnosis;
	}
}