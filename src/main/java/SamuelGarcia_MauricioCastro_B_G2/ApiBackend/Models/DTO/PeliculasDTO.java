package SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Models.DTO;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class PeliculasDTO {
    private Long idPelicula;
    private String titulo;
    private String director;
    private String genero;
    private Long anoEstreno;
    private Long duracionMin;
    private Date fechaCreacion;
}
