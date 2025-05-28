package com.vcore.backend_plataforma_web.model;



import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String contrasena;
    private String email;
    private LocalDate fecha_registro;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

//bastian
    @OneToMany(mappedBy = "estudiante",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Inscripcion> inscripciones;
}
