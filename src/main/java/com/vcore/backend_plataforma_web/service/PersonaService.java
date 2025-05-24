package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Persona;
import com.vcore.backend_plataforma_web.repository.PersonaRepository;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public String almacenar(Persona persona) {
        Persona validacion = personaRepository.findByRut(persona.getRut());
        if(validacion == null) {
            personaRepository.save(persona);
            return "Persona almacenada correctamente!";
        } else {
            return "Persona ya ingresada!";
        }
    }

    public List<Persona> listar() {
        return personaRepository.findAll();
    }



}
