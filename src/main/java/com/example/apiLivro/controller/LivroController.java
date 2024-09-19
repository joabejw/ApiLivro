package com.example.apiLivro.controller;

import com.example.apiLivro.apiDTO.LivroRequestDTO;
import com.example.apiLivro.domain.livro.Livro;
import com.example.apiLivro.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/livro")
public class LivroController {
    @Autowired
    private LivroService livroService;
    @PostMapping("/cadastrarLivro")
    public ResponseEntity cadastrarLivro(@RequestBody LivroRequestDTO livroRequestDTO){
        ResponseEntity<Livro> livro = this.livroService.salvarLivro(livroRequestDTO);
        return ResponseEntity.ok().body(livro);
    }

    @GetMapping
    public List<Livro> buscarTodosLivros() {
        return livroService.buscarTodosLivros();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Livro> buscarLivro(@PathVariable("id") UUID id){
        Livro livro = livroService.buscarLivro(id);
        return ResponseEntity.ok().body(livro);
    }
    @PutMapping("/alterarLivro/{id}")
    public Livro alterarLivro(@PathVariable("id") UUID id, @RequestBody LivroRequestDTO AlterarLivroDTO){
        Livro livroResposta = livroService.alterarLivro(id, AlterarLivroDTO);

        return livroResposta;
    }

    @DeleteMapping("/deletarLivro/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable("id") UUID id){
        livroService.deletarLivro(id);
        return ResponseEntity.accepted().build();
    }

}
