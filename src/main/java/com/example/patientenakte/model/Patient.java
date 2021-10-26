package com.example.patientenakte.model;

import com.example.patientenakte.model.Befund;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Ein Patient mit Namen und Versicherungsnummer. Ein Patient bekommt Befunde von einem Arzt
 */
@Data
@Entity
@RequiredArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nachname;

    private String vorname;

    private String versichertennummer;

    /**
     * Die Menge an Befunden, die ein Patient von einem Arzt bekommt, entspricht Tabelle PATIENT in DB
     */
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Befund> befunde = new HashSet<>();

    public void addBefund(Befund befund) {
        befunde.add(befund);
        befund.setPatient(this);
    }

    public void removeBefund(Befund befund) {
        befunde.remove(befund);
        befund.setPatient(null);
    }

    public Patient(String nachname, String vorname, String versichertennummer) {
        this.nachname = nachname;
        this.vorname = vorname;
        this.versichertennummer = versichertennummer;
    }
}
