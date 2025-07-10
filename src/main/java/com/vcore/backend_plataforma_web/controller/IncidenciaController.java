package com.vcore.backend_plataforma_web.controller;

<<<<<<< HEAD

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Incidencia;
import com.vcore.backend_plataforma_web.service.IncidenciaService;
=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Incidencia;
import com.vcore.backend_plataforma_web.service.IncidenciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@Tag(name = "Incidencias", description = "Operaciones relacionadas con el registro y gestión de incidencias en el sistema")    
@RequestMapping("/incidencias")
public class IncidenciaController {
    @Autowired
    private IncidenciaService incidenciaService;

    @PostMapping("/{idUsuario}")
    @Operation(summary = "Registra una nueva incidencia", 
              description = "Crea y almacena una incidencia asociada a un usuario específico en la base de datos")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Incidencia registrada exitosamente",
                   content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "400", description = "Error en los datos de la solicitud")
    })
    public String almacenar(
            @RequestBody 
            @Schema(description = "Datos de la incidencia a registrar", required = true) 
            Incidencia incidencia,
            
            @Parameter(description = "ID del usuario que reporta la incidencia", required = true)
            @PathVariable Integer idUsuario) {
        return incidenciaService.almacenar(incidencia, idUsuario);
    }

    @GetMapping
    @Operation(summary = "Obtiene todas las incidencias", 
              description = "Retorna una lista completa de todas las incidencias registradas en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de incidencias obtenida exitosamente",
               content = @Content(schema = @Schema(implementation = Incidencia.class)))
    public List<Incidencia> listar() {
        return incidenciaService.listar();
    }
}