package com.example.apiLivro.controller;

import com.example.apiLivro.apiDTO.EmprestimoDTO;
import com.example.apiLivro.domain.empretimo.Emprestimo;
import com.example.apiLivro.repositories.EmprestimoRepository;
import com.example.apiLivro.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {
    @Autowired
    private EmprestimoService emprestimoService;


    @PostMapping("/cadastrarEmprestimo")
    public ResponseEntity cadastrarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO){
        emprestimoService.novoEmprestimo(emprestimoDTO);
        return ResponseEntity.ok().body(emprestimoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity alterarEmprestimo(@PathVariable("id") UUID id, @RequestBody EmprestimoDTO atualizaEmprestimoDTO){
        ResponseEntity<Object> emprestimoResposta = emprestimoService.alterarEmprestimo(id, atualizaEmprestimoDTO);

        return ResponseEntity.ok().body(emprestimoResposta);
    }

    @GetMapping
    List<Emprestimo> buscarTodosEmprestimos(){
        List<Emprestimo> emprestimos = emprestimoService.buscarTodosEmprestimos();
        return emprestimos;
    }

}
