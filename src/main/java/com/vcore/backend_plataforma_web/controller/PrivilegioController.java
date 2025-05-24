package com.vcore.backend_plataforma_web.controller;

import org.springframework.web.bind.annotation.RestController;
import com.vcore.backend_plataforma_web.model.Privilegio;
import com.vcore.backend_plataforma_web.service.PrivilegioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;





@RestController
@RequestMapping("/privilegios")
public class PrivilegioController {
    @Autowired
    private PrivilegioService privilegioService;

    @PostMapping
    public String almacenar(@RequestBody Privilegio privilegio) {
        return privilegioService.almacenar(privilegio);
    }

    @GetMapping
    public List<Privilegio> listar() {
        return privilegioService.listar();
    }
      
}
