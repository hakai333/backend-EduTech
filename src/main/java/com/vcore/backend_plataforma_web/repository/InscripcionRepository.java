package com.vcore.backend_plataforma_web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcore.backend_plataforma_web.model.Curso;
import com.vcore.backend_plataforma_web.model.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer>{
        List<Inscripcion> findByCurso(Curso curso);

}
