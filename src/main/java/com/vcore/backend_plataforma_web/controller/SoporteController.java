package com.vcore.backend_plataforma_web.controller;

import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Soporte;
import com.vcore.backend_plataforma_web.service.SoporteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/soportes")
public class SoporteController {
    @Autowired
    private SoporteService soporteService;

    @PostMapping
    public String almacenar(@RequestBody Soporte soporte) {
        return soporteService.almacenar(soporte);
    } 
    
    @GetMapping
    public List<Soporte> listar() {
        return soporteService.listar();
    }

}
