package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Modulo;
import com.vcore.backend_plataforma_web.service.ModuloService;

<<<<<<< HEAD
=======
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;

>>>>>>> f5dc67c (Subiendo documentación Swagger a la rama basti)
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
<<<<<<< HEAD
@RequestMapping("/modulos")
public class ModuloController {
=======
@Tag(name = "Módulos", description = "Operaciones relacionadas a los módulos asignados a los cursos en EDUTECH.")
@RequestMapping("/modulos")
public class ModuloController {

>>>>>>> f5dc67c (Subiendo documentación Swagger a la rama basti)
    @Autowired
    private ModuloService moduloService;

    @PostMapping
<<<<<<< HEAD
=======
    @Operation(summary = "Crea un módulo y lo almacena en la BD.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Módulo creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
>>>>>>> f5dc67c (Subiendo documentación Swagger a la rama basti)
    public String almacenar(@RequestBody Modulo modulo) {
        return moduloService.almacenar(modulo);
    }

    @GetMapping
<<<<<<< HEAD
=======
    @Operation(summary = "Muestra la lista de módulos registrados en la BD.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de módulos obtenida correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Modulo.class))),
        @ApiResponse(responseCode = "500", description = "Error al obtener la lista de módulos", content = @Content)
    })
>>>>>>> f5dc67c (Subiendo documentación Swagger a la rama basti)
    public List<Modulo> listar() {
        return moduloService.listar();
    }

    @PostMapping("/asignarModuloACurso/{moduloId}/{cursoId}")
<<<<<<< HEAD
    public String asignarModulo(@PathVariable int moduloId, @PathVariable int cursoId) {
=======
    @Operation(summary = "Asigna un módulo registrado a un curso.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Módulo asignado correctamente al curso"),
        @ApiResponse(responseCode = "404", description = "Curso o módulo no encontrado", content = @Content)
    })
    public String asignarModulo(
        @Parameter(description = "ID del módulo", required = true) @PathVariable int moduloId,
        @Parameter(description = "ID del curso", required = true) @PathVariable int cursoId) {
>>>>>>> f5dc67c (Subiendo documentación Swagger a la rama basti)
        return moduloService.asignarModulo(moduloId, cursoId);
    }
}
