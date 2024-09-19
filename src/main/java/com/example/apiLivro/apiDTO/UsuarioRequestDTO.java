package com.example.apiLivro.apiDTO;

import java.util.Date;

public record UsuarioRequestDTO (String nome, String email, Date data_cadastro, String telefone){
}
