package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Curso;
import com.vcore.backend_plataforma_web.repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;
    
     public String almacenar(Curso curso){
        if(cursoRepository.findByNombre(curso.getNombre())== null){
            cursoRepository.save(curso);
            return "Curso "+ curso.getNombre()+ "almacenado correctamente";
        }else{
            return "Curso "+ curso.getNombre()+ "ya se existe.";
        }
    }

    public List<Curso>listar(){
        return cursoRepository.findAll();
    }

}
