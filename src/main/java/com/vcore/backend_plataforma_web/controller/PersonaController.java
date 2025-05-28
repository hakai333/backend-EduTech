package com.vcore.backend_plataforma_web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vcore.backend_plataforma_web.model.Persona;
import com.vcore.backend_plataforma_web.service.PersonaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping("/asignarUsuario/{rutPersona}/{idUsuario}")
    public String asignarUsuario(@PathVariable String rutPersona, @PathVariable Integer idUsuario) {      
        return personaService.asignarUsuario(rutPersona, idUsuario);
    }

    @GetMapping
    public List<Persona> listar() {
        return personaService.listar();
    }

    @PostMapping
    public String almacenar(@RequestBody Persona persona) {
        return personaService.almacenar(persona);
    }

    

    

    

    

    
    
}
