package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Curso;
import com.vcore.backend_plataforma_web.service.CursoService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping 
    public String almacenar(@RequestBody Curso curso){
        return cursoService.almacenar(curso);
    }

    @GetMapping
    public List<Curso>listar() {
        return cursoService.listar();
    }

}
