package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/test")
    public String test(@RequestBody Usuario usuario) {
        return "OK: " + usuario.getNombre();
    }

    //CREAR USUARIO
    @PostMapping("/crear/{idUsuarioActual}")
    public String crearUsuario(
        @RequestBody Usuario usuarioAcrear,
        @PathVariable Integer idUsuarioActual
    ) {
        Usuario usuarioActual = usuarioService.buscarPorId(idUsuarioActual);
        if(usuarioService.buscarAdmin(usuarioActual.getNombre())) {
            return usuarioService.crearUsuario(usuarioAcrear, usuarioActual);
        }
        return "Error, usuario no creado!";
    }

    //LISTAR
    @GetMapping 
    public List<Usuario> listaUsuarios() {
        return usuarioService.listar();
    }
    
    //ACTUALIZAR USUARIO
    @PostMapping("/actualizar/{idUsuarioActual}/{idUsuarioActualizar}")
    public String actualizarUsuario(
        @RequestBody Usuario usuarioActualizar,
        @PathVariable Integer idUsuarioActual,
        @PathVariable Integer idUsuarioActualizar
    ) {
        Usuario usuarioActual = usuarioService.buscarPorId(idUsuarioActual);
        if(usuarioService.buscarAdmin(usuarioActual.getNombre())) {
            return usuarioService.actualizarUsuario(usuarioActualizar, usuarioActual, idUsuarioActualizar);
        }
        return "Error: usuario no actualizado!";
        
    }

    @PostMapping("/desactivar/{idUsuarioActual}/{idUsuarioDesactivar}")
    public ResponseEntity<String> desactivarUsuario(
        @PathVariable Integer idUsuarioActual,
        @PathVariable Integer idUsuarioDesactivar
    ) {
    // Obtener usuario actual (quien ejecuta la acción)
    Usuario usuarioActual = usuarioService.buscarPorId(idUsuarioActual);
    
    // Llamar al service con parámetros en orden correcto
    String resultado = usuarioService.desactivarUsuario(usuarioActual, idUsuarioDesactivar);
    
    if(resultado.equals("Usuario desactivado")) {
        return ResponseEntity.ok("Usuario con ID "+ idUsuarioDesactivar + " desactivado correctamente!");
    }
    return ResponseEntity.badRequest().body(resultado);
    }
    


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
   
    
    

}
