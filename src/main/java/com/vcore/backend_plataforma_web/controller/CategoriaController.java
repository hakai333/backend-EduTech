package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.model.Categoria;
import com.vcore.backend_plataforma_web.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public String almacenar(@RequestBody Categoria categoria){
        return categoriaService.almacenar(categoria);
    }

    @GetMapping
    public List<Categoria>listar(){
        return categoriaService.listar();
    }

    @GetMapping("/asignarCategoriaACurso/{cursoId}/{categoriaId}")
    public String asignarCategoriaACurso(@PathVariable int cursoId,@PathVariable int categoriaId){
        return categoriaService.asignarCategoriaACurso(cursoId, categoriaId);
    }
}
