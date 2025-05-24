package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Incidencia;
import com.vcore.backend_plataforma_web.repository.IncidenciaRepository;

@Service
public class IncidenciaService {
    @Autowired
    private IncidenciaRepository incidenciaRepository;

    public String almacenar(Incidencia incidencia) {
            incidenciaRepository.save(incidencia);
        return "Incidencia ingresada";
    }

    public List<Incidencia> listar() {
        return incidenciaRepository.findAll();
    }

}
