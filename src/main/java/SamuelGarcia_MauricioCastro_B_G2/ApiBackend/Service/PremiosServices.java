package SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Service;

import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Entities.PremiosEntity;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Exception.ExceptionPremioNoEncontrado;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Exception.ExceptiosDatosNoIngresados;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Models.DTO.PeliculasDTO;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Models.DTO.PremiosDTO;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Repositories.PremioRepository;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PremiosServices {
    @Autowired
    PremioRepository repo;
    public List<PremiosDTO>ObtenerTodo(){
        List<PremiosEntity>premios = repo.findAll();
        return premios.stream()
                .map(this::ConvertirAPremiosDTO)
                .collect(Collectors.toList());
    }

    public List<PremiosDTO>ObtenerPremios(){
        List<PremiosEntity> premios = repo.findAll();
        return premios.stream()
                .map(this::ConvertirAPremiosDTO)
                .collect(Collectors.toList());
    }

    public  PremiosDTO createActor(@Valid PremiosDTO json) {
        PremiosEntity objEntity = ConvertirAEntity(json);
        PremiosEntity savedUser = repo.save(objEntity);
        return ConvertirAPremiosDTO(savedUser);
    }

    public PremiosDTO actualizarDatos(Long id,@Valid PremiosDTO json){
        PremiosEntity PremioExistente = repo.findById(id).orElseThrow(()-> new ExceptionPremioNoEncontrado("No se ha Encontrado ese Premio"));
        PremioExistente = ConvertirAEntity(json);
        PremiosEntity PremioActualizado = repo.save(PremioExistente);
        return ConvertirAPremiosDTO(PremioActualizado);
    }

    public boolean deleteActor(Long id) {
        PremiosEntity existente = repo.findById(id).orElse(null);
        if (existente!=null){
            repo.deleteById(id);
            return true;
        }else {
            log.error("Premio no encontrado");
            return false;
        }
    }

    public PremiosDTO ConvertirAPremiosDTO(PremiosEntity entity){
        PremiosDTO dto = new PremiosDTO();
        dto.setIdPremio(entity.getIdPremio());
        dto.setNombrePremio(entity.getNombrePremio());
        dto.setCategoria(entity.getCategoria());
        dto.setAnoPremio(entity.getAnoPremio());
        dto.setResultado(entity.getResultado());
        dto.setFechaRegistro(entity.getFechaRegistro());

        return dto;
    }
    private PremiosEntity ConvertirAEntity(@Valid PremiosDTO json){
        PremiosEntity entity = new PremiosEntity();
        entity.setNombrePremio(json.getNombrePremio());
        entity.setCategoria(json.getCategoria());
        entity.setAnoPremio(json.getAnoPremio());
        entity.setResultado(json.getResultado());
        entity.setFechaRegistro(json.getFechaRegistro());

        return entity;
    }
}
