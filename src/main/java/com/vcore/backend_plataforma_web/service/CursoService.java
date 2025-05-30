package com.vcore.backend_plataforma_web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Curso;
import com.vcore.backend_plataforma_web.model.Persona;
import com.vcore.backend_plataforma_web.model.Usuario;
import com.vcore.backend_plataforma_web.model.Inscripcion;

import com.vcore.backend_plataforma_web.DTO.ReporteDTO;

import com.vcore.backend_plataforma_web.repository.CursoRepository;
import com.vcore.backend_plataforma_web.repository.InscripcionRepository;
import com.vcore.backend_plataforma_web.repository.UsuarioRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired 
    private UsuarioRepository usuarioRepository;
    @Autowired
    private InscripcionRepository inscripcionRepository;

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

    public List<ReporteDTO> getCursosConAlumnos() {
        List<Curso> cursos = cursoRepository.findAll();
        List<ReporteDTO> resultado = new ArrayList<>();
        
        for (Curso curso : cursos) {
            // Obtener todas las inscripciones para este curso
            List<Inscripcion> inscripciones = inscripcionRepository.findByCurso(curso);
            
            // Extraer los nombres completos de los estudiantes desde Persona
            List<String> nombresCompletos = new ArrayList<>();
            for (Inscripcion inscripcion : inscripciones) {
                Usuario estudiante = inscripcion.getEstudiante();
                Persona persona = estudiante.getPersona();
                nombresCompletos.add(persona.getNombre() + " " + persona.getApellido());
            }
            
            // Crear el DTO para este curso
            ReporteDTO dto = new ReporteDTO();
            dto.setNombreCurso(curso.getNombre());
            dto.setCantidadAlumnos(inscripciones.size());
            dto.setNombresCompletosAlumnos(nombresCompletos);
            
            resultado.add(dto);
        }
        
        return resultado;
    }

}
