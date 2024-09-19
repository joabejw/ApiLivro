package com.example.apiLivro.controller;

import com.example.apiLivro.apiDTO.AlterarUsuarioDTO;
import com.example.apiLivro.domain.livro.Livro;
import com.example.apiLivro.domain.usuario.Usuario;
import com.example.apiLivro.apiDTO.UsuarioRequestDTO;
import com.example.apiLivro.repositories.UsuarioRepository;
import com.example.apiLivro.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> buscarTodosUsuarios(){
        List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
        return usuarios;
    }
    @PostMapping("/cadastrar")
    public ResponseEntity novoUsuario(@RequestBody UsuarioRequestDTO body){

        Usuario usuario = this.usuarioService.salvarUsuario(body);
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<Usuario> buscarUsuarioEmail(@PathVariable String email){
        Optional<Usuario> usuario = usuarioService.buscarUsuario(email);
        return ResponseEntity.ok(usuario.get());

    }

    @DeleteMapping(value = "/deletarUsuario/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("email") UUID id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/alterarUsuario/{email}")
    public ResponseEntity<Void> alterarUsuario(@PathVariable("email") String email,
                                               @RequestBody AlterarUsuarioDTO alterarUsuarioDTO){

        usuarioService.alterarUsuario(email, alterarUsuarioDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/livrosRecomendados/{id}")
    public List<Livro> buscarLivrosRecomendados(@PathVariable("id") UUID id){
        List<Livro> livros = usuarioService.recomendarLivros(id);
        return livros;
    }

}
