CREATE TABLE Emprestimos  (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    usuario_id UUID NOT NULL,
    livro_id UUID NOT NULL,
    data_emprestimo DATE NOT NULL CHECK (data_emprestimo <= CURRENT_DATE),
    data_devolucao DATE,
    status VARCHAR(20) NOT NULL,
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
        FOREIGN KEY (livro_id) REFERENCES livros(id) ON DELETE CASCADE
);