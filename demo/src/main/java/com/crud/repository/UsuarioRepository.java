package com.crud.repository;

import com.crud.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements IUsuarioRepository {

    private final IConexao conexao; // ← variável de instância (POO)

    public UsuarioRepository(IConexao conexao) { // ← injeta dependência
        this.conexao = conexao;
        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS usuario (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY," +
                     "nome VARCHAR(100)," +
                     "email VARCHAR(100) UNIQUE)";
        try (Connection conn = conexao.getConnection(); // ← usa a interface
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela", e);
        }
    }

    @Override
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email) VALUES (?, ?)";
        try (Connection conn = conexao.getConnection(); // ← usa a interface
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) usuario.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuário", e);
        }
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection conn = conexao.getConnection(); // ← usa a interface
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários", e);
        }
        return usuarios;
    }

    @Override
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome=?, email=? WHERE id=?";
        try (Connection conn = conexao.getConnection(); // ← usa a interface
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setInt(3, usuario.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário", e);
        }
    }

    @Override
    public void remover(int id) {
        String sql = "DELETE FROM usuario WHERE id=?";
        try (Connection conn = conexao.getConnection(); // ← usa a interface
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover usuário", e);
        }
    }

    @Override
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id=?";
        try (Connection conn = conexao.getConnection(); // ← usa a interface
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("email")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
        return null;
    }
}
