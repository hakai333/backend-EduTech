package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Pago;
import com.vcore.backend_plataforma_web.service.PagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@Tag(name = "Pagos", description = "Operaciones relacionadas a los pagos de inscripciones en EDUTECH.")
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    @Operation(summary = "Crea un pago y lo registra en la BD.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago registrado correctamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })
    public String almacenar(@RequestBody Pago pago) {
        return pagoService.almacenar(pago);
    }

    @GetMapping
    @Operation(summary = "Muestra la lista completa de pagos registrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pago.class))),
        @ApiResponse(responseCode = "500", description = "Error al obtener los pagos", content = @Content)
    })
    public List<Pago> listar() {
        return pagoService.listar();
    }

    @PostMapping("/asignarPagoAInscripcion/{inscripcionId}/{pagoId}")
    @Operation(summary = "Asigna un pago a una inscripción.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago asignado correctamente a la inscripción"),
        @ApiResponse(responseCode = "404", description = "Inscripción o pago no encontrado", content = @Content)
    })
    public String pagarInscripcion(
        @Parameter(description = "ID de la inscripción", required = true) @PathVariable int inscripcionId,
        @Parameter(description = "ID del pago a asignar", required = true) @PathVariable int pagoId) {
        return pagoService.pagarInscripcion(inscripcionId, pagoId);
    }

    @PostMapping("/asignarCuponAPago/{cuponId}/{pagoId}")
    @Operation(summary = "Asigna un cupón de descuento a una inscripción.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cupón asignado correctamente al pago"),
        @ApiResponse(responseCode = "404", description = "Cupón o pago no encontrado", content = @Content)
    })
    public String asignarCuponAPago(
        @Parameter(description = "ID del cupón a aplicar", required = true) @PathVariable int cuponId,
        @Parameter(description = "ID del pago al que se asignará el cupón", required = true) @PathVariable int pagoId) {
        return pagoService.asignarCuponAPago(cuponId, pagoId);
    }
}
