package com.vcore.backend_plataforma_web.model;

import java.sql.Date;

import jakarta.persistence.Entity;
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
    private Integer Id;
    private String nombreEvaluacion;
    private Date fechaLimite;
    private String tipoEvaluacion;
}
