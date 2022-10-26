package stargftmilhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stargftmilhas.model.Presenca;

@Repository
public interface PresencaRepository extends JpaRepository<Presenca, Long> {
}
