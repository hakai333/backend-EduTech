package com.vcore.backend_plataforma_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcore.backend_plataforma_web.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer>{
    Categoria findByNombre(String nombre);

}
