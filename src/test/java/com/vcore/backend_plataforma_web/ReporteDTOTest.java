package com.vcore.backend_plataforma_web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.vcore.backend_plataforma_web.DTO.ReporteDTO;

public class ReporteDTOTest {

    @Test
    void crearReporteDTO_ValoresNormales() {
        // datos para prueba
        String nombreCurso = "Matemáticas Avanzadas";
        String nombreProfesor = "Ana García";
        int cantidadAlumnos = 2;
        List<String> nombresAlumnos = Arrays.asList("Carlos Mendoza", "Luisa Fernández");

        ReporteDTO reporte = new ReporteDTO(
            nombreCurso,
            nombreProfesor,
            cantidadAlumnos,
            nombresAlumnos
        );

        assertEquals(nombreCurso, reporte.getNombreCurso());
        assertEquals(nombreProfesor, reporte.getNombreProfesor());
        assertEquals(cantidadAlumnos, reporte.getCantidadAlumnos());
        assertEquals(nombresAlumnos, reporte.getNombresAlumnos());
    }

    @Test
    void crearReporteDTO_ValoresNulos() {
        // DTO con datos nulos
        ReporteDTO reporte = new ReporteDTO(
            null,
            null,
            0,
            null
        );

        // Valida nulos
        assertEquals(null, reporte.getNombreCurso());
        assertEquals(null, reporte.getNombreProfesor());
        assertEquals(0, reporte.getCantidadAlumnos());
        assertEquals(null, reporte.getNombresAlumnos());
    }

    @Test
    void crearReporteDTO_ListaVacia() {
        // DTO sin alumnos
        ReporteDTO reporte = new ReporteDTO(
            "Curso Vacío",
            "Profesor X",
            0,
            Collections.emptyList()
        );
        assertTrue(reporte.getNombresAlumnos().isEmpty());
    }
}