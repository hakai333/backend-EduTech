package com.vcore.backend_plataforma_web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vcore.backend_plataforma_web.model.Persona;
import com.vcore.backend_plataforma_web.service.PersonaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@Tag(name = "Personas", description = "Operaciones relacionadas al propietario de un usuario EDUTECH.")
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    @Operation(summary = "Muestra la lista de los datos personales registrados de un usuario EDUTECH.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de personas obtenida exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Persona.class))),
        @ApiResponse(responseCode = "500", description = "Error al obtener los datos personales", content = @Content)
    })
    public List<Persona> listar() {
        return personaService.listar(); 
    }

    @PostMapping("/lista")
    @Operation(summary = "Permite ingresar los datos personales de múltiples usuarios EDUTECH.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Personas almacenadas correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    public ResponseEntity<String> almacenar(
        @RequestBody(description = "Lista de personas a registrar", required = true,
                     content = @Content(schema = @Schema(implementation = Persona.class)))
        @org.springframework.web.bind.annotation.RequestBody List<Persona> personas) {
        for (Persona persona : personas) {
            personaService.almacenar(persona);
        }
        return ResponseEntity.ok("Personas almacenadas correctamente");
    }

    @PostMapping("/asignarUsuario/{rutPersona}/{idUsuario}")
    @Operation(summary = "Permite asignar los datos personales almacenados a un usuario EDUTECH.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos personales asignados al usuario"),
        @ApiResponse(responseCode = "404", description = "Persona o usuario no encontrado", content = @Content)
    })
    public String asignarUsuario(
        @Parameter(description = "RUT de la persona", required = true) @PathVariable String rutPersona,
        @Parameter(description = "ID del usuario al que se asignará", required = true) @PathVariable Integer idUsuario) {
        return personaService.asignarUsuario(rutPersona, idUsuario);
    }

    @PostMapping
    @Operation(summary = "Permite almacenar los datos personales de un usuario EDUTECH.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos personales almacenados correctamente"),
        @ApiResponse(responseCode = "400", description = "Error en los datos enviados", content = @Content)
    })
    public String almacenar(
        @RequestBody(description = "Datos personales del usuario a registrar", required = true,
                     content = @Content(schema = @Schema(implementation = Persona.class)))
        @org.springframework.web.bind.annotation.RequestBody Persona persona) {
        return personaService.almacenar(persona);
    }
}
