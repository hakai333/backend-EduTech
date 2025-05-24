package com.vcore.backend_plataforma_web.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vcore.backend_plataforma_web.model.Cupon;
import com.vcore.backend_plataforma_web.repository.CuponRepository;

@Service
public class CuponService {
    @Autowired
    private CuponRepository cuponRepository;

    public String almacenar(Cupon cupon) {
        cuponRepository.save(cupon);
        return "Cupon ingresado";
    }

    public List<Cupon> listar() {
        return cuponRepository.findAll();
    }

    public String eliminar(Integer id) {
        if(!cuponRepository.existsById(id)) {
            return "Cupon no encontrado!";
        } else {
            cuponRepository.deleteById(id);
            return "Cupon eliminado correctamente!";
        }
    }

}
