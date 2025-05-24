package com.vcore.backend_plataforma_web.model;


import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String rut;
    private String dv_rut;
    private String nombre;
    private String apellido;
    private String telefono;
    private Date fechaNacimiento;  
}
