package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Usuario;
import com.vcore.backend_plataforma_web.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping("/crear/{nombreUsuarioActual}")
    public String almacenar(
        @RequestBody Usuario usuarioAcrear,
        @PathVariable String nombreUsuarioActual
    ) {
        if(usuarioService.buscarAdmin(nombreUsuarioActual)) {
            Usuario usuarioActual = usuarioService.buscarPorNombre(nombreUsuarioActual);
            return usuarioService.almacenar(usuarioAcrear, usuarioActual);
        }
        return "Error";
    }
    
    @GetMapping("/listar") 
    public List<Usuario> listaUsuarios() {
        return usuarioService.listar();
    }
    
    //ACTUALIZAR USUARIOf
    /* @PostMapping("/actualizar/{nombreUsuarioActual}/{idUsuarioActualizar}")
    public String actualizarUsuario(
        @RequestBody Usuario usuarioActualizar,
        @PathVariable String nombreUsuarioActual,
        @PathVariable Integer idUsuarioACambiar
    ) {
        Usuario usuarioActual = usuarioService.buscarPorNombre(nombreUsuarioActual);
        return usuarioService.actualizar(usuarioActualizar, usuarioActual, idUsuarioACambiar);
    }
    */
    
    

}
