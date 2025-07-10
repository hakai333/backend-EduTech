package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Incidencia;
import com.vcore.backend_plataforma_web.model.Usuario;
import com.vcore.backend_plataforma_web.repository.IncidenciaRepository;
import com.vcore.backend_plataforma_web.repository.UsuarioRepository;

@Service
public class IncidenciaService {
    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String almacenar(Incidencia incidencia, Integer id) {
        if(!usuarioRepository.existsById(id)){
            return "El usaurio ingresado no existe";
        } else {
            Usuario usuario = usuarioRepository.findById(id).get();
            incidencia.setUsuario(usuario);
            incidenciaRepository.save(incidencia);
            return "Incidencia ingresada";
        }
    }

    public List<Incidencia> listar() {
        return incidenciaRepository.findAll();
    }



}
