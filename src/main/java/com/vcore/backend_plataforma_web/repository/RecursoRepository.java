package com.vcore.backend_plataforma_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcore.backend_plataforma_web.model.Recurso;

public interface RecursoRepository extends JpaRepository<Recurso, Integer> {
    Recurso findByNombreRecurso(String nombreRecurso);
}
