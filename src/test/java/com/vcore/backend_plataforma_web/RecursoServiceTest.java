package com.vcore.backend_plataforma_web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vcore.backend_plataforma_web.model.Modulo;
import com.vcore.backend_plataforma_web.model.Recurso;
import com.vcore.backend_plataforma_web.repository.ModuloRepository;
import com.vcore.backend_plataforma_web.repository.RecursoRepository;
import com.vcore.backend_plataforma_web.service.RecursoService;

@ExtendWith(MockitoExtension.class)
public class RecursoServiceTest {
    @Mock
    private RecursoRepository recursoRepository;

    @Mock
    private ModuloRepository moduloRepository;

    @InjectMocks
    private RecursoService recursoService;

    @Test
    void almacenarRecursoTest() {
        Recurso recurso = new Recurso();
        recurso.setNombreRecurso("Guía de matematica");

        when(recursoRepository.findByNombreRecurso("Guía de matematica")).thenReturn(null);
        String resultado = recursoService.almacenar(recurso);

        assertEquals("Recurso Guía de matematica almacenado correctamente", resultado);
        verify(recursoRepository).save(recurso);
    }

    @Test
    void asignarRecursoAModuloTest() {
        Recurso recurso = new Recurso();
        recurso.setId(1);
        recurso.setNombreRecurso("Guía 1");

        Modulo modulo = new Modulo();
        modulo.setId(10);
        modulo.setNombre("Módulo A");

        // El recurso no existe
        when(recursoRepository.existsById(1)).thenReturn(false);
        String r1 = recursoService.asignarRecursoAModulo(1, 10);
        assertEquals("El recurso ingresado no existe", r1);

        // El módulo no existe
        when(recursoRepository.existsById(1)).thenReturn(true);
        when(moduloRepository.existsById(10)).thenReturn(false);
        String r2 = recursoService.asignarRecursoAModulo(1, 10);
        assertEquals("El módulo ingresado no existe", r2);

        // El recurso ya está asignado al módulo
        when(moduloRepository.existsById(10)).thenReturn(true);
        recurso.setModulo(modulo);
        when(recursoRepository.findById(1)).thenReturn(Optional.of(recurso));
        when(moduloRepository.findById(10)).thenReturn(Optional.of(modulo));
        String r3 = recursoService.asignarRecursoAModulo(1, 10);
        assertEquals("El recurso ya está asignado a este módulo", r3);

        // Asignación exitosa
        recurso.setModulo(null); // limpiar relación
        when(recursoRepository.findById(1)).thenReturn(Optional.of(recurso)); // volver a pasar mocks
        String esperado = "El recurso 'Guía 1' fue asignado correctamente al módulo 'Módulo A'";
        String r4 = recursoService.asignarRecursoAModulo(1, 10);
        assertEquals(esperado, r4);
        verify(recursoRepository).save(recurso);
    }

}
