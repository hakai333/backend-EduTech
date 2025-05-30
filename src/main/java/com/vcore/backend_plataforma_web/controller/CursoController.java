package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vcore.backend_plataforma_web.model.Curso;
import com.vcore.backend_plataforma_web.service.CursoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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

        
    @PostMapping("/lista")
    public ResponseEntity<String> almacenar(@RequestBody List<Curso> cursos) {
        for (Curso curso : cursos) {
            cursoService.almacenar(curso);
        }
        return ResponseEntity.ok("Cursos creados correctamente");
    }

    @PostMapping("/asignarProfesorACurso/{usuarioId}/{cursoId}")
    public String asignarProfesor(@PathVariable int usuarioId, @PathVariable int cursoId){
        return cursoService.asignarProfesor(usuarioId,cursoId);
    }


}
