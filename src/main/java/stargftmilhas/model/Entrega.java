package stargftmilhas.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Entrega {
    @EmbeddedId
    private EntregaID id = new EntregaID();

    @Enumerated(EnumType.STRING)
    private Status status;

    public void setAtividade(Atividade atividade) {
        id.setAtividade(atividade);
    }

    public void setParticipantes(Participante participante) {
        id.setParticipante(participante);
    }
}
