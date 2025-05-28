package com.vcore.backend_plataforma_web.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.vcore.backend_plataforma_web.model.Modulo;

public interface ModuloRepository extends JpaRepository<Modulo, Integer>{
    Modulo findByNombre(String nombre);


}
