package SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Controller;

import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Models.DTO.PremiosDTO;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Service.PremiosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Api/Premios")
public class PremiosController {

    @Autowired
    PremiosServices acceso;

    
}
