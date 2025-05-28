package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Curso;
import com.vcore.backend_plataforma_web.model.Categoria;
import com.vcore.backend_plataforma_web.repository.CategoriaRepository;
import com.vcore.backend_plataforma_web.repository.CursoRepository;
//bastian
@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public String almacenar(Categoria categoria){
        if(categoriaRepository.findByNombre(categoria.getNombre())== null){
            categoriaRepository.save(categoria);
            return "Categoria "+ categoria.getNombre()+ " almacenado correctamente";
        }else{
            return "Categoria "+ categoria.getNombre()+ " ya se existe.";
        }
    }

    public List<Categoria>listar(){
        return categoriaRepository.findAll();
    }

    //agregar cursos a una categoria
    public String asignarCategoriaACurso(int cursoId, int categoriaId){
        if(!cursoRepository.existsById(cursoId)){
            return "El curso ingresado no existe";
        }else if(!categoriaRepository.existsById(categoriaId)){
            return "La categoria ingresada no existe";
        }else{
            Curso curso= cursoRepository.findById(cursoId).get();
            Categoria categoria = categoriaRepository.findById(categoriaId).get();
            
            curso.setCategoria(categoria);
            cursoRepository.save(curso);

        return "El curso '" + curso.getNombre() + "' fue asignado correctamente a la categoría '" + categoria.getNombre() + "'";
    }
    }
}
