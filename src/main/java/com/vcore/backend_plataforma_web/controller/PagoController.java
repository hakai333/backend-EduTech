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

<<<<<<< HEAD
@RestController
@Tag(name = "Pagos", description = "Operaciones relacionadas a los pagos de inscripciones en EDUTECH.")
@RequestMapping("/pagos")
public class PagoController {
=======
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Pagos", description = "Operaciones relacionadas a los pagos de inscripciones en EDUTECH.")
@RequestMapping("/pagos")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @PostMapping
<<<<<<< HEAD
    public String almacenar(@RequestBody Pago pago){
        return pagoService.almacenar(pago);
    }

    @GetMapping
    public List<Pago>listar(){
=======
    @Operation(summary = "Crea un pago y lo registra en la BD.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago registrado correctamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })
    public String almacenar(@RequestBody Pago pago) {
        return pagoService.almacenar(pago);
    }

    @GetMapping
    public List<Pago>listar(){
        return pagoService.listar();
    }

    @PostMapping("/asignarPagoAInscripcion/{inscripcionId}/{pagoId}")
    public String pagarInscripcion(@PathVariable int inscripcionId,@PathVariable int pagoId){
        return pagoService.pagarInscripcion(inscripcionId,pagoId);
    }

    @PostMapping("/asignarCuponAPago/{cuponId}/{pagoId}")
    public String asignarCuponAPago(@PathVariable int cuponId,@PathVariable int pagoId){
        return pagoService.asignarCuponAPago(cuponId,pagoId);
    }
}
