#Readme für Projekt Patientenakte

##Überblick
* Fortsetzung des Projektes aus Java 1 (und 2) mit folgenden Erweiterungen

* Main Methode startet:
  * Spring data JPA + Hibernate
  * Apache Tomcat: Open-Source-Webserver / Webcontainer für Servletes implementiert
  * H2: in Memory relationale Datenbank, welche embedded läuft, d.h. im Gegensatz zu Mysql die Daten nur für die Dauer der Laufzeit behält


* REST-API zum Lesen und Schreiben von Patientenakten
  * Klasse zur Übertragung der Daten über das REST-API
  * Service zm Mappen zwischen REST-API und DB-/Domänenmodell

* zusätzlich zu Klasse 'Patient' Klasse 'Arzt' und 'Befund' hinzugefügt
  * Arzt - Befund: one-to-many Beziehung / bidirektional
  * Patient - Befund: one-to-many Beziehung / bidirektional
* 3 Tabellen:
  * Interface um DB Zugriff für Klassen Patient, Arzt und Befund zu definieren

* Test-Driven Development:
  * PatientenakteRepositoryTest: testet Repository anhand Name, Vorname etc
    und zeigt mit assert equal, dass repository arbeitet wie erwartet
* Injected Dependencies:
  * org.springframework.boot
  * org.projectlombok
  * spring-boot-starter-test<
  * h2 database
  * junit
* Lombok um Codeeffizienz zu verbessern (autogeneriert getter/setter etc)
  * (@Data/@requiredArgsConstructor)

##Übersicht Code
####controller
+ PatientenakteController
  + Controller für das REST-API zum Lesen und Schreiben von Patientenakten

####core
+ Patientenakte
  +  Diese Klasse dient der Übertragung der Daten über das REST-API. Sie enthält eine Liste von Attributen.
+ PatientenakteService
  +  Service zum Mappen zwischen REST-API und DB-/Domänenmodell


####model

+ Arzt
  + Definiert einen Arzt mit Namen und Fachgebiet. Ein Arzt erstellt Befunde für Patienten, entspricht Tabelle ARZT in DB.
+ Befund
  + Ein Befund ist das Ergebnis einer Untersuchung von einem Arzt an einem Patient, entspricht Tabelle BEFUND in DB.  Ein Befund hat ein Ergebnis zu einem bestimmten Untersuchungstermin.
+ Patient
  +  Ein Patient mit Namen und Versicherungsnummer. Ein Patient bekommt Befunde von einem Arzt

####repository
+ ArztRepository
  + DB-Zugriff für Arzt
+ BefundRepository
  + DB-Zugriff für Befund
+ PatientRepository
  + DB-Zugriff für Patient
+ TestdatenService
  + Service, um wiederkehrende Testdaten (Ärzte, Patienten) anzubieten  
+ Test-Driven Development: 
  + PatientenakteRepositoryTest: testet Repository anhand Name, Vorname etc
    und zeigt mit assert equal, dass repository arbeitet wie erwartet

  


