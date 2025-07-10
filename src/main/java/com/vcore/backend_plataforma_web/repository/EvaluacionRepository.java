package com.vcore.backend_plataforma_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vcore.backend_plataforma_web.model.Evaluacion;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {
    public Evaluacion findByNombreEvaluacion(String nombreEvaluacion);
}
