package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Cupon;
import com.vcore.backend_plataforma_web.model.Inscripcion;
import com.vcore.backend_plataforma_web.model.Pago;
import com.vcore.backend_plataforma_web.repository.CuponRepository;
import com.vcore.backend_plataforma_web.repository.InscripcionRepository;
import com.vcore.backend_plataforma_web.repository.PagoRepository;
//bastian
@Service
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;
    @Autowired
    private InscripcionRepository inscripcionRepository;
    @Autowired
    private CuponRepository cuponRepository;
    
    public String almacenar(Pago pago){
        pagoRepository.save(pago);
        return "Pago ingresado correctamente!";
    }

    public List<Pago>listar(){
        return pagoRepository.findAll();
    }

//asignar un pago a una inscripcion
    public String pagarInscripcion(int inscripcionId, int pagoId) {
    // Validar existencia de inscripcion y pago
    if (!inscripcionRepository.existsById(inscripcionId)) {
        return "La inscripcion ingresada no existe";
    }
    if (!pagoRepository.existsById(pagoId)) {
        return "El pago aún no ha sido realizado";
    }

    Inscripcion inscripcion = inscripcionRepository.findById(inscripcionId).get();
    Pago pago = pagoRepository.findById(pagoId).get();
    
    // Validaciones basicas
    if (inscripcion.getEstudiante() == null || inscripcion.getCurso() == null) {
        return "La inscripcion no tiene estudiante o curso asociado";
    }
    if (pago.getInscripcion() != null) {
        return "El pago ya está asignado a otra inscripcion";
    }
    if (pago.getMonto() <= 0) {
        return "Pago rechazado, debe ser por un monto mayor a 0";
    }

    // Aplicar descuento si existe cupon
    if (pago.getCupon() != null && pago.getCupon().getDescuento() != null) {
        double descuento = pago.getCupon().getDescuento();
        if (descuento >= 1 && descuento <= 99) {  // Validar rango de descuento
            double montoConDescuento = pago.getMonto() * (1 - (descuento / 100));
            pago.setMonto(montoConDescuento);
        }
    }

    // Asignar pago a inscripcion
    pago.setInscripcion(inscripcion);
    pagoRepository.save(pago);

    // mensaje
    String mensaje = "Pago de $" + String.format("%.2f", pago.getMonto()) 
        + " registrado para " + inscripcion.getEstudiante().getNombre()
        + " en el curso " + inscripcion.getCurso().getNombre();
    
    if (pago.getCupon() != null) {
        mensaje += " (con descuento aplicado)";
    }

    return mensaje;
}
        //asignar un cupon a pago
    public String asignarCuponAPago(int cuponId, int pagoId) {
    // Validar existencia de cupon y pago
    if (!cuponRepository.existsById(cuponId)) {
        return "El cupon ingresado no existe";
    }
    if (!pagoRepository.existsById(pagoId)) {
        return "El pago seleccionado no existe";
    }

    Cupon cupon = cuponRepository.findById(cuponId).get();
    Pago pago = pagoRepository.findById(pagoId).get();

    // Validar que el pago no tenga ya un cupon asignado
    if (pago.getCupon() != null) {
        return "El pago ya tiene un cupon asignado";
    }

    // Validar que el cupon tenga descuento definido (1-99%)
    double descuentoPorcentaje = cupon.getDescuento();
    if (descuentoPorcentaje < 1 || descuentoPorcentaje > 99) {
        return "El descuento debe ser entre 1% y 99%";
    }

    // Si el pago tiene inscripción con curso y precio definido, aplicar descuento
    if (pago.getInscripcion() != null 
            && pago.getInscripcion().getCurso() != null 
            && pago.getInscripcion().getCurso().getPrecio() != null) {
        
        double precioCurso = pago.getInscripcion().getCurso().getPrecio();
        double descuentoMonto = precioCurso * (descuentoPorcentaje / 100);
        double nuevoMonto = precioCurso - descuentoMonto;

        pago.setMonto(nuevoMonto);
    }

    pago.setCupon(cupon);
    cupon.getPagos().add(pago);

    pagoRepository.save(pago);

    return "cupon asignado correctamente al pago";
    }
}
