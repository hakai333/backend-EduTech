package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vcore.backend_plataforma_web.model.Rol;
import com.vcore.backend_plataforma_web.model.Usuario;
import com.vcore.backend_plataforma_web.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    

    //MIGUEL REYES
    //CREAR USUARIO SERVICE
    @PostMapping("/crear/{idUsuarioActual}")
    public ResponseEntity<String> crearUsuario(
        @RequestBody Usuario usuarioAcrear,
        @PathVariable Integer idUsuarioActual
    ) {
        Usuario usuarioActual = usuarioService.buscarPorId(idUsuarioActual);
        String resultado = usuarioService.crearUsuario(usuarioAcrear, usuarioActual);
        if(resultado.equals("Usuario almacenado correctamente")) {
            return ResponseEntity.ok("Usuario con ID " + usuarioAcrear.getId() + " creado correctamente!");
        }
        return ResponseEntity.badRequest().body(resultado);
    }

    //MIGUEL REYES
    //ACTUALIZAR USUARIO SERVICE
    @PostMapping("/actualizar/{idUsuarioActual}/{idUsuarioActualizar}")
    public ResponseEntity<String> actualizarUsuario(
        @RequestBody Usuario usuarioActualizar,
        @PathVariable Integer idUsuarioActual,
        @PathVariable Integer idUsuarioActualizar
    ) {
        Usuario usuarioActual = usuarioService.buscarPorId(idUsuarioActual);
        String resultado = usuarioService.actualizarUsuario(usuarioActualizar, usuarioActual, idUsuarioActualizar);
        if(resultado.equals("Usuario actualizado")) {
            return ResponseEntity.ok("Usuario con ID "+ idUsuarioActualizar + " actualizado correctamente!");
        }
        return ResponseEntity.badRequest().body(resultado);
        
    }

    //MIGUEL REYES
    //DESACTIVAR USUARIO SERVICE
    @PostMapping("/desactivar/{idUsuarioActual}/{idUsuarioDesactivar}")
    public ResponseEntity<String> desactivarUsuario(
        @PathVariable Integer idUsuarioActual,
        @PathVariable Integer idUsuarioDesactivar
    ) {

    Usuario usuarioActual = usuarioService.buscarPorId(idUsuarioActual);
    
    String resultado = usuarioService.desactivarUsuario(usuarioActual, idUsuarioDesactivar);
    
    if(resultado.equals("Usuario desactivado")) {
        return ResponseEntity.ok("Usuario con ID "+ idUsuarioDesactivar + " desactivado correctamente!");
    }
    return ResponseEntity.badRequest().body(resultado);
    }
    

    //MIGUEL REYES
    //ELIMINAR USUARIO SERVICE
    @DeleteMapping("/eliminar/{idUsuarioActual}/{idUsuarioAEliminar}")
    public ResponseEntity<String> eliminarUsuario(
        @PathVariable Integer idUsuarioActual,
        @PathVariable Integer idUsuarioAEliminar
    ) {
        String resultado = usuarioService.eliminarUsuario(idUsuarioActual, idUsuarioAEliminar);
    
        if(resultado.equals("Usuario eliminado")) {
            return ResponseEntity.ok("Usuario con ID "+ idUsuarioAEliminar + " eliminado correctamente!");
        } else {
            return ResponseEntity.badRequest().body(resultado);
        }       
    }
    @GetMapping 
    public List<Usuario> listaUsuarios() {
        return usuarioService.listar();
    }

    //MIGUEL REYES
    //ASIGAR ROL O PERMISOS
    @PostMapping("/asignar-rol/{idUsuarioActual}/{idUsuarioAsignar}")
    public ResponseEntity<String> asignarUsuario(
        @RequestBody Rol rol,
        @PathVariable Integer idUsuarioActual,
        @PathVariable Integer idUsuarioAsignar
    ) {    
        if (rol == null || rol.getNombre() == null) {
            return ResponseEntity.badRequest().body("Debe proporcionar un rol válido");
        }
        String resultado = usuarioService.asignarRol(rol, idUsuarioActual, idUsuarioAsignar);
        if(resultado.equals("Rol asignado")) {
            return ResponseEntity.ok("Usuario con ID "+ idUsuarioAsignar + " asigando correctamente!");
        } else {
            return ResponseEntity.badRequest().body(resultado);
        }
    }
 

}
