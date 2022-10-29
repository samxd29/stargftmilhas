package stargftmilhas.model;


import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class EntregaID implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="id_atividade")
    private Atividade atividade;
    
    @ManyToOne
    @JoinColumn(name="id_participante")
    private Participante participante;
}
