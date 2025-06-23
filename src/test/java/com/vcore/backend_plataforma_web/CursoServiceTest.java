package com.vcore.backend_plataforma_web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vcore.backend_plataforma_web.model.Curso;
import com.vcore.backend_plataforma_web.model.Rol;
import com.vcore.backend_plataforma_web.model.Usuario;
import com.vcore.backend_plataforma_web.repository.CursoRepository;
import com.vcore.backend_plataforma_web.repository.UsuarioRepository;
import com.vcore.backend_plataforma_web.service.CursoService;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {
    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CursoService cursoService;

    @Test
    void almacenarCursoTest() {
        Curso curso = new Curso();
        curso.setNombre("DSY1103");

        when(cursoRepository.findByNombre("DSY1103")).thenReturn(null);

        String resultado = cursoService.almacenar(curso);
        assertEquals("Curso DSY1103 creado correctamente", resultado);
        verify(cursoRepository).save(curso);
    }

    @Test
    void listarCursosTest() {
        Curso curso1 = new Curso();
        curso1.setNombre("BFR2321");
        Curso curso2 = new Curso();
        curso2.setNombre("KOG1535");

        List<Curso> listaCursos = new ArrayList<>();
        listaCursos.add(curso1);
        listaCursos.add(curso2);
        when(cursoRepository.findAll()).thenReturn(listaCursos);

        List<Curso> resultado = cursoService.listar();
        assertEquals(2, resultado.size());
    }

    @Test
    void asignarProfesorTest() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Juan Pérez");

        Rol rolProfesor = new Rol();
        rolProfesor.setNombre("profesor");

        Curso curso = new Curso();
        curso.setId(100);
        curso.setNombre("Matemáticas");

        // Probando primera condicion
        when(usuarioRepository.existsById(1)).thenReturn(false);
        String r1 = cursoService.asignarProfesor(1, 100);
        assertEquals("El usuario ingresado no existe", r1);

        when(usuarioRepository.existsById(1)).thenReturn(true);
        when(cursoRepository.existsById(100)).thenReturn(false);
        String r2 = cursoService.asignarProfesor(1, 100);
        assertEquals("El curso ingresado no existe", r2);

        // Preparar mocks para los siguientes casos
        when(cursoRepository.existsById(100)).thenReturn(true);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(cursoRepository.findById(100)).thenReturn(Optional.of(curso));

        // Usuario no es profesor
        usuario.setRol(null); // sin rol
        String r3 = cursoService.asignarProfesor(1, 100);
        assertEquals("Usuario no tiene rol de profesor", r3);

        // Asignación exitosa
        usuario.setRol(rolProfesor); // ahora sí es profesor
        String esperado = "Profesor 'Juan Pérez' asignado correctamente al curso 'Matemáticas'";
        String r4 = cursoService.asignarProfesor(1, 100);
        assertEquals(esperado, r4);
        verify(cursoRepository).save(curso);
    }
}
