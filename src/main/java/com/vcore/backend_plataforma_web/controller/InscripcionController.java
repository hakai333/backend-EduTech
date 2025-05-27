package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Inscripcion;
import com.vcore.backend_plataforma_web.service.InscripcionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


//Bastian
@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {
    @Autowired
    private InscripcionService inscripcionService;
    
    @PostMapping
    public String almacenar(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.almacenar(inscripcion);
    }
    
    @GetMapping
    public List <Inscripcion> listar(){
        return inscripcionService.listar();
    }
}
