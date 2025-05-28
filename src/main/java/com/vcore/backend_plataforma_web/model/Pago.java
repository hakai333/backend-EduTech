package com.vcore.backend_plataforma_web.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Pago {
    private int id;
    private double monto;
    private LocalDate fechaPago;
    private Boolean estado;

}
