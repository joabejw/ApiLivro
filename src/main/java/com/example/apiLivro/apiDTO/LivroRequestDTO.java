package com.example.apiLivro.apiDTO;

import java.util.Date;

public record LivroRequestDTO(String titulo, String autor, String isbn, Date data_publicacao, String categoria){
}
