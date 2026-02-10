package com.crud;


import com.crud.model.Usuario;
import com.crud.service.UsuarioService;

public class Main {
    public static void main(String[] args) {
        UsuarioService service = new UsuarioService();

        
        service.criarUsuario("Caio", "caio@email.com");
        service.criarUsuario("Ana", "ana@email.com");

      
        System.out.println("Lista de usuários:");
        for (Usuario u : service.listarUsuarios()) {
            System.out.println(u);
        }
    }
}
