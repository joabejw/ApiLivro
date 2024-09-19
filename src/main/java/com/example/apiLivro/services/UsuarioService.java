package com.example.apiLivro.services;

import com.example.apiLivro.apiDTO.AlterarUsuarioDTO;
import com.example.apiLivro.domain.empretimo.Emprestimo;
import com.example.apiLivro.domain.livro.Livro;
import com.example.apiLivro.domain.usuario.Usuario;
import com.example.apiLivro.apiDTO.UsuarioRequestDTO;
import com.example.apiLivro.exceptions.DomainException;
import com.example.apiLivro.exceptions.ServiceException;
import com.example.apiLivro.repositories.EmprestimoRepository;
import com.example.apiLivro.repositories.LivroRepository;
import com.example.apiLivro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public Usuario salvarUsuario(UsuarioRequestDTO data){
        try {
            Usuario novoUsuario = new Usuario();

            if (usuarioRepository.findByEmail(data.email()).isPresent()){
                throw new DomainException("Usuario já cadastrado");
            }

            novoUsuario.setNome(data.nome());
            novoUsuario.setEmail(data.email());
            novoUsuario.setTelefone(data.telefone());
            novoUsuario.setData_cadastro(data.data_cadastro());
            usuarioRepository.save(novoUsuario);
            return novoUsuario;

        } catch (Exception e){
            throw new ServiceException("Erro ao salvar um novo usuario");
        }
    }

    public void deletarUsuario(UUID id){
        try {
            Optional<Usuario> usuarioEntity = usuarioRepository.findById(id);
            if (usuarioEntity.isEmpty()){
                throw new DomainException("Usuario não encontrado");
            }
            usuarioRepository.deleteById(usuarioEntity.get().getId());
        } catch (Exception e){
            throw new ServiceException("Erro ao deletar usuario");
        }
    }

    public void alterarUsuario(String email, AlterarUsuarioDTO alterarUsuarioDTO){
        try {
            Optional<Usuario> usuarioEntity = usuarioRepository.findByEmail(email);

            if (usuarioEntity.isEmpty()){
                throw new DomainException("Usuario não encontrado");
            }

            if(alterarUsuarioDTO.nome() != null){
                usuarioEntity.get().setNome(alterarUsuarioDTO.nome());
            }
            if(alterarUsuarioDTO.telefone() != null){
                usuarioEntity.get().setTelefone(alterarUsuarioDTO.telefone());
            }
            usuarioRepository.save(usuarioEntity.get());
        }catch (Exception e){
            throw new ServiceException("Erro ao alterar usuario");
        }
    }
    public Optional<Usuario> buscarUsuario(String email){
        try {
            Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
            if (usuario.isEmpty()){
                throw new DomainException("Usuario não encontrado");
            }
            return usuario;

        } catch (Exception e){
            throw new ServiceException("Erro ao buscar usuario");
        }
    }

    public List<Usuario> buscarTodosUsuarios(){
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();
            return usuarios;

        }catch (Exception e){
            throw new ServiceException("Erro ao buscar todos os usuarios");
        }
    }

    public List<Livro> recomendarLivros(UUID id){
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            if (usuario.isEmpty()){
                throw new DomainException("Usuario não encontrado");
            }

            List<Emprestimo> emprestimos = emprestimoRepository.findByUsuario_id(usuario.get().getId());
            List<Livro> livrosEmprestados = new ArrayList<>();

            for (Emprestimo c : emprestimos){
                livrosEmprestados.add(c.getLivro());
            }

            Set<String> listaCategorias = livrosEmprestados.stream()
                    .map(Livro::getCategoria)
                    .collect(Collectors.toSet());

            List<Livro> livrosRecomendados = livroRepository.findAll().stream()
                    .filter(livro -> listaCategorias.contains(livro.getCategoria()))
                    .collect(Collectors.toList());

            return livrosRecomendados;

        } catch (Exception e){
            throw new ServiceException("Erro ao buscar livros recomendados");
        }
    }
}
