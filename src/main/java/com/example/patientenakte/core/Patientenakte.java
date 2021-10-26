package com.example.patientenakte.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Diese Klasse dient der Übertragung der Daten über das REST-API. Sie enthält eine flachgeklopfte Liste von Attributen.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patientenakte {

    private String arztNachname;

    private String arztVorname;

    private String arztFachgebiet;

    private String patientNachname;

    private String patientVorname;

    private String patientVersichertennummer;

    private LocalDate untersuchungstag;

    private String ergebnis;
}
