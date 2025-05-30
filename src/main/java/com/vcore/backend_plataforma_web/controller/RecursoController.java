package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Recurso;
import com.vcore.backend_plataforma_web.service.RecursoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/recursos")
public class RecursoController {
    @Autowired
    private RecursoService recursoService;

    @PostMapping
    public String almacenar(@RequestBody Recurso recurso) {
        return recursoService.almacenar(recurso);
    }

    @GetMapping
    public List<Recurso> listar() {
        return recursoService.listar();
    }

    @PostMapping("/asignarRecursoAModulo/{recursoId}/{moduloId}")
    public String asignarRecursoAModulo(@PathVariable int recursoId, @PathVariable int moduloId) {
        return recursoService.asignarRecursoAModulo(recursoId, moduloId);
    }
}
