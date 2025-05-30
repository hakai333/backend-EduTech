package com.vcore.backend_plataforma_web.controller;

import com.vcore.backend_plataforma_web.DTO.ReporteDTO;
import com.vcore.backend_plataforma_web.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reporteDTO")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> generarReporteCursos() {
        List<ReporteDTO> reporte = reporteService.generarReporteCursos();
        return ResponseEntity.ok(reporte);
    }

}