package SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Controller;

import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Models.DTO.PremiosDTO;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Service.PremiosServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Api/Premios")
public class PremiosController {

    @Autowired
    PremiosServices acceso;

    @GetMapping("/GetPremios")
    private ResponseEntity<List<PremiosDTO>> getData(){
        List<PremiosDTO> premios = acceso
        return acceso.ObtenerPremios();
    }

    @PostMapping("/PostPremios")
    public ResponseEntity<?> agregarUsuario(@Valid @RequestBody     PremiosDTO json, HttpServletRequest request){
        try {
                PremiosDTO respuesta = acceso.insertarDatos(json);
            if(respuesta == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "iNSERCION DE DATOS FALLIDA",
                        "errorType","VALIDATION ERROR",
                        "message", "Error al insertar los datos"
           ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "Success",
                    "data", respuesta
       ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error",
                    "messege", "Error no controlada al reguistrar datos",
                    "details", e.getMessage()
            ));
        }

    @PutMapping("/PutPremios")


    @DeleteMapping("/DeletePremios")

}
