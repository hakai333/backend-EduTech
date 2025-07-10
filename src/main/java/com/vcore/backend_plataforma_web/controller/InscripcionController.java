package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Inscripcion;
import com.vcore.backend_plataforma_web.service.InscripcionService;
<<<<<<< HEAD
=======

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

>>>>>>> basti
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

<<<<<<< HEAD

//Bastian
@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {
    @Autowired
    private InscripcionService inscripcionService;
    
    @PostMapping
    public String almacenar(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.almacenar(inscripcion);
    }
    
    @GetMapping
    public List <Inscripcion> listar(){
=======
@RestController
@Tag(name = "Inscripciones", description = "Operaciones relacionadas a las inscripciones de los usuarios a cursos en EDUTECH.")
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping
    @Operation(summary = "Crea una inscripción y la registra en la base de datos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inscripción registrada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    public String almacenar(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.almacenar(inscripcion);
    }

    @GetMapping
    @Operation(summary = "Muestra una lista de las inscripciones registradas en la base de datos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de inscripciones obtenida correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Inscripcion.class))),
        @ApiResponse(responseCode = "500", description = "Error al obtener las inscripciones", content = @Content)
    })
    public List<Inscripcion> listar() {
>>>>>>> basti
        return inscripcionService.listar();
    }

    @PostMapping("/asignarCursoAInscripcion/{cursoId}/{inscripcionId}")
<<<<<<< HEAD
        public String inscripcionCurso(@PathVariable int cursoId, @PathVariable int inscripcionId){
            return inscripcionService.inscripcionCurso(cursoId,inscripcionId);
        }

    @PostMapping("/asignarEstudianteAInscripcion/{usuarioId}/{inscripcionId}")
    public String inscripcionEstudiante(@PathVariable int usuarioId, @PathVariable int inscripcionId){
        return inscripcionService.inscripcionEstudiante(usuarioId,inscripcionId);
=======
    @Operation(summary = "Asigna un curso a una inscripción existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso asignado correctamente a la inscripción"),
        @ApiResponse(responseCode = "404", description = "Curso o inscripción no encontrado", content = @Content)
    })
    public String inscripcionCurso(
        @Parameter(description = "ID del curso a asignar", required = true) @PathVariable int cursoId,
        @Parameter(description = "ID de la inscripción", required = true) @PathVariable int inscripcionId) {
        return inscripcionService.inscripcionCurso(cursoId, inscripcionId);
    }

    @PostMapping("/asignarEstudianteAInscripcion/{usuarioId}/{inscripcionId}")
    @Operation(summary = "Asigna un estudiante a una inscripción existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estudiante asignado correctamente a la inscripción"),
        @ApiResponse(responseCode = "404", description = "Usuario o inscripción no encontrado", content = @Content)
    })
    public String inscripcionEstudiante(
        @Parameter(description = "ID del estudiante (usuario)", required = true) @PathVariable int usuarioId,
        @Parameter(description = "ID de la inscripción", required = true) @PathVariable int inscripcionId) {
        return inscripcionService.inscripcionEstudiante(usuarioId, inscripcionId);
>>>>>>> basti
    }
}
