package com.example.patientenakte;

import com.example.patientenakte.model.Arzt;
import com.example.patientenakte.model.Befund;
import com.example.patientenakte.model.Patient;
import com.example.patientenakte.repository.ArztRepository;
import com.example.patientenakte.repository.PatientRepository;
import com.example.patientenakte.repository.TestdatenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Umfangreiche Tests für den DB-Zugriff.
 * <p>
 * Es werden Datensätze in den Tabellen ARZT, PATIENT und BEFUND angelegt, geprüft, on die grundätzliche Abfrage funktioniert.
 * <p>
 * Insbesondere die oneToMany Relationsships zwischen den Tabellen werden abgetestet.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PatientenakteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArztRepository arztRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TestdatenService testdatenService;

    @Before
    public void setup() {
        // vor jedem Test wird die DB geleert
        entityManager.clear();
    }

    @Test
    public void findArztByNachnameOderFachgebiet() {

        entityManager.persist(testdatenService.arzt_schmidt_michael_allgemeinmedizin());
        entityManager.persist(testdatenService.arzt_meier_sybille_InnereMedizin());

        long anzahlAezte = arztRepository.count();
        assertEquals(2, anzahlAezte);

        List<Arzt> arztList = arztRepository.findByNachname("Schmidt");
        Arzt arzt = arztList.get(0);
        assertEquals("Schmidt", arzt.getNachname());

        arztList = arztRepository.findByFachgebiet("Innere Medizin");
        arzt = arztList.get(0);
        assertEquals("Meier", arzt.getNachname());
        assertEquals("Innere Medizin", arzt.getFachgebiet());
    }

    @Test
    public void findPatientByVersichertennummer() {

        entityManager.persist(testdatenService.patient_wurst_emma_123234());
        entityManager.persist(testdatenService.patient_obernberger_anton_654655());

        entityManager.persist(testdatenService.patient_huber_fritz_445656());
        entityManager.persist(testdatenService.patient_semmelstuber_maria_113454());

        long anzahlPatienten = patientRepository.count();
        assertEquals(4, anzahlPatienten);

        Patient patient = patientRepository.findByVersichertennummer("654655");
        assertEquals("Obernberger", patient.getNachname());
        assertEquals("654655", patient.getVersichertennummer());
    }

    @Test
    public void findBefunde() {

        Arzt meier = testdatenService.arzt_meier_sybille_InnereMedizin();
        Patient huber = testdatenService.patient_huber_fritz_445656();

        Befund meierhuber = new Befund(LocalDate.of(2021, 02, 20), "Schnupfen und Husten");
        meier.addBefund(meierhuber);
        huber.addBefund(meierhuber);

        entityManager.persist(meier);
        entityManager.persist(huber);
        entityManager.persist(meierhuber);

        Set<Befund> befunde = arztRepository.findByNachname("Meier").get(0).getBefunde();
        assertEquals(1, befunde.size());
        assertBefund(befunde.iterator().next());

        befunde = patientRepository.findByVersichertennummer("445656").getBefunde();
        assertEquals(1, befunde.size());
        assertBefund(befunde.iterator().next());
    }

    private void assertBefund(Befund befund) {
        assertEquals("Schnupfen und Husten", befund.getErgebnis());
        assertEquals(LocalDate.of(2021, 02, 20), befund.getUntersuchungstag());
        assertEquals("Huber", befund.getPatient().getNachname());
        assertEquals("Meier", befund.getArzt().getNachname());
    }
}
