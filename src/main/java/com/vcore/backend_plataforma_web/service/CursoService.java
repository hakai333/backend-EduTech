package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    public ResponseEntity<String> almacenar(Curso curso) {
        if (cursoRepository.findByNombre(curso.getNombre()) == null) {
            cursoRepository.save(curso);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Curso " + curso.getNombre() + " creado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Curso " + curso.getNombre() + " ya existe");
        }
    }

    public List<Curso>listar(){
        return cursoRepository.findAll();
    }

//asignar usuario prof. a curso
    public ResponseEntity<String> asignarProfesor(int usuarioId, int cursoId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario ingresado no existe");
        }

        if (!cursoRepository.existsById(cursoId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El curso ingresado no existe");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId).get();
        Curso curso = cursoRepository.findById(cursoId).get();

        if (usuario.getRol() == null || 
            !usuario.getRol().getNombre().equalsIgnoreCase("profesor")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El usuario no tiene rol de profesor");
        }

        curso.setProfesor(usuario);
        cursoRepository.save(curso);

        return ResponseEntity.ok("Profesor '" + usuario.getNombre() +
                "' asignado correctamente al curso '" + curso.getNombre() + "'");
    }

    
//eliminar curso
    public ResponseEntity <String> eliminarCurso(@PathVariable int cursoId){
        if(cursoRepository.existsById(cursoId)){
            cursoRepository.deleteById(cursoId);
            return ResponseEntity.ok("Curso eliminado correctamente");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado");
        }
    }

}
