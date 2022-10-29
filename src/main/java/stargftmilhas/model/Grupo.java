package stargftmilhas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Não pode ser vazio!")
    private String nome;

    @NotNull
    private int qtdPessoas;

    @NotNull(message = "Participantes não pode ser vazio")
    @ManyToMany
    @JoinTable(name="grupo_participantes",
            joinColumns=@JoinColumn(name="grupo_id"),
            inverseJoinColumns = @JoinColumn(name="participante_id"))
    private Set<Participante> participantes = new HashSet<>();

    @NotNull(message = "Evento não pode ser vazio")
    @ManyToOne
    private Evento evento;
}
