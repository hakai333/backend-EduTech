package com.vcore.backend_plataforma_web.DTO;

import java.time.LocalDate;

import com.vcore.backend_plataforma_web.model.Persona;
import com.vcore.backend_plataforma_web.model.Rol;
import com.vcore.backend_plataforma_web.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    //datos de usuario a mostrar
    private Integer usuarioId;
    private String nombreUsuario;
    private String email;
    private Boolean estado;
    private LocalDate fechaCreacion;


    //datos persona
    private Integer personaId;
    private String nombre;
    private String apellido;


    //datos de rol
    private Integer rolId;
    private String nombreRol;
    private String descripcionRol;

    public UsuarioDTO(Usuario usuario, Persona persona, Rol rol) {
        if(usuario != null) {
            this.usuarioId = usuario.getId();
            this.nombreUsuario = usuario.getNombre();
            this.email = usuario.getEmail();
            this.estado = usuario.getEsActivo();
            this.fechaCreacion = usuario.getFechaRegistro();

            if(usuario.getRol() != null){
                this.rolId = usuario.getRol().getId();
                this.nombreRol = usuario.getRol().getNombre();
                this.descripcionRol = usuario.getRol().getDescripcion();
            }
        }
        if(persona != null) {
            this.personaId = persona.getId();
            this.nombre = persona.getNombre();
            this.apellido = persona.getApellido();
        }

    }

    public UsuarioDTO(Usuario usuario, Rol rol) {
        if(usuario != null) {
            this.usuarioId = usuario.getId();
            this.nombreUsuario = usuario.getNombre();
            this.email = usuario.getEmail();
            this.estado = usuario.getEsActivo();
            this.fechaCreacion = usuario.getFechaRegistro();

            if(usuario.getRol() != null){
                this.rolId = usuario.getRol().getId();
                this.nombreRol = usuario.getRol().getNombre();
                this.descripcionRol = usuario.getRol().getDescripcion();
            }
        }
    }
}