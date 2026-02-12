package com.crud.service;

import com.crud.model.Usuario;
import com.crud.repository.IUsuarioRepository;
import com.crud.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {
    private final IUsuarioRepository repository;

    
    public UsuarioService(IUsuarioRepository repository) {
        this.repository = repository;
    }

    public void criarUsuario(String nome, String email) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome obrigatório.");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email obrigatório.");

        repository.salvar(new Usuario(nome, email));
    }

    public List<Usuario> listarUsuarios() {
        return repository.listarTodos();
    }

    public void atualizarUsuario(int id, String nome, String email) {
        Usuario u = repository.buscarPorId(id);
        if (u == null) throw new RuntimeException("Usuário não encontrado.");
        u.setNome(nome);
        u.setEmail(email);
        repository.atualizar(u);
    }

    public void deletarUsuario(int id) {
        Usuario u = repository.buscarPorId(id);
        if (u == null) throw new RuntimeException("Usuário não encontrado.");
        repository.remover(id);
    }
}
