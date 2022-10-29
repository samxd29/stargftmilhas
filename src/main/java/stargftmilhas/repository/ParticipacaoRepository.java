package stargftmilhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stargftmilhas.model.Participacao;

@Repository
public interface ParticipacaoRepository extends JpaRepository<Participacao, Long> {
}
