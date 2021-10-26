package com.example.patientenakte.controller;

import com.example.patientenakte.core.Patientenakte;
import com.example.patientenakte.core.PatientenakteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller für das REST-API zum Lesen und Schreiben von Patientenakten
 */
@RestController
public class PatientenakteController {

    private PatientenakteService patientenakteService;

    PatientenakteController(@Autowired PatientenakteService patientenakteService) {
        this.patientenakteService = patientenakteService;
    }

    @GetMapping("/patientenakte")
    public List<Patientenakte> find() {
        return this.patientenakteService.findAll();
    }


    @PostMapping(path = "/patientenakte", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Patientenakte patientenakte) {
        this.patientenakteService.savePatientenakte(patientenakte);
    }

}