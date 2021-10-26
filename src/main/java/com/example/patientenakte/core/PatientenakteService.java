package com.example.patientenakte.core;

import com.example.patientenakte.model.Arzt;
import com.example.patientenakte.model.Befund;
import com.example.patientenakte.model.Patient;
import com.example.patientenakte.repository.ArztRepository;
import com.example.patientenakte.repository.BefundRepository;
import com.example.patientenakte.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Service zm Mappen zwischen REST-API und DB-/Domänenmodell
 */
@Service
public class PatientenakteService {

    @Autowired
    BefundRepository befundRepository;

    @Autowired
    ArztRepository arztRepository;

    @Autowired
    PatientRepository patientRepository;

    public List<Patientenakte> findAll() {
        List result = new ArrayList<Patientenakte>();

        Iterator iterable = befundRepository.findAll().iterator();
        while (iterable.hasNext()) {
            Befund befund = (Befund) iterable.next();

            Patientenakte patientenakte = new Patientenakte();
            patientenakte.setUntersuchungstag(befund.getUntersuchungstag());
            patientenakte.setErgebnis(befund.getErgebnis());

            Arzt arzt = befund.getArzt();
            patientenakte.setArztFachgebiet(arzt.getFachgebiet());
            patientenakte.setArztVorname(arzt.getVorname());
            patientenakte.setArztNachname(arzt.getNachname());

            Patient patient = befund.getPatient();
            patientenakte.setPatientNachname(patient.getNachname());
            patientenakte.setPatientVorname(patient.getVorname());
            patientenakte.setPatientVersichertennummer(patient.getVersichertennummer());

            result.add(patientenakte);
        }
        return result;
    }

    public void savePatientenakte(Patientenakte patientenakte) {

        Arzt arzt = ermittleArzt(patientenakte);
        Patient patient = ermittlePatient(patientenakte);

        arztRepository.save(arzt);
        patientRepository.save(patient);

        Befund befund = new Befund(patientenakte.getUntersuchungstag(), patientenakte.getErgebnis());
        arzt.addBefund(befund);
        patient.addBefund(befund);

        befundRepository.save(befund);


    }

    private Patient ermittlePatient(Patientenakte patientenakte) {
        Patient patient = patientRepository.findByVersichertennummer(patientenakte.getPatientVersichertennummer());
        if (patient == null) {
            // den Patient gibt es noch nicht, dann legen wir ihn an
            patient = new Patient(patientenakte.getPatientNachname(), patientenakte.getPatientVorname(), patientenakte.getPatientVersichertennummer());
        }
        return patient;
    }

    private Arzt ermittleArzt(Patientenakte patientenakte) {
        List<Arzt> arztList = arztRepository.findByNachname(patientenakte.getArztNachname());

        if (arztList.size() == 0) {
            // den Arzt gibt es noch nicht, dann legen wir ihn an
            return new Arzt(patientenakte.getArztNachname(), patientenakte.getArztVorname(), patientenakte.getArztFachgebiet());
        }
        // vereinfacht angenommen kann es nur einen Arzt für diesen Nachnamen geben
        return arztList.get(0);
    }
}
