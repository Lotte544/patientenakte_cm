package com.example.patientenakte.repository;

import com.example.patientenakte.model.Arzt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DB-Zugriff für Arzt
 */
public interface ArztRepository extends CrudRepository<Arzt, Long> {

    List<Arzt> findByNachname(String nachname);

    List<Arzt> findByFachgebiet(String fachgebiet);

}
