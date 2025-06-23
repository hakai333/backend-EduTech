package com.vcore.backend_plataforma_web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vcore.backend_plataforma_web.model.*;
import com.vcore.backend_plataforma_web.repository.CuponRepository;
import com.vcore.backend_plataforma_web.repository.InscripcionRepository;
import com.vcore.backend_plataforma_web.repository.PagoRepository;
import com.vcore.backend_plataforma_web.service.PagoService;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;
    
    @Mock
    private InscripcionRepository inscripcionRepository;
    
    @Mock
    private CuponRepository cuponRepository;
    
    @InjectMocks
    private PagoService pagoService;
    
    private Pago pago;
    private Inscripcion inscripcion;
    private Cupon cupon;
    private Curso curso;
    private Usuario usuarioEstudiante;
    private Usuario usuarioProfesor;
    private Rol rolEstudiante;
    private Rol rolProfesor;
    
    @BeforeEach
    void setUp() {
        rolEstudiante = new Rol();
        rolEstudiante.setId(1);
        rolEstudiante.setNombre("ESTUDIANTE");
        
        rolProfesor = new Rol();
        rolProfesor.setId(2);
        rolProfesor.setNombre("PROFESOR");
        
        usuarioEstudiante = new Usuario();
        usuarioEstudiante.setId(1);
        usuarioEstudiante.setNombre("Juan Perez");
        usuarioEstudiante.setRol(rolEstudiante);
        
        usuarioProfesor = new Usuario();
        usuarioProfesor.setId(2);
        usuarioProfesor.setNombre("Profesor Martinez");
        usuarioProfesor.setRol(rolProfesor);
        
        curso = new Curso();
        curso.setId(1);
        curso.setNombre("Matemáticas");
        curso.setPrecio(100.0);
        curso.setProfesor(usuarioProfesor);
        
        inscripcion = new Inscripcion();
        inscripcion.setId(1);
        inscripcion.setEstudiante(usuarioEstudiante);
        inscripcion.setCurso(curso);
        
        cupon = new Cupon();
        cupon.setId(1);
        cupon.setDescuento(10.0);
        cupon.setUsado(false);
        
        pago = new Pago();
        pago.setId(1);
        pago.setMonto(100.0);
    }
 
    @Test
    void testAlmacenarPago() {
        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);
        
        String resultado = pagoService.almacenar(pago);
        
        assertEquals("Pago ingresado correctamente!", resultado);
        verify(pagoRepository, times(1)).save(pago);
    }

    @Test
    void testListarPagos() {
        List<Pago> pagos = Arrays.asList(pago);
        when(pagoRepository.findAll()).thenReturn(pagos);
        
        List<Pago> resultado = pagoService.listar();
        
        assertEquals(1, resultado.size());
        assertEquals(pago, resultado.get(0));
        verify(pagoRepository, times(1)).findAll();
    }
    
    @Test
    void testPagarInscripcionExitoso() {
        when(inscripcionRepository.existsById(1)).thenReturn(true);
        when(pagoRepository.existsById(1)).thenReturn(true);
        when(inscripcionRepository.findById(1)).thenReturn(Optional.of(inscripcion));
        when(pagoRepository.findById(1)).thenReturn(Optional.of(pago));
        when(pagoRepository.existsByInscripcionId(1)).thenReturn(false);
        
        String resultado = pagoService.pagarInscripcion(1, 1);
        
        assertNotNull(resultado, "El resultado no debería ser nulo");
        assertTrue(resultado.contains("Pago"), "Debe indicar que es un pago");
        assertTrue(resultado.contains("100"), "Debe contener el monto");
        assertTrue(resultado.contains("Juan Perez"), "Debe contener el nombre del estudiante");
        assertTrue(resultado.contains("Matemáticas") || resultado.contains("curso"), 
            "Debe hacer referencia al curso");        
        verify(pagoRepository, times(1)).save(pago);
    }

    @Test
    void testPagarInscripcionConUsuarioNoEstudiante() {
        // usuario con rol dif a estudiante
        usuarioEstudiante.setRol(rolProfesor);
        
        when(inscripcionRepository.existsById(1)).thenReturn(true);
        when(pagoRepository.existsById(1)).thenReturn(true);
        when(inscripcionRepository.findById(1)).thenReturn(Optional.of(inscripcion));
        when(pagoRepository.findById(1)).thenReturn(Optional.of(pago));
        
        String resultado = pagoService.pagarInscripcion(1, 1);
        
        assertEquals("La inscripción no tiene un estudiante válido asociado", resultado);
        
        verify(pagoRepository, never()).existsByInscripcionId(anyInt());
        
        verify(pagoRepository, never()).save(any());
    }

    @Test
    void testPagarInscripcionPagoYaAsignado() {
        Inscripcion otraInscripcion = new Inscripcion();
        otraInscripcion.setId(2);
        pago.setInscripcion(otraInscripcion);
        
        when(inscripcionRepository.existsById(1)).thenReturn(true);
        when(pagoRepository.existsById(1)).thenReturn(true);
        when(inscripcionRepository.findById(1)).thenReturn(Optional.of(inscripcion));
        when(pagoRepository.findById(1)).thenReturn(Optional.of(pago)); // ¡Este mock es esencial!
        
        String resultado = pagoService.pagarInscripcion(1, 1);
        
        assertEquals("El pago ya está asignado a otra inscripción", resultado);
                verify(pagoRepository, never()).save(any());
    }

    @Test
    void testAsignarCuponAPagoValido() {
        Inscripcion inscripcion = new Inscripcion();
        Curso curso = new Curso();
        curso.setPrecio(100.0);
        inscripcion.setCurso(curso);
        
        Pago pagoTest = new Pago();
        pagoTest.setId(1);
        pagoTest.setMonto(100.0);
        pagoTest.setInscripcion(inscripcion); // CON inscripción y curso con precio para def
        pagoTest.setCupon(null);

        Cupon cuponTest = new Cupon();
        cuponTest.setId(1);
        cuponTest.setDescuento(20.0); // 20% de descuento
        cuponTest.setUsado(false);
        cuponTest.setPagos(new ArrayList<>());

        when(cuponRepository.existsById(1)).thenReturn(true);
        when(pagoRepository.existsById(1)).thenReturn(true);
        when(cuponRepository.findById(1)).thenReturn(Optional.of(cuponTest));
        when(pagoRepository.findById(1)).thenReturn(Optional.of(pagoTest));

        String resultado = pagoService.asignarCuponAPago(1, 1);

        assertEquals("cupon asignado correctamente al pago", resultado);
        assertNotNull(pagoTest.getCupon(), "El cupón no fue asignado al pago");
        assertEquals(80.0, pagoTest.getMonto(), 0.01, "El descuento no se aplicó correctamente");
        assertTrue(cuponTest.getPagos().contains(pagoTest), "El pago no fue agregado al cupón");
        verify(pagoRepository, times(1)).save(pagoTest);
    }

    @Test
    void testAsignarCuponYaUsado() {
        Inscripcion inscripcion = new Inscripcion();
        Curso curso = new Curso();
        curso.setPrecio(100.0);
        inscripcion.setCurso(curso);
        
        Pago pagoTest = new Pago();
        pagoTest.setId(1);
        pagoTest.setMonto(100.0);
        pagoTest.setInscripcion(inscripcion);
        pagoTest.setCupon(null);

        Cupon cuponTest = new Cupon();
        cuponTest.setId(1);
        cuponTest.setDescuento(20.0);
        cuponTest.setUsado(true); // Cupón YA USADO
        cuponTest.setPagos(new ArrayList<>());

        when(cuponRepository.existsById(1)).thenReturn(true);
        when(pagoRepository.existsById(1)).thenReturn(true);
        when(cuponRepository.findById(1)).thenReturn(Optional.of(cuponTest));
        when(pagoRepository.findById(1)).thenReturn(Optional.of(pagoTest));

        String resultado = pagoService.asignarCuponAPago(1, 1);

        assertEquals("Este cupón ya ha sido utilizado y no puede ser asignado nuevamente", resultado);
        assertNull(pagoTest.getCupon(), "No se debe asignar el cupón usado");
        assertEquals(100.0, pagoTest.getMonto(), 0.01, "El monto no debe cambiar");
        verify(pagoRepository, never()).save(any());
    }

    @Test
    void testCuponNoSeMarcaComoUsadoSiAsignacionFalla() {
        Cupon cuponExistente = new Cupon();
        cuponExistente.setId(1);
        cuponExistente.setUsado(false);
        
        Pago pagoTest = new Pago();
        pagoTest.setId(1);
        pagoTest.setCupon(new Cupon()); // Ya tiene cupón asignado
        
        when(cuponRepository.existsById(1)).thenReturn(true); // ← Cupón existe
        when(pagoRepository.existsById(1)).thenReturn(true);  // ← Pago existe
        when(pagoRepository.findById(1)).thenReturn(Optional.of(pagoTest));
        when(cuponRepository.findById(1)).thenReturn(Optional.of(cuponExistente));
        
        String resultado = pagoService.asignarCuponAPago(1, 1);
        
        assertEquals("El pago ya tiene un cupon asignado", resultado);
        assertFalse(cuponExistente.isUsado(), "El cupón no debe marcarse como usado");
        verify(pagoRepository, never()).save(any());
        verify(cuponRepository, never()).save(any()); // ← Verificación adicional
    }

}