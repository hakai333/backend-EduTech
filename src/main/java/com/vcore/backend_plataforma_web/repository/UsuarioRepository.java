package com.vcore.backend_plataforma_web.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcore.backend_plataforma_web.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    List<Usuario> findAllByNombre(String nombre);

}   
