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

@RestController
@RequestMapping("/pagos")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @PostMapping
    public String almacenar(@RequestBody Pago pago){
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
