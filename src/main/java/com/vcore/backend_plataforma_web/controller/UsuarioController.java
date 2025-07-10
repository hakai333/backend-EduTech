package com.vcore.backend_plataforma_web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcore.backend_plataforma_web.DTO.UsuarioDTO;
import com.vcore.backend_plataforma_web.model.Rol;
import com.vcore.backend_plataforma_web.model.Usuario;
import com.vcore.backend_plataforma_web.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    // MIGUEL REYES
    // CREAR USUARIO SERVICE
    @PostMapping("/crear/{idUsuarioActual}")
    @Operation(summary = "Crea un usuario desde un usuario con rol 'Administrador del sistema'.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    public ResponseEntity<String> crearUsuario(
            @RequestBody @Schema(description = "Datos del usuario a crear") Usuario usuarioAcrear,
            @Parameter(description = "ID del usuario administrador que realiza la acción") 
            @PathVariable Integer idUsuarioActual) {
        Usuario usuarioActual = usuarioService.buscarPorId(idUsuarioActual);
        String resultado = usuarioService.crearUsuario(usuarioAcrear, usuarioActual);
        if (resultado.equals("Usuario almacenado correctamente")) {
            return ResponseEntity.ok("Usuario con ID " + usuarioAcrear.getId() + " creado correctamente!");
        }
        return ResponseEntity.badRequest().body(resultado);
    }

    // MIGUEL REYES
    // ACTUALIZAR USUARIO SERVICE
    @PostMapping("/actualizar/{idUsuarioActual}/{idUsuarioActualizar}")
    @Operation(summary = "Actualiza un usuario por ID desde un usuario con rol 'Administrador del sistema'.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    public ResponseEntity<String> actualizarUsuario(
            @RequestBody @Schema(description = "Datos actualizados del usuario") Usuario usuarioActualizar,
            @Parameter(description = "ID del usuario administrador que realiza la acción") 
            @PathVariable Integer idUsuarioActual,
            @Parameter(description = "ID del usuario a actualizar") 
            @PathVariable Integer idUsuarioActualizar) {
        Usuario usuarioActual = usuarioService.buscarPorId(idUsuarioActual);
        String resultado = usuarioService.actualizarUsuario(usuarioActualizar, usuarioActual, idUsuarioActualizar);
        if (resultado.equals("Usuario actualizado")) {
            return ResponseEntity.ok("Usuario con ID " + idUsuarioActualizar + " actualizado correctamente!");
        }
        return ResponseEntity.badRequest().body(resultado);
    }

    // MIGUEL REYES
    // DESACTIVAR USUARIO SERVICE
    @PostMapping("/desactivar/{idUsuarioActual}/{idUsuarioDesactivar}")
    @Operation(summary = "Desactiva un usuario por ID desde un usuario con rol 'Administrador del sistema'.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario desactivado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    public ResponseEntity<String> desactivarUsuario(
            @Parameter(description = "ID del usuario administrador que realiza la acción") 
            @PathVariable Integer idUsuarioActual,
            @Parameter(description = "ID del usuario a desactivar") 
            @PathVariable Integer idUsuarioDesactivar) {

        Usuario usuarioActual = usuarioService.buscarPorId(idUsuarioActual);

        String resultado = usuarioService.desactivarUsuario(usuarioActual, idUsuarioDesactivar);

        if (resultado.equals("Usuario desactivado")) {
            return ResponseEntity.ok("Usuario con ID " + idUsuarioDesactivar + " desactivado correctamente!");
        }
        return ResponseEntity.badRequest().body(resultado);
    }

    // MIGUEL REYES
    // ELIMINAR USUARIO SERVICE
    @DeleteMapping("/eliminar/{idUsuarioActual}/{idUsuarioAEliminar}")
    @Operation(summary = "Borra de la BD a un usuario por ID desde un usuario con rol 'Administrador del sistema'.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    public ResponseEntity<String> eliminarUsuario(
            @Parameter(description = "ID del usuario administrador que realiza la acción") 
            @PathVariable Integer idUsuarioActual,
            @Parameter(description = "ID del usuario a eliminar") 
            @PathVariable Integer idUsuarioAEliminar) {
        String resultado = usuarioService.eliminarUsuario(idUsuarioActual, idUsuarioAEliminar);

        if (resultado.equals("Usuario eliminado")) {
            return ResponseEntity.ok("Usuario con ID " + idUsuarioAEliminar + " eliminado correctamente!");
        } else {
            return ResponseEntity.badRequest().body(resultado);
        }
    }

    @GetMapping
    @Operation(summary = "Muestra la lista de usuarios ingresados en la BD con su informacion completa.")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente",
               content = @Content(schema = @Schema(implementation = Usuario.class)))
    public List<Usuario> listaUsuarios() {
        return usuarioService.listar();
    }

    // MIGUEL REYES
    // ASIGAR ROL O PERMISOS
    @PostMapping("/asignar-rol/{idUsuarioActual}/{idUsuarioAsignar}")
    @Operation(summary = "Permite administrar los roles de un usuario por ID desde un usuario con rol 'Administrador del sistema'.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rol asignado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    public ResponseEntity<String> asignarUsuario(
            @RequestBody @Schema(description = "Datos del rol a asignar") Rol rol,
            @Parameter(description = "ID del usuario administrador que realiza la acción") 
            @PathVariable Integer idUsuarioActual,
            @Parameter(description = "ID del usuario a asignar el rol") 
            @PathVariable Integer idUsuarioAsignar) {
        if (rol == null || rol.getNombre() == null) {
            return ResponseEntity.badRequest().body("Debe proporcionar un rol válido");
        }
        String resultado = usuarioService.asignarRol(rol, idUsuarioActual, idUsuarioAsignar);
        if (resultado.equals("Rol asignado")) {
            return ResponseEntity.ok("Usuario con ID " + idUsuarioAsignar + " asigando correctamente!");
        } else {
            return ResponseEntity.badRequest().body(resultado);
        }
    }

    @GetMapping("/usuarioDto1")
    @Operation(summary = "Muestra la lista de usuarios ingresados en la BD sin su contrasena.")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente",
               content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    public List<UsuarioDTO> obtenerUsuarioPersonaRolDto() {
        return usuarioService.obtenerUsuarioPersonaRolDto();
    }

    @GetMapping("/usuarioDto2")
    @Operation(summary = "Muestra la lista de usuarios ingresados en la BD sin su contrasena y sin sus datos personales.")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente",
               content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    public List<UsuarioDTO> obtenerUsuarioRolDto() {
        return usuarioService.obtenerUsuarioRolDto();
    }
}