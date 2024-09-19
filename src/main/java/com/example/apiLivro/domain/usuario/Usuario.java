package com.example.apiLivro.domain.usuario;

import com.example.apiLivro.apiDTO.UsuarioRequestDTO;
import com.example.apiLivro.domain.empretimo.Emprestimo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table(name = "Usuarios")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;
    private Date data_cadastro;
    private String telefone;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Usuario(UsuarioRequestDTO usuarioRequestDTO){
        this.nome = usuarioRequestDTO.nome();
        this.email = usuarioRequestDTO.email();
        this.data_cadastro = usuarioRequestDTO.data_cadastro();
        this.telefone = usuarioRequestDTO.telefone();
    }
}
