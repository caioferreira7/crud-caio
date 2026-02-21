package com.crud.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.crud.model.Usuario;
import com.crud.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario criarUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isBlank())
            throw new IllegalArgumentException("Nome obrigatório.");

        if (usuario.getEmail() == null || usuario.getEmail().isBlank())
            throw new IllegalArgumentException("Email obrigatório.");

        return repository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    public Usuario atualizarUsuario(Integer id, Usuario usuarioAtualizado) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());

        return repository.save(usuario);
    }

    public void deletarUsuario(Integer id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Usuário não encontrado.");

        repository.deleteById(id);
    }
}