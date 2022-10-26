package stargftmilhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import stargftmilhas.model.Participante;

import javax.servlet.http.Part;
import java.util.List;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    List<Participante> findByNomeContains(String nome);

    @Query(value = "SELECT * FROM grupo_participantes WHERE grupo_id = ?1", nativeQuery = true)
    List<Participante> findByGrupo(Long id);
}
