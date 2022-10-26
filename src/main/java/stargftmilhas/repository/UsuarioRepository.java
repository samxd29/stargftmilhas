package stargftmilhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stargftmilhas.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNome(String nome);
}
