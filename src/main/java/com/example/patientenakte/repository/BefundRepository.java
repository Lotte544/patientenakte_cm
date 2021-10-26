package com.example.patientenakte.repository;

import com.example.patientenakte.model.Befund;
import org.springframework.data.repository.CrudRepository;

/**
 * DB-Zugriff für Befund
 */
public interface BefundRepository extends CrudRepository<Befund, Long> {
}
