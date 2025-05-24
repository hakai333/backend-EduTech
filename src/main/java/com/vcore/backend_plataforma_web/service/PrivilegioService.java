package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Privilegio;
import com.vcore.backend_plataforma_web.repository.PrivilegioRepository;



@Service
public class PrivilegioService {
    @Autowired
    private PrivilegioRepository privilegioRepository;
    public String almacenar(Privilegio privilegio) {
        Privilegio validacion = privilegioRepository.findByCodigo(privilegio.getCodigo());
        if(validacion == null) {
            privilegioRepository.save(privilegio);
            return "Privilegio almacenado correctamente!";
        } else {
            return "Privilegio ya ingresado!";
        }
    }

    public List<Privilegio> listar() {
        return privilegioRepository.findAll();
    }
}
