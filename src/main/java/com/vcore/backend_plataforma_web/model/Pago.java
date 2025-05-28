package com.vcore.backend_plataforma_web.model;
import java.time.LocalDate;

import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//bastian
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double monto;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaPago;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name= "pago_id")
    private Inscripcion inscripcion;

    @ManyToOne
    @JoinColumn(name="cupon_id")
    private Cupon cupon;

    @PrePersist
    public void asignarFechaPago() {
        this.fechaPago = LocalDate.now();
    }
}
