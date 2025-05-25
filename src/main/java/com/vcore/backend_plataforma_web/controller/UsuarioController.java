package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/crear/{nombreUsuarioActual}")
    public String crearUsuario(
        @RequestBody Usuario usuarioAcrear,
        @PathVariable String nombreUsuarioActual
    ) {
        Usuario usuarioActual = usuarioService.buscarPorNombre(nombreUsuarioActual);
        if(usuarioActual == null) {
            return "Usuario actual no existe!";
        }
        
        return usuarioService.almacenar(usuarioAcrear, usuarioActual);
    }
    
    @GetMapping("/listar")
    public List<Usuario> listaUsuarios() {
        return usuarioService.listar();
    }


}
