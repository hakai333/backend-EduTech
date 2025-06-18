package com.vcore.backend_plataforma_web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vcore.backend_plataforma_web.model.Modulo;
import com.vcore.backend_plataforma_web.repository.ModuloRepository;
import com.vcore.backend_plataforma_web.service.ModuloService;

@ExtendWith(MockitoExtension.class)
public class ModuloServiceTest {
    @Mock
    private ModuloRepository moduloRepository;
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
}
