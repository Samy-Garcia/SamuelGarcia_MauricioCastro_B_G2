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
@Table(name = "PELICULAS")
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_peliculas")
    @SequenceGenerator(name = "seq_peliculas", sequenceName =  "seq_peliculas", allocationSize = 1)
    @Column(name = "ID_PELICULA")
    private Long idPelicula;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "DIRECTOR")
    private String director;
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "ANO_ESTRENO")
    private Long anoEstreno;
    @Column(name = "DURACION_MIN")
    private Long duracionMin;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL)
    private List<PremiosEntity> premios;
}
