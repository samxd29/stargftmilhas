package stargftmilhas.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
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

    @NotNull(message = "Atividade não pode ser vazia")
    @OneToMany(mappedBy = "evento")
    private List<Atividade> atividade = new ArrayList<>();

    @NotNull(message = "Grupo não pode ser vazio")
    @OneToMany(mappedBy = "evento", fetch = FetchType.EAGER)
    private List<Grupo> grupos = new ArrayList<>();


    @ManyToMany
    @JoinTable(name="evento_participacao",
            joinColumns=@JoinColumn(name="evento_id"),
            inverseJoinColumns = @JoinColumn(name="participacao_id"))
    private Set<Participacao> Participacao;
}
