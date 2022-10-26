package stargftmilhas.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Não pode ser vazio!")
    private String nome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataFinal;

    @OneToMany(mappedBy = "evento")
    private List<Atividade> atividade = new ArrayList<>();

    @OneToMany(mappedBy = "evento")
    private List<Grupo> grupos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="evento_informacaoevento",
            joinColumns=@JoinColumn(name="evento_id"),
            inverseJoinColumns = @JoinColumn(name="informacaoevento_id"))
    private Set<InformacaoEvento> informacaoEventos;
}
