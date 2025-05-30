package com.vcore.backend_plataforma_web.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vcore.backend_plataforma_web.model.Incidencia;
import com.vcore.backend_plataforma_web.service.IncidenciaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/incidencias")
public class IncidenciaController {
    @Autowired
    private IncidenciaService incidenciaService;

    @PostMapping("/{idUsuario}")
    public String almacenar(@RequestBody Incidencia incidencia, @PathVariable Integer idUsuario) {
        return incidenciaService.almacenar(incidencia, idUsuario);
    }

    @GetMapping
    public List<Incidencia> listar() {
        return incidenciaService.listar();
    }
    



    
    

}
