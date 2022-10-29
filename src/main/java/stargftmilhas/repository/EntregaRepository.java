package stargftmilhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stargftmilhas.model.Entrega;
import stargftmilhas.model.EntregaID;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, EntregaID> {
}
