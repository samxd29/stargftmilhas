package stargftmilhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stargftmilhas.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
