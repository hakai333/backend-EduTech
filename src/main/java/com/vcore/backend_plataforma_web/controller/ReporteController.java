package com.vcore.backend_plataforma_web.controller;

import com.vcore.backend_plataforma_web.DTO.ReporteDTO;
import com.vcore.backend_plataforma_web.service.ReporteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Reporte de cursos", description = "Operaciones relacionadas con reportes de cursos, profesores y estudiantes")
@RequestMapping("/reporteDTO")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    @Operation(
        summary = "Obtener reporte de cursos",
        description = "Devuelve un listado de cursos, su profesor asignado y cantidad de estudiantes inscritos"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reporte generado exitosamente", 
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ReporteDTO.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    public ResponseEntity<List<ReporteDTO>> generarReporteCursos() {
        List<ReporteDTO> reporte = reporteService.generarReporteCursos();
        return ResponseEntity.ok(reporte);
    }

}

