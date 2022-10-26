package stargftmilhas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stargftmilhas.repository.PresencaRepository;

@Service
public class PresencaService {

    @Autowired
    private PresencaRepository presencaRepository;
}
