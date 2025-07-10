package com.vcore.backend_plataforma_web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vcore.backend_plataforma_web.model.Persona;
import com.vcore.backend_plataforma_web.service.PersonaService;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@Tag(name = "Personas", description = "Operaciones relacionadas al propietario de un usuario EDUTECH.")
@RequestMapping("/personas")
public class PersonaController {
=======

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

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
<<<<<<< HEAD
    public List<Persona> listar() {
        return personaService.listar(); 
    }

    @PostMapping("/lista")
    public ResponseEntity<String> almacenar(@RequestBody List<Persona> personas) {
=======
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
    public ResponseEntity<String> almacenar(@RequestBody List<Persona> personas) {
        for (Persona persona : personas) {
            personaService.almacenar(persona);
        }
        return ResponseEntity.ok("Personas almacenadas correctamente");
    }

    @PostMapping("/asignarUsuario/{rutPersona}/{idUsuario}")
    public String asignarUsuario(@PathVariable String rutPersona, @PathVariable Integer idUsuario) {
        return personaService.asignarUsuario(rutPersona, idUsuario);
    }

    @PostMapping
    public String almacenar(@RequestBody Persona persona) {
        return personaService.almacenar(persona);
    }
}
