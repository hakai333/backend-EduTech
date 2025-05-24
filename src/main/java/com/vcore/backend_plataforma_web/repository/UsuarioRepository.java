package com.vcore.backend_plataforma_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcore.backend_plataforma_web.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Usuario findByNombre(String nombre);
}
