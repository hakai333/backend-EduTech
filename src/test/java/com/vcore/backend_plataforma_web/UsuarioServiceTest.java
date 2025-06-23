package com.vcore.backend_plataforma_web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vcore.backend_plataforma_web.model.Rol;
import com.vcore.backend_plataforma_web.model.Usuario;
import com.vcore.backend_plataforma_web.repository.PersonaRepository;
import com.vcore.backend_plataforma_web.repository.RolRepository;
import com.vcore.backend_plataforma_web.repository.UsuarioRepository;
import com.vcore.backend_plataforma_web.service.UsuarioService;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolRepository rolRepository;

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    // ✅ 1) Buscar por ID - existente
    @Test
    void buscarPorId_UsuarioExistente_RetornaUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(usuarioRepository).findById(1);
    }

    // ✅ 2) Buscar por Nombre - existente
    @Test
    void buscarPorNombre_UsuarioExistente_RetornaUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Miguel");
        when(usuarioRepository.findAllByNombre("Miguel")).thenReturn(List.of(usuario));

        Usuario resultado = usuarioService.buscarPorNombre("Miguel");

        assertNotNull(resultado);
        assertEquals("Miguel", resultado.getNombre());
        verify(usuarioRepository).findAllByNombre("Miguel");
    }

    // ✅ 3) Crear Usuario - admin correcto
    @Test
    void crearUsuario_AdminCorrecto_AlmacenaUsuario() {
        Usuario usuarioActual = new Usuario();
        Rol rolAdmin = new Rol();
        rolAdmin.setNombre("Administrador del sistema");
        usuarioActual.setRol(rolAdmin);

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Nuevo");

        String resultado = usuarioService.crearUsuario(nuevoUsuario, usuarioActual);

        assertEquals("Usuario almacenado correctamente", resultado);
        assertTrue(nuevoUsuario.getEsActivo());
        verify(usuarioRepository).save(nuevoUsuario);
    }

    // ✅ 4) Actualizar Usuario - admin correcto
    @Test
    void actualizarUsuario_AdminCorrecto_ActualizaDatos() {
        Usuario usuarioActual = new Usuario();
        Rol rolAdmin = new Rol();
        rolAdmin.setNombre("Administrador del sistema");
        usuarioActual.setRol(rolAdmin);

        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(1);
        usuarioExistente.setNombre("Viejo");

        Usuario datosActualizar = new Usuario();
        datosActualizar.setNombre("Nuevo");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioExistente));

        String resultado = usuarioService.actualizarUsuario(datosActualizar, usuarioActual, 1);

        assertEquals("Usuario actualizado", resultado);
        assertEquals("Nuevo", usuarioExistente.getNombre());
        verify(usuarioRepository).save(usuarioExistente);
    }

    // ✅ 5) Desactivar Usuario - admin correcto
    @Test
    void desactivarUsuario_AdminCorrecto_Desactiva() {
        Usuario usuarioDesactivar = new Usuario();
        usuarioDesactivar.setId(2);
        usuarioDesactivar.setEsActivo(true);

        Usuario usuarioAdmin = new Usuario();
        usuarioAdmin.setNombre("Admin");
        Rol rolAdmin = new Rol();
        rolAdmin.setNombre("Administrador del sistema");
        usuarioAdmin.setRol(rolAdmin);

        when(usuarioRepository.findById(2)).thenReturn(Optional.of(usuarioDesactivar));
        when(usuarioRepository.findAll()).thenReturn(List.of(usuarioAdmin));

        String resultado = usuarioService.desactivarUsuario(usuarioAdmin, 2);

        assertEquals("Usuario desactivado", resultado);
        assertFalse(usuarioDesactivar.getEsActivo());
        verify(usuarioRepository).save(usuarioDesactivar);
    }
}
