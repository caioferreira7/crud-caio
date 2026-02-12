package com.crud.repository;

import com.crud.model.Usuario;
import java.util.List;

public interface IUsuarioRepository {
    void salvar(Usuario usuario);
    List<Usuario> listarTodos();
    void atualizar(Usuario usuario);
    void remover(int id);
    Usuario buscarPorId(int id);
}