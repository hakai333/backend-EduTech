package com.vcore.backend_plataforma_web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vcore.backend_plataforma_web.model.Rol;
import com.vcore.backend_plataforma_web.repository.RolRepository;
import com.vcore.backend_plataforma_web.service.RolService;

@ExtendWith(MockitoExtension.class)
public class RolServiceTest {
    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolService rolService;

    @Test
    void almacenarRolTest() {
        Rol rol = new Rol();
        rol.setNombre("profesor");

        when(rolRepository.findByNombre("profesor")).thenReturn(null);

        String resultado = rolService.almacenar(rol);

        assertEquals("Rol almacenado correctamente!", resultado);
        verify(rolRepository).save(rol);
    }

    @Test
    void listarRolesTest() {
        Rol modulo1 = new Rol();
        modulo1.setNombre("profesor");
        Rol modulo2 = new Rol();
        modulo2.setNombre("estudiante");

        List<Rol> listaRoles = new ArrayList<>();
        listaRoles.add(modulo1);
        listaRoles.add(modulo2);
        when(rolRepository.findAll()).thenReturn(listaRoles);

        List<Rol> resultado = rolService.listar();
        assertEquals(2, resultado.size());
    }
}
