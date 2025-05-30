package com.vcore.backend_plataforma_web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.DTO.ReporteDTO;
import com.vcore.backend_plataforma_web.repository.CursoRepository;
//bastian
@Service
public class ReporteService {
    
    @Autowired
    private CursoRepository cursoRepository;
    
    public List<ReporteDTO> generarReporteCursos() {
        return cursoRepository.findAll().stream()
            .map(curso -> new ReporteDTO(
                curso.getNombre(),
                curso.getProfesor() != null ? curso.getProfesor().getNombre() : "Sin asignar",
                curso.getInscripciones().size(),
                curso.getInscripciones().stream()
                    .map(inscripcion -> inscripcion.getEstudiante().getNombre())
                    .collect(Collectors.toList())
            ))
            .collect(Collectors.toList());
    }

}