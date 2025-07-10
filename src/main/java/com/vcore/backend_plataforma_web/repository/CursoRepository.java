package com.vcore.backend_plataforma_web.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vcore.backend_plataforma_web.model.Curso;



public interface CursoRepository extends JpaRepository<Curso, Integer>{
    Curso findByNombre(String nombre);
}
