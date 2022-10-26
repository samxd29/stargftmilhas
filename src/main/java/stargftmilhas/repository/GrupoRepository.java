package stargftmilhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stargftmilhas.model.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}
