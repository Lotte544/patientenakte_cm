package com.example.patientenakte;

import com.example.patientenakte.model.Arzt;
import com.example.patientenakte.model.Befund;
import com.example.patientenakte.model.Patient;
import com.example.patientenakte.repository.ArztRepository;
import com.example.patientenakte.repository.BefundRepository;
import com.example.patientenakte.repository.PatientRepository;
import com.example.patientenakte.repository.TestdatenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;

/**
 * Einstiegsklasse Spring Boot mit main Methode
 *
 * Es werden gestartet:
 *
 * - Spring data JPA + Hibernate
 * - Apache Tomcat: Open-Source-Webserver / Webcontainer für Servletes implementiert
 * - H2: in Memory relationale Datenbank, welche embedded läuft, d.h. im Gegensatz zu Mysql die Daten nur für die Dauer der Laufzeit behält
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.patientenakte"})
public class PatientenakteApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientenakteApplication.class, args);
    }
}
