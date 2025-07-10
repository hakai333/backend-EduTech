package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Cupon;
import com.vcore.backend_plataforma_web.service.CuponService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

<<<<<<< HEAD


@RestController
@Tag(name = "Cupones", description = "Gestión de cupones de descuento - Operaciones CRUD para la administración de cupones promocionales")    
@RequestMapping("/cupones")
public class CuponController {
=======
@RestController
@Tag(name = "Cupones", description = "Gestión de cupones de descuento - Operaciones CRUD para la administración de cupones promocionales")    
@RequestMapping("/cupones")
public class CuponController {
    
    @Autowired
    private CuponService cuponService;

    @PostMapping
    @Operation(summary = "Crear nuevo cupón", 
               description = "Registra un nuevo cupón de descuento en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cupón creado exitosamente",
                     content = @Content(mediaType = "text/plain", 
                     examples = @ExampleObject(value = "Cupón creado exitosamente"))),
        @ApiResponse(responseCode = "400", description = "Datos del cupón inválidos",
                     content = @Content(mediaType = "text/plain",
                     examples = @ExampleObject(value = "Error: Los datos del cupón son inválidos")))
    })

    public String almacenar(
            @Parameter(description = "Objeto Cupón con los datos a registrar", required = true,
                       content = @Content(schema = @Schema(implementation = Cupon.class),
                       examples = @ExampleObject(
                           value = "{\"codigo\":\"DESC20\",\"descuento\":20.0,\"validoHasta\":\"2023-12-31\"}"
                       )))
            @RequestBody Cupon cupon) {
        return cuponService.almacenar(cupon);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los cupones", 
               description = "Retorna una lista completa de todos los cupones registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de cupones obtenida exitosamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cupon.class)))
    public List<Cupon> listar() {
        return cuponService.listar();
    }
}
