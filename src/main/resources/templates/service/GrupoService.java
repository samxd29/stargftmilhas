package br.com.gftMilhas.service;

import br.com.gftMilhas.model.Atividade;
import br.com.gftMilhas.model.Evento;
import br.com.gftMilhas.model.Grupo;
import br.com.gftMilhas.model.Participante;
import br.com.gftMilhas.repositories.GrupoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    public Grupo consultarPorId(Long id){
        Optional<Grupo> consultar = grupoRepository.findById(id);

        if (consultar.isEmpty()){
            throw new RuntimeException("Nenhum grupo cadastrado!");
        }

        return consultar.get();
    }

    public Grupo editarGrupo(Grupo grupo){
        var grupoeditar = consultarPorId(grupo.getId());

        grupo.setId(grupoeditar.getId());
        grupo.setNome(grupoeditar.getNome());
        grupo.setQtdPessoas(grupoeditar.getQtdPessoas());

        return grupoRepository.save(grupo);
    }

    public void excluirGrupo(Long id) {
        var consultarGrupo = consultarPorId(id);
        grupoRepository.deleteById(id);
    }

    public List<Grupo> listarTodos() {
        return grupoRepository.findAll();
    }

    @Transactional
    public void salvar(Grupo grupo) {
       grupoRepository.save(grupo);
    }

}
