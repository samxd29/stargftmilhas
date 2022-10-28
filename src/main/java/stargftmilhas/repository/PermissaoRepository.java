package stargftmilhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stargftmilhas.model.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}