package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Evaluacion;
import com.vcore.backend_plataforma_web.service.EvaluacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Tag(name = "Evaluaciones", description = "Operaciones relacionadas con la gestión de evaluaciones académicas")    
@RequestMapping("/evaluaciones")
public class EvaluacionController {
    @Autowired
    private EvaluacionService evaluacionService;

    @PostMapping
    @Operation(summary = "Crea una nueva evaluación", 
              description = "Registra una evaluación académica en el sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Evaluación creada exitosamente",
                   content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "400", description = "Error en los datos de la evaluación")
    })
    public String almacenar(
            @RequestBody 
            @Schema(description = "Datos de la evaluación a registrar", required = true)
            Evaluacion evaluacion) {
        return evaluacionService.almacenar(evaluacion);
    }
//listar
    @GetMapping
    @Operation(summary = "Obtiene todas las evaluaciones", 
              description = "Retorna una lista completa de todas las evaluaciones registradas en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de evaluaciones obtenida exitosamente",
               content = @Content(schema = @Schema(implementation = Evaluacion.class)))
    public List<Evaluacion> listar() {
        return evaluacionService.listar();
    }
}