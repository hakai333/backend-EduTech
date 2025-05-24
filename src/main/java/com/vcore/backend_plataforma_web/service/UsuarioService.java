package com.vcore.backend_plataforma_web.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Usuario;
import com.vcore.backend_plataforma_web.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String almacenar(Usuario usuario) {
       Usuario validacion = usuarioRepository.findByNombre(usuario.getNombre());
        if(validacion == null) {
            usuarioRepository.save(usuario);
            return "Rol almacenado correctamente!";
        } else {
            return "Rol ya ingresado!";
        }
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }
}
