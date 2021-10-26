package com.example.patientenakte.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * Ein Befund ist das Ergebnis einer Untersuchung von einem Arzt an einem Patient, entspricht Tabelle BEFUND in DB
 * <p>
 * Ein Befund hat ein Ergebnis zu einem bestimmten Untersuchungstermin
 */
@Data
@Entity
@RequiredArgsConstructor
public class Befund {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Arzt arzt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    private LocalDate untersuchungstag;

    private String ergebnis;

    public Befund(LocalDate untersuchungstag, String ergebnis) {
        this.untersuchungstag = untersuchungstag;
        this.ergebnis = ergebnis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Befund)) return false;
        return id != null && id.equals(((Befund) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
