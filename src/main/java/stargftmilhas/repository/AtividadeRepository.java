package stargftmilhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stargftmilhas.model.Atividade;

import java.util.Optional;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    Optional<Atividade> findById(Long id);

    Atividade findAtividadeById(Long id);
}
