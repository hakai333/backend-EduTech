package com.vcore.backend_plataforma_web.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;

    // basti
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Inscripcion> inscripciones;

    // Pulie
    @ManyToOne
    @JoinColumn(name = "profesor_id")
    @JsonManagedReference(value = "usuarioCursos")
    private Usuario profesor;
}
