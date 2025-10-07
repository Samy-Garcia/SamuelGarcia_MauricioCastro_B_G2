package SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Service;

import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Entities.PremiosEntity;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Exception.ExceptionPremioNoEncontrado;
import SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Exception.ExceptiosDatosNoIngresados;
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

    public PremiosDTO insertarDatos(@Valid PremiosDTO json){
        if (json == null || json.getNombrePremio() == null || json.getNombrePremio().isEmpty()){

            throw  new IllegalArgumentException("Datos no Ingresados");

        }
        try {
            PremiosEntity entity = ConvertirAEntity(json);
            PremiosEntity respuesta = repo.save(entity);

            return ConvertirAPremiosDTO(respuesta);
        }catch (Exception e){
            Log error ("Error al Insertar Datos" + e.getMessage());
            throw new ExceptiosDatosNoIngresados("Datos No Ingresados");
        }
    }

    public PremiosDTO actualizarDatos(Long id,@Valid PremiosDTO json){
        PremiosEntity PremioExistente = repo.findById(id).orElseThrow(()-> new ExceptionPremioNoEncontrado("No se ha Encontrado ese Premio"));

        PremioExistente = ConvertirAEntity(json);

        PremiosEntity PremioActualizado = repo.save(PremioExistente);

        return ConvertirAPremiosDTO(PremioActualizado);
    }

    public boolean (Long id){
        try {
            PremiosEntity objEntity = repo.findById(id).orElse(null);

            if (objEntity != null){
                return true;
            }
            return false;
        }catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("No se Encontro premio con ID:" + id , 1);
        }
    }
    public PremiosDTO ConvertirAPremiosDTO(PremiosEntity entity){
        PremiosDTO dto = new PremiosDTO();

        dto.setIdPremios(entity.getId());
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
