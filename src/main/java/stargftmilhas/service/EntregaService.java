package stargftmilhas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stargftmilhas.model.Atividade;
import stargftmilhas.model.Entrega;
import stargftmilhas.model.Grupo;
import stargftmilhas.model.Participante;
import stargftmilhas.repository.AtividadeRepository;
import stargftmilhas.repository.EntregaRepository;
import stargftmilhas.repository.ParticipanteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    public List<Participante> obterParticipantes(Long id) {
        Atividade atividade = atividadeRepository.findAtividadeById(id);
        List<Grupo> consultar = atividade.getEvento().getGrupos();
        List<Participante> participanteLista = new ArrayList<>();

        for (Grupo grupo : consultar) {
            participanteLista.addAll(grupo.getParticipantes());
        }

        return participanteLista;
    }

    public Entrega salvar(Entrega entrega) {
        return entregaRepository.save(entrega);
    }
}
