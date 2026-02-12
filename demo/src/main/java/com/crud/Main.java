package com.crud;

import com.crud.model.Usuario;
import com.crud.repository.H2Conexao;
import com.crud.repository.IConexao;
import com.crud.repository.UsuarioRepository;
import com.crud.service.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        IConexao conexao = new H2Conexao();
        UsuarioRepository repository = new UsuarioRepository(conexao);
        UsuarioService service = new UsuarioService(repository);

        Scanner scanner = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Criar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Atualizar usuário");
            System.out.println("4 - Deletar usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            try {
                switch (opcao) {
                    case "1": 
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        service.criarUsuario(nome, email);
                        System.out.println("Usuário criado com sucesso!");
                        break;

                    case "2": 
                        List<Usuario> usuarios = service.listarUsuarios();
                        System.out.println("Lista de usuários:");
                        for (Usuario u : usuarios) {
                            System.out.println(u);
                        }
                        break;

                    case "3": 
                        System.out.print("ID do usuário a atualizar: ");
                        int idAtualizar = Integer.parseInt(scanner.nextLine());
                        System.out.print("Novo nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Novo email: ");
                        String novoEmail = scanner.nextLine();
                        service.atualizarUsuario(idAtualizar, novoNome, novoEmail);
                        System.out.println("Usuário atualizado com sucesso!");
                        break;

                    case "4": 
                        System.out.print("ID do usuário a deletar: ");
                        int idDeletar = Integer.parseInt(scanner.nextLine());
                        service.deletarUsuario(idDeletar);
                        System.out.println("Usuário deletado com sucesso!");
                        break;

                    case "0":
                        rodando = false;
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
