package com.example.apiLivro.services;

import com.example.apiLivro.apiDTO.UsuarioRequestDTO;
import com.example.apiLivro.domain.usuario.Usuario;
import com.example.apiLivro.repositories.EmprestimoRepository;
import com.example.apiLivro.repositories.LivroRepository;
import com.example.apiLivro.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;;

@ActiveProfiles("teste")
class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private LivroRepository livroRepository;
    @Mock
    private EmprestimoRepository emprestimoRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Captor
    private ArgumentCaptor<Usuario> usuarioArgumentCaptor;
    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;
    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    @DisplayName("Cadastrar novo usuario com sucesso")
    void cadastrarNovoUsuario() {

        Usuario novousuUsuario = new Usuario(UUID.randomUUID(),"joabe","joabe.teste@hotmail.com", new Date(), "449991414",null);
        doReturn(novousuUsuario).when(usuarioRepository).save(usuarioArgumentCaptor.capture());
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO("joabe","joabe.teste@hotmail.com", new Date(), "449991414");

        var retorno = usuarioService.salvarUsuario(usuarioRequestDTO);

        assertNotNull(retorno);
        var usuarioCap = usuarioArgumentCaptor.getValue();
        assertEquals(usuarioRequestDTO.nome(), usuarioCap.getNome());
        assertEquals(usuarioRequestDTO.email(), usuarioCap.getEmail());
        assertEquals(usuarioRequestDTO.telefone(), usuarioCap.getTelefone());

    }

    @Test
    @DisplayName("Buscar usuario por email com sucesso")
    void buscarUsuarioSucesso() {

        var usuario = new Usuario(UUID.randomUUID(),"joabe","joabe.teste@hotmail.com", new Date(), "449991414",null);
        doReturn(Optional.of(usuario)).when(usuarioRepository).findByEmail(stringArgumentCaptor.capture());

       Optional<Usuario> retorno = usuarioService.buscarUsuario(usuario.getEmail().toString());

       assertTrue(retorno.isPresent());

       assertEquals(usuario.getEmail(), stringArgumentCaptor.getValue());

    }
}