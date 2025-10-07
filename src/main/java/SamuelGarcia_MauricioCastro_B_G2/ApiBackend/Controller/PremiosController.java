package SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Controller;

import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Exception.ExceptioPremioDuplicado;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Exception.ExceptionPremioNoEncontrado;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Exception.ExceptiosDatosNoIngresados;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Models.DTO.PremiosDTO;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Service.PremiosServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Api/Premios")
public class PremiosController {

    @Autowired
    PremiosServices acceso;

    @GetMapping("/GetPremios")
    private ResponseEntity<List<PremiosDTO>> getData(){
        List<PremiosDTO> premio = acceso.ObtenerTodo();
        if (premio == null){
            ResponseEntity.badRequest().body(Map.of(
                    "status", "No hay categorias registradas"
            ));
        }
        return ResponseEntity.ok(premio);
    }

    @PostMapping("/PostPremios")
    public ResponseEntity<ApiResponse<PremiosDTO>> insertarActor(@Valid @RequestBody PremiosDTO json){
        if (json == null){
            throw new ExceptiosDatosNoIngresados("Error al recibir y procesar la información del usuario");
        }
        PremiosDTO usuarioGuardado = acceso.createActor(json);
        if (usuarioGuardado == null){
            throw new ExceptiosDatosNoIngresados("El usuario no pudo ser registrado debido a algun inconveniente con los datos");
        }
        return ResponseEntity.ok(ApiResponse.success("Usuario registrado exitosamente", usuarioGuardado));
    }

        @PutMapping("/PutPremios")
        public ResponseEntity<?> modificarUsuario (
                @PathVariable Long id,
                @Valid @RequestBody CategoryDTO usuario,
                BindingResult bindingResult){
            if (bindingResult.hasErrors()) {
                Map<String, String> errores = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error ->
                        errores.put(error.getField(), error.getDefaultMessage()));
                return ResponseEntity.badRequest().body(errores);
            }

            try {
                PremiosDTO PremioActualizado = acceso.actualizarDatos(id, premio);
                return ResponseEntity.ok(PremioActualizado);
            } catch (ExceptionPremioNoEncontrado e) {
                return ResponseEntity.notFound().build();
            } catch (ExceptioPremioDuplicado e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        Map.of("error", "Datos duplicados", "campo", e.getColumnDuplicate())
                );
            }
        }

        @DeleteMapping("/DeletePremios")
        public ResponseEntity<Map<String, Object>> eliminarUsuario (@PathVariable Long id){
            try {
                // Intenta eliminar la categoria usando objeto 'service'
                // Si el metodo delete retorna false (no encontró la categoria)
                if (!service.delete(id)) {
                    // Retorna una respuesta 404 (Not Found) con información detallada
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            // Agrega un header personalizado
                            .header("X-Mensaje-Error", "Categoría no encontrada")
                            // Cuerpo de la respuesta con detalles del error
                            .body(Map.of(
                                    "error", "Not found",  // Tipo de error
                                    "mensaje", "La categoria no ha sido encontrada",  // Mensaje descriptivo
                                    "timestamp", Instant.now().toString()  // Marca de tiempo del error
                            ));
                }

                // Si la eliminación fue exitosa, retorna 200 (OK) con mensaje de confirmación
                return ResponseEntity.ok().body(Map.of(
                        "status", "Proceso completado",  // Estado de la operación
                        "message", "Categoría eliminada exitosamente"  // Mensaje de éxito
                ));

            } catch (Exception e) {
                // Si ocurre cualquier error inesperado, retorna 500 (Internal Server Error)
                return ResponseEntity.internalServerError().body(Map.of(
                        "status", "Error",  // Indicador de error
                        "message", "Error al eliminar la categoría",  // Mensaje general
                        "detail", e.getMessage()  // Detalles técnicos del error (para debugging)
                ));
            }
        }

    }
}
