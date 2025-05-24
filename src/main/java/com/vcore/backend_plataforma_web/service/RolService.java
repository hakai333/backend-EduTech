package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Rol;
import com.vcore.backend_plataforma_web.repository.RolRepository;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;
    public String almacenar(Rol rol) {
        Rol validacion = rolRepository.findByNombre(rol.getNombre());
        if(validacion == null) {
            rolRepository.save(rol);
            return "Rol almacenado correctamente!";
        } else {
            return "Rol ya ingresado!";
        }
    }

    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    public List<Rol> buscar(String nombre) {
        return rolRepository.findByNombreContaining(nombre);
    }


}
