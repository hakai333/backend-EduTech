package com.vcore.backend_plataforma_web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vcore.backend_plataforma_web.model.*;
import com.vcore.backend_plataforma_web.repository.*;
import com.vcore.backend_plataforma_web.service.InscripcionService;

@ExtendWith(MockitoExtension.class)
public class InscripcionServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private InscripcionRepository inscripcionRepository;

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private InscripcionService inscripcionService;

    @Test
    void testAlmacenarInscripcion() {
        Inscripcion inscripcion = new Inscripcion();
        when(inscripcionRepository.save(any(Inscripcion.class))).thenReturn(inscripcion);
        
        String resultado = inscripcionService.almacenar(inscripcion);
        
        assertEquals("Inscripcion creada!", resultado);
        verify(inscripcionRepository).save(inscripcion);
    }

    @Test
    void testInscripcionCurso_CursoNoExiste() {
        when(cursoRepository.existsById(1)).thenReturn(false);
        
        String resultado = inscripcionService.inscripcionCurso(1, 1);
        
        assertEquals("El curso ingresado no existe", resultado);
    }

    @Test
    void testInscripcionCurso_InscripcionNoExiste() {
        when(cursoRepository.existsById(1)).thenReturn(true);
        when(inscripcionRepository.existsById(1)).thenReturn(false);
        
        String resultado = inscripcionService.inscripcionCurso(1, 1);
        
        assertEquals("La inscripcion aun no ha sido creada", resultado);
    }

    @Test
    void testInscripcionCurso_Exitoso() {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Matemáticas");
        
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(1);
        
        when(cursoRepository.existsById(1)).thenReturn(true);
        when(inscripcionRepository.existsById(1)).thenReturn(true);
        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));
        when(inscripcionRepository.findById(1)).thenReturn(Optional.of(inscripcion));
        
        String resultado = inscripcionService.inscripcionCurso(1, 1);
        
        assertEquals("Curso Matemáticas asignado correctamente a inscripcion 1", resultado);
        assertEquals(curso, inscripcion.getCurso());
        verify(inscripcionRepository).save(inscripcion);
    }

    @Test
    void testInscripcionEstudiante_UsuarioNoExiste() {
        when(usuarioRepository.existsById(1)).thenReturn(false);
        
        String resultado = inscripcionService.inscripcionEstudiante(1, 1);
        
        assertEquals("El usuario ingresado no existe", resultado);
    }

    @Test
    void testInscripcionEstudiante_InscripcionNoExiste() {
        when(usuarioRepository.existsById(1)).thenReturn(true);
        when(inscripcionRepository.existsById(1)).thenReturn(false);
        
        String resultado = inscripcionService.inscripcionEstudiante(1, 1);
        
        assertEquals("La inscripcion aun no ha sido creada", resultado);
    }

    @Test
    void testInscripcionEstudiante_UsuarioNoEsEstudiante() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Profesor");
        
        Rol rol = new Rol();
        rol.setNombre("Profesor");
        usuario.setRol(rol);
        
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(1);
        
        when(usuarioRepository.existsById(1)).thenReturn(true);
        when(inscripcionRepository.existsById(1)).thenReturn(true);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(inscripcionRepository.findById(1)).thenReturn(Optional.of(inscripcion));
        
        String resultado = inscripcionService.inscripcionEstudiante(1, 1);
        
        assertEquals("El usuario no tiene rol de estudiante", resultado);
    }

    @Test
    void testInscripcionEstudiante_Exitoso() {
        Usuario estudiante = new Usuario();
        estudiante.setId(1);
        estudiante.setNombre("Estudiante Ejemplo");
        
        Rol rolEstudiante = new Rol();
        rolEstudiante.setNombre("estudiante");
        estudiante.setRol(rolEstudiante);
        
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(1);
        
        when(usuarioRepository.existsById(1)).thenReturn(true);
        when(inscripcionRepository.existsById(1)).thenReturn(true);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(estudiante));
        when(inscripcionRepository.findById(1)).thenReturn(Optional.of(inscripcion));
        
        String resultado = inscripcionService.inscripcionEstudiante(1, 1);
        
        assertEquals("Estudiante Estudiante Ejemplo asignado correctamente a inscripcion 1", resultado);
        assertEquals(estudiante, inscripcion.getEstudiante());
        assertTrue(inscripcion.getEstado());
        verify(inscripcionRepository).save(inscripcion);
    }
}