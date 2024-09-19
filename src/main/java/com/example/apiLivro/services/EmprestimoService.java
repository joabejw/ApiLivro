package com.example.apiLivro.services;

import com.example.apiLivro.apiDTO.EmprestimoDTO;
import com.example.apiLivro.domain.empretimo.Emprestimo;
import com.example.apiLivro.domain.livro.Livro;
import com.example.apiLivro.domain.usuario.Usuario;
import com.example.apiLivro.exceptions.DomainException;
import com.example.apiLivro.exceptions.ServiceException;
import com.example.apiLivro.repositories.EmprestimoRepository;
import com.example.apiLivro.repositories.LivroRepository;
import com.example.apiLivro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LivroRepository livroRepository;

    public ResponseEntity<Emprestimo> novoEmprestimo(EmprestimoDTO emprestimoDTO){
        try{
            Emprestimo novoEmprestimo = new Emprestimo();

            Optional<Usuario> usuario = this.usuarioRepository.findById(emprestimoDTO.idUsuario());
            Optional<Livro> livro = this.livroRepository.findById(emprestimoDTO.idLivro());
            if(usuario.isEmpty()){
                throw new DomainException("Usuario não encontrado");
            }
            if(livro.isEmpty()){
                throw new DomainException("Livro não encontrado");
            }
            novoEmprestimo.setData_emprestimo(emprestimoDTO.data_emprestimo());
            novoEmprestimo.setData_devolucao(emprestimoDTO.data_devolucao());
            novoEmprestimo.setLivro(livro.get());
            novoEmprestimo.setUsuario(usuario.get());
            novoEmprestimo.setStatus(emprestimoDTO.status());

            emprestimoRepository.save(novoEmprestimo);
            return ResponseEntity.ok(novoEmprestimo);

        }catch (Exception e){
            throw new ServiceException("Erro ao salvar emprestimo");
        }
    }
    public ResponseEntity<Object> alterarEmprestimo(UUID id, EmprestimoDTO atualizaEmprestimoDTO){
        try {
            Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
            if(emprestimo.isEmpty()){
                throw new DomainException("Emprestimo não encontrado");
            }
            if (emprestimo.get().getStatus() != "ativo"){
                throw new DomainException("Emprestimo Inativo");
            }
            if(atualizaEmprestimoDTO.data_devolucao() != null){
                emprestimo.get().setData_devolucao(atualizaEmprestimoDTO.data_devolucao());
            }
            if(atualizaEmprestimoDTO.status() != null){
                emprestimo.get().setStatus(atualizaEmprestimoDTO.status());
            }
            emprestimoRepository.save(emprestimo.get());
            return ResponseEntity.ok(emprestimo.get());

        }catch (Exception e){
            throw new ServiceException("Erro ao alterar emprestimo");
        }
    }
    public List<Emprestimo>buscarTodosEmprestimos(){
        try {
            List<Emprestimo> emprestimos = emprestimoRepository.findAll();
            return emprestimos;
        } catch (Exception e){
            throw new ServiceException("Erro ao buscar todos os emprestimos");
        }
    }
    public List<Emprestimo>buscarEmprestimosUsuario(UUID id){
        try {
            List<Emprestimo> emprestimos = emprestimoRepository.findAll();
            return emprestimos;
        } catch (Exception e){
            throw new ServiceException("Erro ao buscar todos os emprestimos");
        }
    }

}
