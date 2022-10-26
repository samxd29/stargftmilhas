package stargftmilhas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import stargftmilhas.model.Permissao;
import stargftmilhas.model.Usuario;
import stargftmilhas.repository.UsuarioRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario editarUsuario(Usuario usuario) {
        var usuarioEditar = buscarUsusarioPorId(usuario.getId());

        usuarioEditar.setNome(usuario.getNome());
        usuarioEditar.setPermissoes(usuario.getPermissoes());
        usuarioEditar.setSenha(usuario.getSenha());

        return usuarioRepository.save(usuario);
    }

    public Usuario usuarioSalvar(Usuario usuario) {
        if (usuario.getId() == null){
            var usuarioExistente = usuarioRepository.findByNome(usuario.getNome());
            if(usuarioExistente.isPresent()){
                throw new RuntimeException("Usuário já cadastrado.");
            }
        }
        if(usuario.getPermissoes().isEmpty()){
            usuario.setPermissoes(Collections.singletonList(new Permissao(2L, "ROLE_USER")));
        }
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public void usuarioExcluir(Long id) {
        var usuario = buscarUsusarioPorId(id);
        if (usuario == null) {
            throw new RuntimeException("Usuário não cadastrado!");
        }
        usuarioRepository.delete(usuario);
    }

    public Usuario obterUsuario(Long id) throws Exception{
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            throw new Exception("Usuario não encontrado!");
        }

        return usuario.get();
    }

    private Usuario buscarUsusarioPorId(Long id) {
        return usuarioRepository.findById(id).get();
    }

    public List<Usuario> listarUsuario(String nome){
        return usuarioRepository.findAll();
    }


}
