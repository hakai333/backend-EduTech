package com.vcore.backend_plataforma_web.model;



import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evaluaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluacion {
    @Id
    private Integer Id;
    private String nombreEvaluacion;
    private LocalTime fechaLimite;
    private String tipoEvaluacion;
}
