package stargftmilhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stargftmilhas.model.InformacaoEvento;

@Repository
public interface InformcaoEventoRepository extends JpaRepository<InformacaoEvento, Long> {
}
