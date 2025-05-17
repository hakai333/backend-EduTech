package com.vcore.backend_plataforma_web.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

//import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;

    private String contrasena;

    private String email;

    /*
     * @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_usuario_id", nullable = false)
    private Rol tipoUsuario;
    */
      /*
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private Persona persona;
    */

    @ManyToMany
    @JoinTable(
        name = "alumno_curso",
        joinColumns = @JoinColumn(name = "alumno_rut"),
        inverseJoinColumns = @JoinColumn(name = "curso_sigla")
    )
    @JsonBackReference
    private List<Curso> cursos;
    //private Date fecha_registro;







    
}
