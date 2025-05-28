package com.vcore.backend_plataforma_web.service;

import java.util.List;
import com.vcore.backend_plataforma_web.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vcore.backend_plataforma_web.model.Curso;
import com.vcore.backend_plataforma_web.model.Inscripcion;
import com.vcore.backend_plataforma_web.model.Usuario;
import com.vcore.backend_plataforma_web.repository.CursoRepository;
import com.vcore.backend_plataforma_web.repository.InscripcionRepository;

//bastian
@Service
public class InscripcionService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private InscripcionRepository inscripcionRepository;
    @Autowired
    private CursoRepository cursoRepository;

    InscripcionService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String almacenar(Inscripcion inscripcion) {
            inscripcionRepository.save(inscripcion);
        return "Inscripcion creada!";
    }

    public List<Inscripcion> listar() {
        return inscripcionRepository.findAll();
    }

//inscribir un curso
    public String inscripcionCurso(int cursoId, int inscripcionId){
        if(!cursoRepository.existsById(cursoId)){
            return "El curso ingresado no existe";
        } else if(!inscripcionRepository.existsById(inscripcionId)){
            return "La inscripcion aun no ha sido creada";
        } else {
            Curso curso = cursoRepository.findById(cursoId).get();
            Inscripcion inscripcion = inscripcionRepository.findById(inscripcionId).get();

            inscripcion.setCurso(curso);
            inscripcionRepository.save(inscripcion);

            return "Curso "+ curso.getNombre() +
            " asignado correctamente a inscripcion " + inscripcion.getId();
        }
    }
//inscribir un estudiante
    public String inscripcionEstudiante(int usuarioId, int inscripcionId){
        if(!usuarioRepository.existsById(usuarioId)){
            return "El usuario ingresado no existe";
        } else if(!inscripcionRepository.existsById(inscripcionId)){
            return "La inscripcion aun no ha sido creada";
        } else {
            Usuario usuario= usuarioRepository.findById(usuarioId).get();
            Inscripcion inscripcion = inscripcionRepository.findById(inscripcionId).get();

            if(usuario.getRol()==null|| !usuario.getRol().getNombre().equalsIgnoreCase("estudiante")){
                return "El usuario no tiene rol de estudiante";
            }

            inscripcion.setEstudiante(usuario);
            inscripcion.setEstado(true);
            inscripcionRepository.save(inscripcion);

            return "Estudiante "+ usuario.getNombre() +
            " asignado correctamente a inscripcion " + inscripcion.getId();
        }
    }

}
