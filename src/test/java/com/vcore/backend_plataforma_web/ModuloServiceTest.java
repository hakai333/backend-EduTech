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
import com.vcore.backend_plataforma_web.model.Modulo;
import com.vcore.backend_plataforma_web.repository.CursoRepository;
import com.vcore.backend_plataforma_web.repository.ModuloRepository;
import com.vcore.backend_plataforma_web.service.ModuloService;

@ExtendWith(MockitoExtension.class)
public class ModuloServiceTest {
    @Mock
    private ModuloRepository moduloRepository;

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private ModuloService moduloService;

    @Test
    void almacenarModuloTest() {
        Modulo modulo = new Modulo();
        modulo.setNombre("Matemáticas");

        when(moduloRepository.findByNombre("Matemáticas")).thenReturn(null);

        String resultado = moduloService.almacenar(modulo);

        assertEquals("Modulo Matemáticas almacenado correctamente", resultado);
        verify(moduloRepository).save(modulo);
    }

    @Test
    void listarModulosTest() {
        Modulo modulo1 = new Modulo();
        modulo1.setNombre("Matemáticas");
        Modulo modulo2 = new Modulo();
        modulo2.setNombre("Ciencias Sociales");

        List<Modulo> listaModulos = new ArrayList<>();
        listaModulos.add(modulo1);
        listaModulos.add(modulo2);
        when(moduloRepository.findAll()).thenReturn(listaModulos);

        List<Modulo> resultado = moduloService.listar();
        assertEquals(2, resultado.size());
    }

    @Test
    void asignarModuloACursoTest() {
        Modulo modulo = new Modulo();
        modulo.setId(10);
        modulo.setNombre("Modulo X");

        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Curso A");
        curso.setModulos(new ArrayList<>());

        // 1. Módulo no existe
        when(moduloRepository.existsById(10)).thenReturn(false);
        String r1 = moduloService.asignarModulo(10, 1);
        assertEquals("El módulo ingresado no existe", r1);

        // 2. Curso no existe
        when(moduloRepository.existsById(10)).thenReturn(true);
        when(cursoRepository.existsById(1)).thenReturn(false);
        String r2 = moduloService.asignarModulo(10, 1);
        assertEquals("El curso ingresado no existe", r2);

        // Preparar mocks válidos para los casos siguientes
        when(moduloRepository.existsById(10)).thenReturn(true);
        when(cursoRepository.existsById(1)).thenReturn(true);
        when(moduloRepository.findById(10)).thenReturn(Optional.of(modulo));
        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));

        // 3. Módulo asignado a otro curso
        Curso otro = new Curso();
        otro.setId(2);
        otro.setNombre("Curso B");
        modulo.setCurso(otro); // asignado a otro curso

        String r3 = moduloService.asignarModulo(10, 1);
        assertEquals("El módulo ya está asignado al curso: Curso B", r3);

        // 4. Módulo ya asignado al mismo curso
        modulo.setCurso(curso); // asignado al curso correcto
        String r4 = moduloService.asignarModulo(10, 1);
        assertEquals("El módulo ya está asignado a este curso", r4);

        // 5. Asignación exitosa
        modulo.setCurso(null); // desasociado para permitir la asignación
        curso.setModulos(new ArrayList<>());

        String esperado = "Módulo 'Modulo X' asignado correctamente al curso 'Curso A'";
        String r5 = moduloService.asignarModulo(10, 1);
        assertEquals(esperado, r5);

        verify(moduloRepository).save(modulo);
        verify(cursoRepository).save(curso);
    }

}
