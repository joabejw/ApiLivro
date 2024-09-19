package com.example.apiLivro.services;

import com.example.apiLivro.apiDTO.LivroRequestDTO;
import com.example.apiLivro.domain.livro.Livro;
import com.example.apiLivro.exceptions.DomainException;
import com.example.apiLivro.exceptions.ServiceException;
import com.example.apiLivro.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public ResponseEntity<Livro> salvarLivro(LivroRequestDTO data){
        if(livroRepository.findByIsbn(data.isbn()).isEmpty()){
            throw new DomainException("Livro já cadastrado");
        }
        try {
            Livro novoLivro = new Livro();
            novoLivro.setAutor(data.autor());
            novoLivro.setIsbn(data.isbn());
            novoLivro.setCategoria(data.categoria());
            novoLivro.setData_publicacao(data.data_publicacao());
            novoLivro.setTitulo(data.titulo());
            livroRepository.save(novoLivro);
            return ResponseEntity.ok(novoLivro);
        } catch (Exception e){
            throw new ServiceException("Erro ao salvar um novo livro");
        }

    }

    public void deletarLivro(UUID id){
        try {
            Optional<Livro> livroEntity = this.livroRepository.findById(id);
            if (livroEntity.isEmpty()){
                throw new DomainException("Livro não encontrado");
            }
            livroRepository.deleteById(livroEntity.get().getId());
        } catch (Exception e){
            throw new ServiceException("Erro ao deletar livro");
        }
    }

    public Livro alterarLivro(UUID id, LivroRequestDTO livroRequestDTO){
        try {
            Optional<Livro> livroEntity = this.livroRepository.findById(id);

            if (livroEntity.isEmpty()){
                throw new DomainException("Livro não encontrado");
            }

            if(livroRequestDTO.titulo() != null){
                livroEntity.get().setTitulo(livroRequestDTO.titulo());
            }
            if(livroRequestDTO.autor() != null){
                livroEntity.get().setAutor(livroRequestDTO.autor());
            }
            if(livroRequestDTO.data_publicacao() != null){
                livroEntity.get().setData_publicacao(livroRequestDTO.data_publicacao());
            }

            if(livroRequestDTO.categoria() != null){
                livroEntity.get().setCategoria(livroRequestDTO.categoria());
            }
            livroRepository.save(livroEntity.get());
            return livroEntity.get();
        }catch (Exception e){
            throw new ServiceException("Erro ao alterar livro");
        }
    }
    public Livro buscarLivro(UUID id){
        try {
            Optional<Livro> livroEntity = this.livroRepository.findById(id);
            if (livroEntity.isEmpty()){
                throw new DomainException("Livro não encontrado");
            }
            return livroEntity.get();

        } catch (Exception e){
            throw new ServiceException("Erro ao buscar livro");
        }
    }

    public List<Livro> buscarTodosLivros(){
        try {
            List<Livro> livros = livroRepository.findAll();
            return livros;
        }catch (Exception e){
            throw new ServiceException("Erro ao buscar todos os livros");
        }
    }
}
