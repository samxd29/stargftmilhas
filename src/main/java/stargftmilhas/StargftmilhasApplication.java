package stargftmilhas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import stargftmilhas.model.*;
import stargftmilhas.repository.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class StargftmilhasApplication implements CommandLineRunner {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private ParticipacaoRepository participacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PresencaRepository presencaRepository;

    public static void main(String[] args) {
        SpringApplication.run(StargftmilhasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        Date dataAtual = new Date();
//
//        Presenca presenca = new Presenca();
//        presenca.setDataPresenca(dataAtual);
//        presencaRepository.save(presenca);
//
//        Permissao permissao = new Permissao();
//        permissao.setNome("ROLE_ADMIN");
//        permissaoRepository.save(permissao);
//
//        Usuario usuario = new Usuario();
//        usuario.setNome("Samantha");
//        usuario.setSenha(new BCryptPasswordEncoder().encode(("123")));
//        usuario.setPermissoes(List.of(permissao));
//        usuarioRepository.save(usuario);
//
//        Participante participante = new Participante();
//        participante.setNome("Samantha");
//        participante.setNivel("L1");
//        participante.setEmail("salu@gft.com");
//        participante.setQuatroLetras("salu");
//
//        Participante participante1 = new Participante();
//        participante1.setNome("Loyanna");
//        participante1.setNivel("L1");
//        participante1.setEmail("loyanna@gft.com");
//        participante1.setQuatroLetras("loya");
//
//        Participante participante2 = new Participante();
//        participante2.setNome("Mariana");
//        participante2.setNivel("L1");
//        participante2.setEmail("mari@gft.com");
//        participante2.setQuatroLetras("mari");
//
//        Participante participante3 = new Participante();
//        participante3.setNome("Alex");
//        participante3.setNivel("L1");
//        participante3.setEmail("alex@gft.com");
//        participante3.setQuatroLetras("alex");
//        participanteRepository.save(participante);
//        participanteRepository.save(participante1);
//        participanteRepository.save(participante2);
//        participanteRepository.save(participante3);
//
//        Atividade atividade = new Atividade();
//
//        EntregaID entregaID = new EntregaID();
//        entregaID.setParticipante(participante);
//        entregaID.setAtividade(atividade);
//
//        atividade.setEntrega();
//        atividadeRepository.save(atividade);
//
//        Entrega entrega = new Entrega();
//        entrega.setStatus(Status.ENTREGUE);
//        entrega.setId(entregaID);
//        entrega.setAtividade(atividade);
//        eventoRepository.save(entrega);
//
//
////
//        atividade.setNome("Java 11");
//        atividade.setDataInicio(dataAtual);
//        atividade.setDataFinal(dataAtual);
//        atividade.setEvento(null);
//        atividade.setEntrega(entrega);
//
//        Evento evento = new Evento();
//        Date dataFinal = new Date();
//        evento.setNome("GFT Starter");
//        evento.setDataInicio(dataAtual);
//        evento.setDataFinal(dataFinal);
//
//        evento.setAtividade(List.of(new Atividade(null, "MVC Spring", dataAtual, dataFinal, evento, List.of(entrega))));
//        evento.setGrupos(List.of(new Grupo()));
//        evento.setParticipacao(Set.of(new Participacao()));
//        eventoRepository.save(evento);


    }

}
