package stargftmilhas.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Presenca {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataPresenca;

    @ManyToMany
    private List<Evento> eventos;

    @ManyToMany
    private List<Grupo> grupos;


}
