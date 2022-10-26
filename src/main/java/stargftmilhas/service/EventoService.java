package stargftmilhas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stargftmilhas.model.Atividade;
import stargftmilhas.model.Evento;
import stargftmilhas.repository.EventoRepository;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private AtividadeService atividadeService;


    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Evento buscarEventoPorId(Long id) {
        Evento evento = eventoRepository.findById(id).get();
        if (evento == null) {
            throw new RuntimeException("Não foi possível localizar evento");
        }
        return evento;
    }

    // metodo salvar
    public Evento salvarEvento(Evento evento) {

//        Atividade atividade = atividadeService.consultarAtividadePorId(evento.getAtividade().getId());
//
//        evento.setAtividade(atividade);

        return eventoRepository.save(evento);
    }

    public void excluir(Long id) {
        var evento = buscarEventoPorId(id);
        if (evento == null) {
            throw new RuntimeException("Evento não cadastrado!");
        }
        eventoRepository.delete(evento);
    }
}
