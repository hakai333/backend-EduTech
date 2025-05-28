package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vcore.backend_plataforma_web.model.Persona;
import com.vcore.backend_plataforma_web.model.Rol;
import com.vcore.backend_plataforma_web.model.Usuario;
import com.vcore.backend_plataforma_web.repository.PersonaRepository;
import com.vcore.backend_plataforma_web.repository.RolRepository;
import com.vcore.backend_plataforma_web.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PersonaRepository personaRepository;

    UsuarioService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    //MIGUEL REYES
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    //MIGUEL REYES
    public Usuario buscarPorNombre(String nombre) {
        // Modificado para manejar múltiples resultados
        List<Usuario> usuarios = usuarioRepository.findAllByNombre(nombre);
        return usuarios.isEmpty() ? null : usuarios.get(0); // Devuelve el primero o null
    }

    //MIGUEL REYES
    public Boolean buscarAdmin(String nombre) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for(Usuario usuario : usuarios) {
            if(usuario.getNombre().equals(nombre) && 
            usuario.getRol() != null &&
            usuario.getRol().getNombre().equalsIgnoreCase("admin")) {
                return true;
            }           
        }
        return false;
    }

    


    //MIGUEL REYES
    //METODOS DE ADMIN
    //ADMIN--CREAR USUARIO
    public String crearUsuario(Usuario usuarioACrear, Usuario usuarioActual) {
        //si usuario actual existe
        if(usuarioActual == null) {
            return "Usuario actual no existe!";
        }

        //Si usuario actual no es "admin"
        if(!usuarioActual.getRol().getNombre().equalsIgnoreCase("admin")) {
            return "Acceso denegado";
        }
        usuarioACrear.setEsActivo(true);
        usuarioRepository.save(usuarioACrear);
        return "Usuario almacenado correctamente";
    }

    //MIGUEL REYES
    //ADMIN--ACTUALIZAR USUARIO
    public String actualizarUsuario(Usuario usuarioActualizar, Usuario usuarioActual, Integer id) {
        if(usuarioActual == null || usuarioActual.getRol() == null) {
            return "Error: Usuario actual inválido";
        }
        
        if(!usuarioActual.getRol().getNombre().equalsIgnoreCase("admin")) {
            return "Acceso denegado! -- Usuario en @PathVariable no tiene permisos";
        }

        if(usuarioActualizar == null) {
            return "Error: Datos de usuario no proporcionados";
        }

        if (id == null) {
            return "Error: ID de usuario no proporcionado";
        }

        Usuario usuarioACambiar = usuarioRepository.findById(id).orElse(null);
        if(usuarioACambiar == null) {
            return "Usuario no existe!";
        } 
        if(usuarioActualizar.getNombre() != null) {
            usuarioACambiar.setNombre(usuarioActualizar.getNombre());
        }
        if(usuarioActualizar.getContrasena() != null) {
            usuarioACambiar.setContrasena(usuarioActualizar.getContrasena());
        }
        if(usuarioActualizar.getEmail() != null) {
            usuarioACambiar.setEmail(usuarioActualizar.getEmail());
        }
        if(usuarioActualizar.getFecha_registro() != null) {
            usuarioACambiar.setFecha_registro(usuarioActualizar.getFecha_registro());
        }
        if(usuarioActualizar.getEsActivo() != null) {
            usuarioACambiar.setEsActivo(usuarioActualizar.getEsActivo());
        }
        
        //verifica que el rol nuevo no sea null y que el nombre del rol no sea null
        if (usuarioActualizar.getRol() != null && usuarioActualizar.getRol().getNombre() != null) {          
            //INGRESA EL ROL A ACTUALIZAR A nuevoRol para ver si es null
            Rol nuevoRol = rolRepository.findByNombre(usuarioActualizar.getRol().getNombre());  
            //SI NO ES NULL, LE INGRESARA EL ROL A usuario a cambiar
            if (nuevoRol != null) {
            usuarioACambiar.setRol(nuevoRol);
            } else {
                return "Error: El rol especificado no existe";
            }        
        }

        usuarioRepository.save(usuarioACambiar);
        return "Usuario actualizado";
  
    }

    //MIGUEL REYES
    //ADMIN--DESACTIVAR USUARIO
    public String desactivarUsuario(Usuario usuarioActual, Integer idUsuarioDesactivar) {
        Usuario usuarioDesactivar = buscarPorId(idUsuarioDesactivar);
        if(usuarioDesactivar == null) {
            return "Usuario a desactivar no encontrado";
        }

        if (usuarioActual == null || usuarioActual.getRol() == null || 
        !buscarAdmin(usuarioActual.getNombre())) {
        return "Usuario sin permisos";
        }

        usuarioDesactivar.setEsActivo(false);
        usuarioRepository.save(usuarioDesactivar);
        Usuario usuarioVerificado = usuarioRepository.findById(idUsuarioDesactivar).orElse(null);
        if(usuarioVerificado != null && !usuarioVerificado.getEsActivo()) {
            return "Usuario desactivado";
        }
            return "Error al desactivar usuario";
    }

    //MIGUEL REYES
    //ADMIN--ELIMINAR USUARIO
    public String eliminarUsuario(Integer idUsuarioActual, Integer idUsuarioAEliminar) {

    Usuario usuarioActual = usuarioRepository.findById(idUsuarioActual)
        .orElseThrow(() -> new RuntimeException("Usuario actual no encontrado"));
    
    if(!usuarioActual.getRol().getNombre().equalsIgnoreCase("ADMIN")) {
        return "Usuario sin permisos";
    }
    
    Usuario usuarioAEliminar = usuarioRepository.findById(idUsuarioAEliminar)
        .orElse(null);
    
    if(usuarioAEliminar == null) {
        return "Usuario a eliminar no encontrado!";
    }
    
    usuarioRepository.delete(usuarioAEliminar);
    
    if(!usuarioRepository.existsById(idUsuarioAEliminar)) {
        return "Usuario eliminado";
    } else {
        return "Error al eliminar usuario";
    }
}


    //MIGUEL REYES
    //ADMIN -- ASIGNAR ROL USUARIO
    public String asignarRol(Rol rol, Integer idUsuarioActual, Integer idUsuarioAsignar) {
        Usuario usuarioAsignar = usuarioRepository.findById(idUsuarioAsignar).orElse(null);
        Rol rolNuevo = rolRepository.findByNombre(rol.getNombre());
        List<Rol> rolesExistentes = rolRepository.findAll();
    
        boolean rolValido = false;
        for (Rol rolExistente : rolesExistentes) {
            if (rolExistente.getNombre().equalsIgnoreCase(rol.getNombre())) {
                rolValido = true;
                // Asignar el rol encontrado
                usuarioAsignar.setRol(rolExistente);
                usuarioRepository.save(usuarioAsignar);
                break;
            }
        }
    
        if (!rolValido) {
            return "Rol no válido o no existe";
        }

        Usuario usuarioActual = usuarioRepository.findById(idUsuarioActual).orElse(null);
        if(!usuarioActual.getRol().getNombre().equalsIgnoreCase("admin")) {
            return "Acceso denegado! -- Usuario en @PathVariable no tiene permisos";
        }

        usuarioAsignar.setRol(rolNuevo);
        usuarioRepository.save(usuarioAsignar);
        return "Rol asignado";
    }


    //PERSONA   
    public String asignarPersona(Integer idUsuario, Persona persona) { 
        if(!usuarioRepository.existsById(idUsuario)) {
            return "El usuario no existe";
        } else if(!personaRepository.existsById(persona.getId())) {
            return "La persona no existe";
        } else {
            Usuario usuario = usuarioRepository.findById(idUsuario).get();
            usuario.setPersona(persona);
            personaRepository.save(persona);
            return "Usuario asignado"; 
        }       
    }

    public String asignarPersona(Persona dto) { 
        if(!usuarioRepository.existsById(dto.getId())) {
            return "El usuario no existe";
        } else if(!personaRepository.existsById(dto.getId())) {
            return "La persona no existe";
        } else {
            Usuario usuario = usuarioRepository.findById(dto.getId()).get();
            Persona persona = personaRepository.findById(dto.getId()).get();
            usuario.setPersona(persona);
            usuarioRepository.save(usuario);
            return "Usuario asignado"; 
        }       
    }


    //sin validaciones
    public String almacenar(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "Usuario creado exitosamente";
    }



    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }
    
    
    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public List<Rol> listarRol() {
        return rolRepository.findAll();
    }

}
