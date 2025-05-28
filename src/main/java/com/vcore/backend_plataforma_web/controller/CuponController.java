package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Cupon;
import com.vcore.backend_plataforma_web.service.CuponService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/cupones")
public class CuponController {
    @Autowired
    private CuponService cuponService;

    @PostMapping
    public String almacenar(@RequestBody Cupon cupon) {
        return cuponService.almacenar(cupon);
    }

    @GetMapping
    public List<Cupon> listar() {
        return cuponService.listar();
    }
}
