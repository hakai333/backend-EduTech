package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Modulo;
import com.vcore.backend_plataforma_web.model.Recurso;
import com.vcore.backend_plataforma_web.repository.ModuloRepository;
import com.vcore.backend_plataforma_web.repository.RecursoRepository;

@Service
public class RecursoService {
    @Autowired
    private RecursoRepository recursoRepository;

    @Autowired
    private ModuloRepository moduloRepository;

    public String almacenar(Recurso recurso) {
        if (recursoRepository.findByNombreRecurso(recurso.getNombreRecurso()) == null) {
            recursoRepository.save(recurso);
            return "Recurso " + recurso.getNombreRecurso() + " almacenado correctamente";
        } else {
            return "Recurso " + recurso.getNombreRecurso() + " ya existe.";
        }
    }

    public List<Recurso> listar() {
        return recursoRepository.findAll();
    }

    // asignar un recurso a un modulo
    public String asignarRecursoAModulo(int recursoId, int moduloId) {
        if (!recursoRepository.existsById(recursoId)) {
            return "El recurso ingresado no existe";
        } else if (!moduloRepository.existsById(moduloId)) {
            return "El módulo ingresado no existe";
        } else {
            Recurso recurso = recursoRepository.findById(recursoId).get();
            Modulo modulo = moduloRepository.findById(moduloId).get();

            if (recurso.getModulo() != null && recurso.getModulo().getId() == moduloId) {
                return "El recurso ya está asignado a este módulo";
            }

            recurso.setModulo(modulo);
            recursoRepository.save(recurso);

            return "El recurso '" + recurso.getNombreRecurso() + "' fue asignado correctamente al módulo '"
                    + modulo.getNombre() + "'";
        }
    }
}
