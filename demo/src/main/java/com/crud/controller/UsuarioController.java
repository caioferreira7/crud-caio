package com.crud.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.crud.model.Usuario;
import com.crud.service.UsuarioService;

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