package com.vcore.backend_plataforma_web.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "roles")//nose si dejarle como rolesxd
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre; // Ej: "ESTUDIANTE", "PROFESOR", "ADMIN"
    private String descripcion;
    //@ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios; 
    
}
