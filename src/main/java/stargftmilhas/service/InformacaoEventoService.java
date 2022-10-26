package stargftmilhas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stargftmilhas.model.Grupo;
import stargftmilhas.model.InformacaoEvento;
import stargftmilhas.model.Participante;
import stargftmilhas.repository.InformcaoEventoRepository;

import java.util.*;

@Service
public class InformacaoEventoService {

    @Autowired
    private InformcaoEventoRepository informcaoEventoRepository;
    @Autowired
    private ParticipanteService participanteService;

    @Autowired
    private EventoService eventoService;

    public void GerarInformacaoEvento(InformacaoEvento informacaoEvento){
        Set<InformacaoEvento> informacaoEventos = new HashSet<>();
        var evento = eventoService.buscarEventoPorId(informacaoEvento.getId());
        for (Grupo grupo : evento.getGrupos()){
            for (Participante participante : grupo.getParticipantes()){
//                informacaoEventos.add(new InformacaoEvento(null,false, participante, evento));
                informcaoEventoRepository.save(new InformacaoEvento(null, false, participante));
            }
        }
        evento.setInformacaoEventos(informacaoEventos);
        eventoService.salvarEvento(evento);
    }

//    public InformacaoEvento registrar(InformacaoEvento informacaoEvento) throws Exception {
//        for( Participante participante : informacaoEvento.getParticipantes()) {
//            var resultado = participanteService.buscarParticipantePorId(participante.getId());
//            participantes.add(resultado);
//        }
//        informacaoEvento.setParticipantes(participantes);
//        return informcaoEventoRepository.save(informacaoEvento);
//    }

    public InformacaoEvento salvarParticipacao(InformacaoEvento informacaoEvento) {
        return informcaoEventoRepository.save(informacaoEvento);
    }

    public InformacaoEvento buscarParticipacaoPorID(Long id) throws Exception {
        Optional<InformacaoEvento> participacao = informcaoEventoRepository.findById(id);
        return participacao.get();
    }

    public List<InformacaoEvento> listarTodos() {
        return informcaoEventoRepository.findAll();
    }


}
