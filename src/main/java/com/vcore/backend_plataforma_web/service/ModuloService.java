package com.vcore.backend_plataforma_web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Curso;
import com.vcore.backend_plataforma_web.model.Modulo;
import com.vcore.backend_plataforma_web.repository.CursoRepository;
import com.vcore.backend_plataforma_web.repository.ModuloRepository;

//bastian
@Service
public class ModuloService {
    @Autowired
    private ModuloRepository moduloRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public String almacenar(Modulo modulo) {
        if (moduloRepository.findByNombre(modulo.getNombre()) == null) {
            moduloRepository.save(modulo);
            return "Modulo " + modulo.getNombre() + " almacenado correctamente";
        } else {
            return "Modulo " + modulo.getNombre() + " ya se existe.";
        }
    }

    public List<Modulo> listar() {
        return moduloRepository.findAll();
    }

    // asignar Modulo a curso
    public String asignarModulo(int moduloId, int cursoId) {
        if (!moduloRepository.existsById(moduloId)) {
            return "El módulo ingresado no existe";
        }
        if (!cursoRepository.existsById(cursoId)) {
            return "El curso ingresado no existe";
        }

        Modulo modulo = moduloRepository.findById(moduloId).get();
        Curso curso = cursoRepository.findById(cursoId).get();

        if (modulo.getCurso() != null && modulo.getCurso().getId() != cursoId) {
            return "El módulo ya está asignado al curso: " + modulo.getCurso().getNombre();
        }

        if (modulo.getCurso() != null && modulo.getCurso().getId() == cursoId) {
            return "El módulo ya está asignado a este curso";
        }

        modulo.setCurso(curso);

        if (curso.getModulos() == null) {
            curso.setModulos(new ArrayList<>());
        }
        if (!curso.getModulos().contains(modulo)) {
            curso.getModulos().add(modulo);
        }

        moduloRepository.save(modulo);
        cursoRepository.save(curso);

        return "Módulo '" + modulo.getNombre() + "' asignado correctamente al curso '" + curso.getNombre() + "'";
    }
}
