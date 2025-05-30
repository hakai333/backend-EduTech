package com.vcore.backend_plataforma_web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcore.backend_plataforma_web.model.Persona;
<<<<<<< HEAD
import com.vcore.backend_plataforma_web.model.Usuario;
import com.vcore.backend_plataforma_web.repository.PersonaRepository;
=======
import com.vcore.backend_plataforma_web.model.Rol;
import com.vcore.backend_plataforma_web.model.Usuario;
import com.vcore.backend_plataforma_web.repository.PersonaRepository;
import com.vcore.backend_plataforma_web.repository.RolRepository;
>>>>>>> origin/master
import com.vcore.backend_plataforma_web.repository.UsuarioRepository;

//bastian
@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;
<<<<<<< HEAD
    @Autowired 
    private UsuarioRepository usuarioRepository;
=======

    @Autowired
    private UsuarioRepository usuarioRepository;

>>>>>>> origin/master
    public String almacenar(Persona persona) {
        if(!personaRepository.existsById(persona.getId())) {
            personaRepository.save(persona);
            return "Persona ingresada";
        } 
        
        return "Error";
    }


    public String asignarUsuario(String rutPersona, Integer idUsuario) {
        Persona persona = personaRepository.findByRut(rutPersona);
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        if(usuarioRepository.existsById(idUsuario)) {
            usuario.setPersona(persona);
            usuarioRepository.save(usuario);
            personaRepository.save(persona);
            return "Persona almacenada correctamente!";
        } else {
            return "Persona ya ingresada!";
        }
    }

    public List<Persona> listar() {
        return personaRepository.findAll();
    }

    public String asignarUsuario(String rutPersona, Integer idUsuario) {
            Persona persona = personaRepository.findByRut(rutPersona);
            Usuario usuario = usuarioRepository.findById(idUsuario).get();
            if(usuarioRepository.existsById(idUsuario)) {
                usuario.setPersona(persona);
                usuarioRepository.save(usuario);
                personaRepository.save(persona);
                return "Se asigna rut " + persona.getRut() + " a " + usuario.getNombre()+
                 " correctamente!";
            } else {
                return "Persona ya ingresada!";
            }
        }



}
