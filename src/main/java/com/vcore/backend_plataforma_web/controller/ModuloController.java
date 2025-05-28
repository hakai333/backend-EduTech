package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Modulo;
import com.vcore.backend_plataforma_web.service.ModuloService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/modulos")
public class ModuloController {
    @Autowired
    private ModuloService moduloService;

    @PostMapping
    public String almacenar(@RequestBody Modulo modulo){
        return moduloService.almacenar(modulo);
    }

    @GetMapping
    public List<Modulo> listar (){
        return moduloService.listar();
    }

    @PostMapping("/asignarModuloACurso/{moduloId}/{cursoId}")
    public String asignarModulo(@PathVariable int moduloId, @PathVariable int cursoId) {
        return moduloService.asignarModulo(moduloId, cursoId);
    }

    

}
