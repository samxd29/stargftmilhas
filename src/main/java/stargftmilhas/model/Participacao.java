package stargftmilhas.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Participacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dataRegistro;

    @OneToOne
    private Atividade atividade;

    @ManyToMany
    @JoinTable(name="participacao_participantes",
            joinColumns=@JoinColumn(name="participacao_id"),
            inverseJoinColumns = @JoinColumn(name="participante_id"))
    private Set<Participante> participantes = new HashSet<>();

}
