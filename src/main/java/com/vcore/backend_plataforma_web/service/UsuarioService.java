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
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                if (usuario.getRol() != null &&
                        usuario.getRol().getNombre().equalsIgnoreCase("admin")) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    // METODOS DE ADMIN
    // ADMIN--CREAR USUARIO
    public String almacenar(Usuario usuarioACrear, Usuario usuarioActual) {
        // si usuario actual existe
        if (usuarioActual == null) {
            return "Usuario actual no existe!";
        }

        // Si usuario actual no es "admin"
        if (!usuarioActual.getRol().getNombre().equalsIgnoreCase("admin")) {
            return "Acceso denegado";
        }

        usuarioRepository.save(usuarioACrear);
        return "Usuario almacenado correctamente!";
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    // ADMIN -- ASIGNAR ROL USUARIO
    public String asignarRol(Usuario usuario, Rol rol, Integer id) {
        // verificar el rol si existe
        Rol rolNuevo = rolRepository.findByNombre(rol.getNombre());
        if (rolNuevo == null) {
            return "Rol no valido";
        }
        usuario.setRol(rolNuevo);
        return "Rol asignado!";
    }

    // ADMIN--ACTUALIZAR USUARIO
    public String actualizar(Usuario usuarioActualizar, Usuario usuarioActual, Integer id) {
        if (usuarioActual == null || usuarioActual.getRol() == null) {
            return "Error: Usuario actual inválido";
        }

        if (!usuarioActual.getRol().getNombre().equalsIgnoreCase("admin")) {
            return "Acceso denegado! -- Usuario en @PathVariable no tiene permisos";
        }

        if (usuarioActualizar == null) {
            return "Error: Datos de usuario no proporcionados";
        }

        if (id == null) {
            return "Error: ID de usuario no proporcionado";
        }

        // ENCONTRAMOS AL USUARIO A CAMBIAR
        Usuario usuarioACambiar = usuarioRepository.findById(id).orElse(null);
        if (usuarioACambiar == null) {
            return "Usuario no existe!";
        }
        // Verifica que cada parametro sea distinto a nulo para cambiarlo
        if (usuarioActualizar.getNombre() != null) {
            usuarioACambiar.setNombre(usuarioActualizar.getNombre());
        }
        if (usuarioActualizar.getContrasena() != null) {
            usuarioACambiar.setContrasena(usuarioActualizar.getContrasena());
        }
        if (usuarioActualizar.getEmail() != null) {
            usuarioACambiar.setEmail(usuarioActualizar.getEmail());
        }
        if (usuarioActualizar.getFecha_registro() != null) {
            usuarioACambiar.setFecha_registro(usuarioActualizar.getFecha_registro());
        }

        // verifica que el rol nuevo no sea null y que el nombre del rol no sea null
        if (usuarioActualizar.getRol() != null && usuarioActualizar.getRol().getNombre() != null) {
            // INGRESA EL ROL A ACTUALIZAR A nuevoRol para ver si es null
            Rol nuevoRol = rolRepository.findByNombre(usuarioActualizar.getRol().getNombre());
            // SI NO ES NULL, LE INGRESARA EL ROL A usuario a cambiar
            if (nuevoRol != null) {
                usuarioACambiar.setRol(nuevoRol);
            } else {
                return "Error: El rol especificado no existe";
            }
        }
        usuarioRepository.save(usuarioACambiar);
        return "Usuario actualizado!";

    }

    // ADMIN--DESACTIVAR USUARIO

    // ADMIN--ELIMINAR USUARIO

    // PULIE
    // VERIFICANDO QUE USUARIO SEA PROFE
    // public String validarUsuarioProfe(Integer id) {

    // }
    // PROFE--CREAR CONTENIDO (recurso)
    public String crearContenido(Usuario usuario, Integer id) {
        // Verificar si el usuario es un profesor
        if (usuario == null || usuario.getRol() == null ||
                !usuario.getRol().getNombre().equalsIgnoreCase("profe")) {
            return "Acceso denegado! -- Usuario no es profesor";
        }
        // Lógica para crear contenido
        return "Contenido creado correctamente!";
    }
    // PROFE--ACTUALIZAR CONTENIDO
}
