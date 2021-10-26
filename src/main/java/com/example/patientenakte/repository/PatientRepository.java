package com.example.patientenakte.repository;

import com.example.patientenakte.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DB-Zugriff für Patient
 */
public interface PatientRepository extends CrudRepository<Patient, Long> {

    Patient findByVersichertennummer(String versichertennummer);
}
