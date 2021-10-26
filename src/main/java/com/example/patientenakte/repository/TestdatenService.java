package com.example.patientenakte.repository;

import com.example.patientenakte.model.Arzt;
import com.example.patientenakte.model.Patient;
import org.springframework.stereotype.Service;

/**
 * Service, um wiederkehrende Testdaten (Ärzte, Patienten) anzubieten
 */
@Service
public class TestdatenService {

    public Arzt arzt_schmidt_michael_allgemeinmedizin() {
        return new Arzt("Schmidt", "Michael", "Allgemeinmedizin");
    }

    public Arzt arzt_meier_sybille_InnereMedizin() {
        return new Arzt("Meier", "Sybiller", "Innere Medizin");
    }

    public Patient patient_wurst_emma_123234() {
        return new Patient("Wurst", "Emma", "123234");
    }

    public Patient patient_huber_fritz_445656() {
        return new Patient("Huber", "Fritz", "445656");
    }

    public Patient patient_obernberger_anton_654655() {
        return new Patient("Obernberger", "Anton", "654655");
    }

    public Patient patient_semmelstuber_maria_113454() {
        return new Patient("Semmelstuber", "Maria", "113454");
    }


}
