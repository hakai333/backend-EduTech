package com.vcore.backend_plataforma_web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vcore.backend_plataforma_web.model.Persona;
import com.vcore.backend_plataforma_web.service.PersonaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping
    public String almacenar(@RequestBody Persona persona) {
        return personaService.almacenar(persona);
    }

    @GetMapping
    public List<Persona> listar() {
        return personaService.listar();
    }
    
    
    
}
