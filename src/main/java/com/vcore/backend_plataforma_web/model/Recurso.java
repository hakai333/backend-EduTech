package com.vcore.backend_plataforma_web.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreRecurso;
    private String tipo;
    private String url;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "modulo_id") // nombre de columna en la BDD
    @JsonBackReference
    private Modulo modulo;
}
