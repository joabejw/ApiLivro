package com.example.apiLivro.apiDTO;

import com.example.apiLivro.domain.livro.Livro;
import com.example.apiLivro.domain.usuario.Usuario;

import java.util.Date;
import java.util.UUID;

public record EmprestimoDTO(Date data_emprestimo, Date data_devolucao, String status, UUID idUsuario, UUID idLivro) {
}
