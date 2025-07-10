package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Categoria;
import com.vcore.backend_plataforma_web.service.CategoriaService;

<<<<<<< HEAD
@RestController
@Tag(name = "Categorías", 
     description = "Gestión de categorías de cursos - Permite administrar las categorías para clasificar los cursos ofrecidos")    
@RequestMapping("/categorias")
public class CategoriaController {
=======
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Categorías", 
     description = "Gestión de categorías de cursos - Permite administrar las categorías para clasificar los cursos ofrecidos")    
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    @Operation(summary = "Crear nueva categoría", 
               description = "Registra una nueva categoría en el sistema para clasificación de cursos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría creada exitosamente",
                    content = @Content(mediaType = "text/plain",
                    examples = @ExampleObject(value = "Categoría creada exitosamente"))),
        @ApiResponse(responseCode = "400", description = "Datos de categoría inválidos")
    })
    public String almacenar(
            @Parameter(description = "Objeto Categoría con los datos a registrar", required = true,
                      content = @Content(schema = @Schema(implementation = Categoria.class),
                      examples = @ExampleObject(
                          value = "{\"nombre\":\"Programación\",\"descripcion\":\"Cursos de desarrollo de software\"}"
                      )))
            @RequestBody Categoria categoria) {
        return categoriaService.almacenar(categoria);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las categorías", 
               description = "Recupera la lista completa de categorías disponibles en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida exitosamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Categoria.class)))
    public List<Categoria> listar() {
        return categoriaService.listar();
    }

    @PostMapping("/lista")
<<<<<<< HEAD
    public ResponseEntity<String> almacenar(@RequestBody List<Categoria> categorias) {
        for (Categoria categoria:categorias) {
            categoriaService.almacenar(categoria);
        }
        return ResponseEntity.ok("Categorías almacenadas correctamente");
    }

    @PostMapping("/asignarCategoriaACurso/{cursoId}/{categoriaId}")
    @Operation(summary = "Asignar categoría a curso", 
               description = "Asocia una categoría existente a un curso específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría asignada exitosamente",
                    content = @Content(mediaType = "text/plain",
                    examples = @ExampleObject(value = "Categoría asignada correctamente"))),
        @ApiResponse(responseCode = "404", description = "Curso o categoría no encontrada"),
        @ApiResponse(responseCode = "400", description = "Asignación inválida")
    })
    public String asignarCategoriaACurso(
            @Parameter(description = "ID del curso al que se asignará la categoría", required = true, example = "101")
            @PathVariable int cursoId,
            @Parameter(description = "ID de la categoría a asignar", required = true, example = "5")
            @PathVariable int categoriaId) {
        return categoriaService.asignarCategoriaACurso(cursoId, categoriaId);
    }
}
=======
    @Operation(summary = "Crear múltiples categorías", 
               description = "Registra un conjunto de categorías en una sola operación mediante una lista")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categorías creadas exitosamente",
                    content = @Content(mediaType = "text/plain",
                    examples = @ExampleObject(value = "Categorías almacenadas correctamente"))),
        @ApiResponse(responseCode = "400", description = "Datos de alguna categoría inválidos")
    })
    public ResponseEntity<String> almacenar(
            @Parameter(description = "Lista de objetos Categoría a registrar", required = true,
                      content = @Content(schema = @Schema(implementation = Categoria.class),
                      examples = @ExampleObject(
                          value = "[{\"nombre\":\"Diseño\"}, {\"nombre\":\"Negocios\"}]"
                      )))
            @RequestBody List<Categoria> categorias) {
        for (Categoria categoria : categorias) {
            categoriaService.almacenar(categoria);
        }
        return ResponseEntity.ok("Categorías almacenadas correctamente");
    }

    @PostMapping("/asignarCategoriaACurso/{cursoId}/{categoriaId}")
    @Operation(summary = "Asignar categoría a curso", 
               description = "Asocia una categoría existente a un curso específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría asignada exitosamente",
                    content = @Content(mediaType = "text/plain",
                    examples = @ExampleObject(value = "Categoría asignada correctamente"))),
        @ApiResponse(responseCode = "404", description = "Curso o categoría no encontrada"),
        @ApiResponse(responseCode = "400", description = "Asignación inválida")
    })
    public String asignarCategoriaACurso(
            @Parameter(description = "ID del curso al que se asignará la categoría", required = true, example = "101")
            @PathVariable int cursoId,
            @Parameter(description = "ID de la categoría a asignar", required = true, example = "5")
            @PathVariable int categoriaId) {
        return categoriaService.asignarCategoriaACurso(cursoId, categoriaId);
    }
}
