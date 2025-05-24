package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Soporte;
import com.vcore.backend_plataforma_web.repository.SoporteRepository;



@Service
public class SoporteService {
    @Autowired
    private SoporteRepository soporteRepository;

    public String almacenar(Soporte soporte) {
        soporteRepository.save(soporte);
        return "Soporte ingresado";
    }

    public List<Soporte> listar() {
        return soporteRepository.findAll();
    }

    public String eliminar(Integer id) {
        if(!soporteRepository.existsById(id)) {
            return "Soporte no encontrado!";
        } else {
            soporteRepository.deleteById(id);
            return "Soporte eliminado correctamente!";
        }
    }

    

}
