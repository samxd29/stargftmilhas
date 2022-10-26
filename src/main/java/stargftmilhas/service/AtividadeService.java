package stargftmilhas.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stargftmilhas.model.Atividade;
import stargftmilhas.repository.AtividadeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public Atividade consultarAtividadePorId(Long id){
        Optional<Atividade> consultar = atividadeRepository.findById(id);

        if (consultar.isEmpty()){
            throw new RuntimeException("Nenhuma atividade cadastrada!");
        }

        return consultar.get();
    }

    public List<Atividade> listarTodasAtividades(){
        return atividadeRepository.findAll();
    }

    public Atividade editarAtividade(Atividade atividade) {
        var editar = atividadeRepository.findById(atividade.getId());

        atividade.setId(atividade.getId());
        atividade.setNome(atividade.getNome());
        atividade.setDataInicio(atividade.getDataInicio());
        atividade.setDataFinal(atividade.getDataFinal());

        return atividadeRepository.save(atividade);
    }

    public Atividade salvarAtividade(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }


    public void excluirAtividade(Long id) {
      var consultarAtividade = atividadeRepository.findById(id);

      if (consultarAtividade != null) {
          atividadeRepository.deleteById(id);
      } else {
          throw new RuntimeException("Atividade não pode ser excluida, pois está vinculado a Evento!");
      }
    }
}
