package SamuelGarcia_MauricioCastro_B_G2.ApiBackend.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "PREMIOS")
public class PremiosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_premios")
    @SequenceGenerator(name = "seq_premios", sequenceName =  "seq_premios", allocationSize = 1)
    @Column(name = "IDPREMIOS")
    private Long id;

    @Column(name = "NOMBRE_PREMIO")
    private String nombrePremio;
    @Column(name = "CATEGORIA")
    private String categoria;
    @Column(name = "ANO_PREMIO")
    private String anoPremio;
    @Column(name = "RESULTADO")
    private String resultado;
    @Column(name = "FECHA_REGISTRO")
    private Date fechaRegistro;

    @OneToMany(mappedBy = "PELICULA", cascade = CascadeType.ALL)
    private List<PremiosEntity> premios;



}
