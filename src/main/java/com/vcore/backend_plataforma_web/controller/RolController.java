package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Rol;
import com.vcore.backend_plataforma_web.service.RolService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Tag(name = "Roles", description = "Operaciones relacionadas con los roles para los usuarios")
@RequestMapping("/roles")
public class RolController {
    @Autowired
    private RolService rolService;

    @PostMapping()
    @Operation(summary = "Crea un rol y lo guarda en la BD", 
               description = "Permite crear un nuevo rol en el sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rol creado exitosamente",
                   content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    public String almacenar(
            @RequestBody 
            @Schema(description = "Objeto Rol con los datos a crear", required = true) 
            Rol rol) {
        return rolService.almacenar(rol);
    }
    
    @GetMapping
    @Operation(summary = "Muestra la lista de roles ingresados en la BD",
               description = "Obtiene todos los roles registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de roles obtenida exitosamente",
               content = @Content(schema = @Schema(implementation = Rol.class)))
    public List<Rol> listar() {
        return rolService.listar();
    }

    @PostMapping("/lista")
    @Operation(summary = "Crea uno o mas roles al mismo tiempo", 
               description = "Permite crear múltiples roles en una sola operación. Se debe ingresar como array en el JSON")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Roles creados exitosamente",
                   content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    public ResponseEntity<String> almacenar(
            @RequestBody 
            @Schema(description = "Lista de objetos Rol a crear", required = true) 
            List<Rol> roles) {
        for (Rol rol : roles) {
            rolService.almacenar(rol);
        }
        return ResponseEntity.ok("Roles almacenados correctamente");
    }
}