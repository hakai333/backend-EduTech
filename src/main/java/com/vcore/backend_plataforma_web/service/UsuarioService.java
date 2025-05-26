package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vcore.backend_plataforma_web.controller.UsuarioController;
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

    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario buscarPorNombre(String nombre) {
        // Modificado para manejar múltiples resultados
        List<Usuario> usuarios = usuarioRepository.findAllByNombre(nombre);
        return usuarios.isEmpty() ? null : usuarios.get(0); // Devuelve el primero o null
    }

    public Boolean buscarAdmin(String nombre) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for(Usuario usuario : usuarios) {
            if(usuario.getNombre().equals(nombre)) {
                if( usuario.getRol() != null &&
                    usuario.getRol().getNombre().equalsIgnoreCase("admin")) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }



    //METODOS DE ADMIN
    //ADMIN--CREAR USUARIO
    public String almacenar(Usuario usuarioACrear, Usuario usuarioActual) {
        //si usuario actual existe
        if(usuarioActual == null) {
            return "Usuario actual no existe!";
        }

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


    

    //ADMIN--ACTUALIZAR USUARIO
    public String actualizar(Usuario usuarioActualizar, Usuario usuarioActual, int id) {
        if(!usuarioActual.getRol().getNombre().equalsIgnoreCase("admin")) {
            return "Acceso denegado! -- Usuario en @PathVariable no tiene permisos";
        }

        //ENCONTRAMOS AL USUARIO A CAMBIAR
        Usuario usuarioACambiar = usuarioRepository.findById(id).orElse(null);
        if(usuarioACambiar == null) {
            return "Usuario no existe!";
        } else {
            usuarioACambiar.setNombre(usuarioActualizar.getNombre());
            usuarioACambiar.setContrasena(usuarioActualizar.getContrasena());
            usuarioACambiar.setEmail(usuarioActualizar.getEmail());
            usuarioACambiar.setFecha_registro(usuarioActualizar.getFecha_registro());
            usuarioRepository.save(usuarioACambiar);
            return "Usuario actualizado!";
        }
        

        
    }
   

}
