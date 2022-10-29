package stargftmilhas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stargftmilhas.model.Participacao;
import stargftmilhas.model.Participante;
import stargftmilhas.model.Usuario;
import stargftmilhas.repository.ParticipacaoRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ParticipacaoService {

    @Autowired
    private ParticipacaoRepository participacaoRepository;
    @Autowired
    private ParticipanteService participanteService;


    private Set<Participante> participantes = new HashSet<>();

    public Participacao registrar(Participacao participacao) throws Exception {
        for (Participante participante : participacao.getParticipantes()) {
            var resultado = participanteService.buscarParticipantePorId(participante.getId());
            participantes.add(resultado);
        }
        participacao.setParticipantes(participantes);
        return participacaoRepository.save(participacao);
    }

    public Participacao salvarParticipacao(Participacao participacao) {
        return participacaoRepository.save(participacao);
    }

    public Participacao buscarParticipacaoPorID(Long id) throws Exception {
        Optional<Participacao> participacao = participacaoRepository.findById(id);

        return participacao.get();
    }

    public List<Participacao> listarTodos() {
        return participacaoRepository.findAll();
    }

    public void excluir(Long id) throws Exception {
        var participante = buscarParticipacaoPorID(id);
        if (participante == null) {
            throw new RuntimeException("Participante não cadastrado!");
        }
        participacaoRepository.delete(participante);
    }

}