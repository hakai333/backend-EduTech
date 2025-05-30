package com.vcore.backend_plataforma_web.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO {
    private String nombreCurso;
    private int cantidadAlumnos;
    private List<String> nombresCompletosAlumnos;

    
}
