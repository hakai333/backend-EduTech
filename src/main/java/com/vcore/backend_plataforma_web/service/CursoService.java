package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Curso;
import com.vcore.backend_plataforma_web.model.Usuario;


import com.vcore.backend_plataforma_web.repository.CursoRepository;
import com.vcore.backend_plataforma_web.repository.UsuarioRepository;

//bastian
@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired 
    private UsuarioRepository usuarioRepository;

     public String almacenar(Curso curso){
        if(cursoRepository.findByNombre(curso.getNombre())== null){
            cursoRepository.save(curso);
            return "Curso "+ curso.getNombre()+ " creado correctamente";
        }else{
            return "Curso "+ curso.getNombre()+ " ya se existe.";
        }
    }

    public List<Curso>listar(){
        return cursoRepository.findAll();
    }

//asignar usuario prof. a curso
    public String asignarProfesor(int usuarioId, int cursoId){
        if(!usuarioRepository.existsById(usuarioId)){
            return "El usuario ingresado no existe";
        }else if(!cursoRepository.existsById(cursoId)){
            return "El curso ingresado no existe";
        }else {
            Usuario usuario= usuarioRepository.findById(usuarioId).get();
            Curso curso = cursoRepository.findById(cursoId).get();
            
            if(usuario.getRol()==null|| !usuario.getRol().getNombre().equalsIgnoreCase("profesor")){
                return "Usuario no tiene rol de profesor";
            }

            curso.setProfesor(usuario);
            cursoRepository.save(curso);

            return "Profesor '"+ usuario.getNombre()+ "' asignado correctamente al curso '"+
            curso.getNombre()+"'";
        }
    }



}
