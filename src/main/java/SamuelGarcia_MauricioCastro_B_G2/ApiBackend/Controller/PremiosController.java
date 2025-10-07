package SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Controller;

import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Models.DTO.PremiosDTO;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Service.PremiosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/Premios")
public class PremiosController {

    @Autowired
    PremiosServices acceso;

    @GetMapping("/GetPremios")
    public List<PremiosDTO>ObtenerDatos{
        return acceso.ObtenerPremios();
    }

    @PostMapping("/PostPremios")


    @PutMapping("/PutPremio")


    @DeleteMapping("/DeletePremio")

}
