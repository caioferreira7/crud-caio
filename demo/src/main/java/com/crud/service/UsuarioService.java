package com.crud.service;

import com.crud.model.Usuario;
import com.crud.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {
    private final UsuarioRepository repository = new UsuarioRepository();

    public void criarUsuario(String nome, String email) {
        Usuario usuario = new Usuario(nome, email);
        repository.salvar(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return repository.listarTodos();
    }

    public void atualizarUsuario(Usuario usuario) {
        repository.atualizar(usuario);
    }

    public void deletarUsuario(int id) {
        repository.remover(id);
    }
}
