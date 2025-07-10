package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Recurso;
import com.vcore.backend_plataforma_web.service.RecursoService;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
=======

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.web.bind.annotation.PostMapping;
>>>>>>> basti
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
<<<<<<< HEAD
@RequestMapping("/recursos")
public class RecursoController {
=======
@Tag(name = "Recursos", description = "Operaciones relacionadas con los recursos disponibles para los módulos de cada curso")
@RequestMapping("/recursos")
public class RecursoController {

>>>>>>> basti
    @Autowired
    private RecursoService recursoService;

    @PostMapping
<<<<<<< HEAD
    public String almacenar(@RequestBody Recurso recurso) {
=======
    @Operation(summary = "Crea un nuevo recurso y lo registra en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
    })
    public String almacenar(
        @RequestBody(description = "Recurso a almacenar", required = true,
                     content = @Content(schema = @Schema(implementation = Recurso.class)))
        @org.springframework.web.bind.annotation.RequestBody Recurso recurso) {
>>>>>>> basti
        return recursoService.almacenar(recurso);
    }

    @GetMapping
<<<<<<< HEAD
=======
    @Operation(summary = "Obtiene la lista de todos los recursos registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de recursos obtenida correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Recurso.class))),
        @ApiResponse(responseCode = "500", description = "Error al obtener los recursos", content = @Content)
    })
>>>>>>> basti
    public List<Recurso> listar() {
        return recursoService.listar();
    }

    @PostMapping("/asignarRecursoAModulo/{recursoId}/{moduloId}")
<<<<<<< HEAD
    public String asignarRecursoAModulo(@PathVariable int recursoId, @PathVariable int moduloId) {
=======
    @Operation(summary = "Asigna un recurso existente a un módulo de un curso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recurso asignado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Recurso o módulo no encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno al asignar recurso", content = @Content)
    })
    public String asignarRecursoAModulo(
        @Parameter(description = "ID del recurso a asignar", required = true) @PathVariable int recursoId,
        @Parameter(description = "ID del módulo al cual se asignará el recurso", required = true) @PathVariable int moduloId) {
>>>>>>> basti
        return recursoService.asignarRecursoAModulo(recursoId, moduloId);
    }
}
