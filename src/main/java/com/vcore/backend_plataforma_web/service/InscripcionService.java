package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vcore.backend_plataforma_web.model.Curso;
import com.vcore.backend_plataforma_web.model.Inscripcion;
import com.vcore.backend_plataforma_web.repository.CursoRepository;
import com.vcore.backend_plataforma_web.repository.InscripcionRepository;

//bastian
@Service
public class InscripcionService {
    @Autowired
    private InscripcionRepository inscripcionRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public String almacenar(Inscripcion inscripcion) {
            inscripcionRepository.save(inscripcion);
        return "Inscripcion exitosa!";
    }

    public List<Inscripcion> listar() {
        return inscripcionRepository.findAll();
    }

//inscribir un curso
    public String inscripcionCurso(int cursoId, int inscripcionId){
        if(!cursoRepository.existsById(cursoId)){
            return "El curso ingresado no existe";
        } else if(!inscripcionRepository.existsById(inscripcionId)){
            return "La inscripcion aun no ha sido creada";
        } else {
            Curso curso = cursoRepository.findById(cursoId).get();
            Inscripcion inscripcion = inscripcionRepository.findById(inscripcionId).get();

            inscripcion.setCurso(curso);
            inscripcionRepository.save(inscripcion);

            return "Curso "+ curso.getNombre() +
            " asignado correctamente a inscripcion: " + inscripcion.getId();
        }
    }
}
