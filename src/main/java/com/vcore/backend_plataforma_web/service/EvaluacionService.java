package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Evaluacion;
import com.vcore.backend_plataforma_web.repository.EvaluacionRepository;

@Service
public class EvaluacionService {
    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public String almacenar(Evaluacion evaluacion) {
        if (evaluacionRepository.findByNombreEvaluacion(evaluacion.getNombreEvaluacion()) == null) {
            evaluacionRepository.save(evaluacion);
            return "Evaluación " + evaluacion.getNombreEvaluacion() + " almacenada correctamente";
        } else {
            return "Evaluación " + evaluacion.getNombreEvaluacion() + " ya existe.";
        }
    }

    public List<Evaluacion> listar() {
        return evaluacionRepository.findAll();
    }
}
