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
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@Tag(name = "Cursos", description = "Operaciones CRUD para la gestión de cursos en la plataforma EDUTECH")    
@RequestMapping("/cursos")
public class CursoController {
=======

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@Tag(name = "Cursos", description = "Operaciones CRUD para la gestión de cursos en la plataforma EDUTECH")    
@RequestMapping("/cursos")
public class CursoController {
    
    @Autowired
    private CursoService cursoService;

    @PostMapping 
    @Operation(summary = "Crear un nuevo curso", 
               description = "Registra un nuevo curso en el sistema con los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos del curso inválidos")
    })
    public String almacenar(
            @Parameter(description = "Objeto Curso con los datos a registrar", required = true)
            @RequestBody Curso curso) {
        return cursoService.almacenar(curso);
    }

    @GetMapping
<<<<<<< HEAD
    public List<Curso>listar() {
        return cursoService.listar();
    }

    @PostMapping("/lista")
    public ResponseEntity<String> almacenar(@RequestBody List<Curso> cursos) {
=======
    @Operation(summary = "Obtener todos los cursos", 
               description = "Retorna una lista con todos los cursos registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida exitosamente")
    public List<Curso> listar() {
        return cursoService.listar();
    }

    @PostMapping("/lista")
    @Operation(summary = "Crear múltiples cursos", 
               description = "Registra una lista de cursos en el sistema en una sola operación")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cursos creados exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de algún curso inválidos")
    })
    public ResponseEntity<String> almacenar(
            @Parameter(description = "Lista de objetos Curso a registrar", required = true)
            @RequestBody List<Curso> cursos) {
        for (Curso curso : cursos) {
            cursoService.almacenar(curso);
        }
        return ResponseEntity.ok("Cursos creados correctamente");
    }

    @PostMapping("/asignarProfesorACurso/{usuarioId}/{cursoId}")
    @Operation(summary = "Asignar profesor a curso", 
               description = "Asocia un profesor existente a un curso específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profesor asignado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Profesor o curso no encontrado")
    })
    public String asignarProfesor(
            @Parameter(description = "ID del usuario profesor", required = true, example = "123")
            @PathVariable int usuarioId,
            @Parameter(description = "ID del curso a asignar", required = true, example = "456")
            @PathVariable int cursoId) {
        return cursoService.asignarProfesor(usuarioId, cursoId);
    }
}