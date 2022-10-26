package br.com.gftMilhas.service;

import br.com.gftMilhas.model.Permissao;
import br.com.gftMilhas.repositories.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public List<Permissao> listaPermissao(){
        return permissaoRepository.findAll();
    }
}
