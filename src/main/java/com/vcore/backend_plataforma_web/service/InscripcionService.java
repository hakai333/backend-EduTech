package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Inscripcion;
import com.vcore.backend_plataforma_web.repository.InscripcionRepository;

@Service
public class InscripcionService {
    @Autowired
    private InscripcionRepository inscripcionRepository;

    public String almacenar(Inscripcion inscripcion) {
            inscripcionRepository.save(inscripcion);
        return "Inscripcion ingresada";
    }

    public List<Inscripcion> listar() {
        return inscripcionRepository.findAll();
    }
}
