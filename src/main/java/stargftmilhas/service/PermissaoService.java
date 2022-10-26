package stargftmilhas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stargftmilhas.model.Permissao;
import stargftmilhas.repository.PermissaoRepository;

import java.util.List;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public List<Permissao> listaPermissao(){
        return permissaoRepository.findAll();
    }
}
