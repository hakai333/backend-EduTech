package com.vcore.backend_plataforma_web.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Rol;
import com.vcore.backend_plataforma_web.model.Usuario;
import com.vcore.backend_plataforma_web.repository.RolRepository;
import com.vcore.backend_plataforma_web.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    public Usuario buscarPorNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    public String almacenar(Usuario usuarioACrear, Usuario usuarioActual) {
        //Si usuario actual no es "admin"
        if(!usuarioActual.getRol().getNombre().equalsIgnoreCase("admin")) {
            return "Acceso denegado";
        }

        //verificar el rol si existe
        Rol rolUsuarioNuevo = rolRepository.findByNombre(usuarioACrear.getRol().getNombre());
        if(rolUsuarioNuevo == null) {
            return "Rol no valido";
        }

        usuarioACrear.setRol(rolUsuarioNuevo);
        usuarioRepository.save(usuarioACrear);
        return "Usuario almacenado correctamente!";
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }
}
