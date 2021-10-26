package com.example.patientenakte.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Ein Arzt mit Namen und Fachgebiet. Ein Arzt erstellt Befunde für Patienten, entspricht Tabelle ARZT in DB
 */
@Data
@Entity
@RequiredArgsConstructor
public class Arzt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nachname;

    private String vorname;

    private String fachgebiet;

    /**
     * Die Menge an Befunden, die ein Arzt für Patienten erstellt
     */
    @OneToMany(mappedBy = "arzt", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Befund> befunde = new HashSet<>();

    public void addBefund(Befund befund) {
        befunde.add(befund);
        befund.setArzt(this);
    }

    public void removeBefund(Befund befund) {
        befunde.remove(befund);
        befund.setArzt(null);
    }

    public Arzt(String nachname, String vorname, String fachgebiet) {
        this.nachname = nachname;
        this.vorname = vorname;
        this.fachgebiet = fachgebiet;
    }
}