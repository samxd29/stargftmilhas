package stargftmilhas.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Não pode ser vazio!")
    private String nome;

    private int qtdPessoas;

    @ManyToMany
    @JoinTable(name="grupo_participantes",
            joinColumns=@JoinColumn(name="grupo_id"),
            inverseJoinColumns = @JoinColumn(name="participante_id"))
    private Set<Participante> participantes = new HashSet<>();

    @ManyToOne
    private Evento evento;
}
