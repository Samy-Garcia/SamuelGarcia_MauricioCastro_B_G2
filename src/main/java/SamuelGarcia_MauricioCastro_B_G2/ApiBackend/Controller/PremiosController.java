package SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Controller;

import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Exception.ExceptionPremioNoEncontrado;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Exception.ExceptiosDatosNoIngresados;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Models.ApiResponse.ApiResponse;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Models.DTO.PremiosDTO;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Service.PremiosServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    private ResponseEntity<List<PremiosDTO>> getData() {
        List<PremiosDTO> premio = acceso.ObtenerTodo();
        if (premio == null) {
            ResponseEntity.badRequest().body(Map.of(
                    "status", "No hay categorias registradas"
            ));
        }
        return ResponseEntity.ok(premio);
    }

    @PostMapping("/PostPremios")
    public ResponseEntity<ApiResponse<PremiosDTO>> insertarActor(@Valid @RequestBody PremiosDTO json) {
        if (json == null) {
            throw new ExceptiosDatosNoIngresados("Error al recibir y procesar la información del usuario");
        }
        PremiosDTO usuarioGuardado = acceso.createActor(json);
        if (usuarioGuardado == null) {
            throw new ExceptiosDatosNoIngresados("El usuario no pudo ser registrado debido a algun inconveniente con los datos");
        }
        return ResponseEntity.ok(ApiResponse.success("Usuario registrado exitosamente", usuarioGuardado));
    }

    @PutMapping("/PutPremios")
    public ResponseEntity<?> editarEmpresa(
            @PathVariable Long id, //Extraer el id que va en la URL
            @Valid @RequestBody PremiosDTO json,
            BindingResult bindingResult //Contiene los resultados de la validacion @Valid

    ){
        //1. Vereficar si hay errores de validacion con BindingResult
        if(bindingResult.hasErrors()) {
            //2. Crear un mapa de errores (campo-> mensaje
            Map<String, String> errores = new HashMap<>();
            //3. Interar sobre cada error y lo agrega al objeto Map
            bindingResult.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
            //4. Retornar error HTTP 400 (Bad Request con los errores de validacion
            return ResponseEntity.badRequest().body(errores);
        }

        try {
            // Intentar actualizar la empresa llamando al servicio
            PremiosDTO dto = acceso.actualizarDatos(id, json);
            return ResponseEntity.ok(dto);

        } catch (ExceptionPremioNoEncontrado e) {
            return ResponseEntity.notFound().build();

        }
    }


    @DeleteMapping("/DeletePremios")
        public ResponseEntity<Map<String, Object>> eliminarUsuario (@PathVariable Long id){
            try {
                // Intenta eliminar la categoria usando objeto 'service'
                // Si el metodo delete retorna false (no encontró la categoria)
                if (!acceso.deleteActor(id)) {
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

