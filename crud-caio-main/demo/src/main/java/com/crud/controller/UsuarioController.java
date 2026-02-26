package com.crud.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.model.Usuario;
import com.crud.service.UsuarioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

     
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return service.criarUsuario(usuario);
    }

    
    @GetMapping
    public List<Usuario> listar() {
        return service.listarUsuarios();
    }

    
    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Integer id,
                              @RequestBody Usuario usuario) {
        return service.atualizarUsuario(id, usuario);
    }

    
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletarUsuario(id);
    }
}