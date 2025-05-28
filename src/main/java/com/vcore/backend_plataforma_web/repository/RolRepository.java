package com.vcore.backend_plataforma_web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcore.backend_plataforma_web.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer>{
    Rol findByNombre(String nombre);


}
