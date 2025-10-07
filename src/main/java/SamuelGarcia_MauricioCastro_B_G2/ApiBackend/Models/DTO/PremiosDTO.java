package SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Models.DTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class PremiosDTO {

    private Long idPremio;
    private Long idPelicula;
    private String nombrePremio;
    private String categoria;
    private String anoPremio;
    private String resultado;
    private Date fechaRegistro;

}
