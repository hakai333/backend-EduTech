package com.vcore.backend_plataforma_web.model;



import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cupones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cupon {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double descuento;
    private String codigo;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaExpiracion;
    private boolean usado;


    @OneToMany(mappedBy="cupon")
    @JsonIgnore
    private List<Pago>pagos;

}
