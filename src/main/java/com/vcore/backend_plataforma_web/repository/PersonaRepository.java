package com.vcore.backend_plataforma_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcore.backend_plataforma_web.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    Persona findByRut(String rut);
}
