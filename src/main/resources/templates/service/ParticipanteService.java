package br.com.gftMilhas.service;

import br.com.gftMilhas.model.Participante;
import br.com.gftMilhas.repositories.ParticipanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService {
    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private GrupoService grupoService;

    public List<Participante> listarTodos() {
        return participanteRepository.findAll();
    }

    public List<Participante> listarParticipantes(String nome){

        if(nome != null) {
            return listarParticipantesPorNome(nome);
        }

        return listarTodos();
    }

    private List<Participante> listarParticipantesPorNome(String nome){
        return participanteRepository.findByNomeContains(nome);
    }

    public List<Participante> listarParticipantesPorGrupoId(Long id){
        return participanteRepository.findByParticipanteByGrupo(id);
    }

    public Participante buscarParticipantePorId(Long id) throws Exception {
        Optional<Participante> participante = participanteRepository.findById(id);

        if(participante.isEmpty()) {
            throw new Exception("Panticipante não encontrado!");
        }
        return participante.get();
    }

    public void excluir(Long id) throws Exception {
        var participante = buscarParticipantePorId(id);
        if (participante == null) {
            throw new RuntimeException("Participante não cadastrado!");
        }
        participanteRepository.delete(participante);
    }

    public Participante salvarParticipante(Participante participante) {
        return participanteRepository.save(participante);
    }
}
