package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Rol;
import com.vcore.backend_plataforma_web.service.RolService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/roles")
public class RolController {
    @Autowired
    private RolService rolService;

    @PostMapping
    public String almacenar(@RequestBody Rol rol) {
        return rolService.almacenar(rol);
    }
    
    @GetMapping
    public List<Rol> listar() {
        return rolService.listar();
    }

    @GetMapping("/{nombre}")
    public List<Rol> buscar(@PathVariable String nombre) {
        return rolService.buscar(nombre);
    }
    
    
}
