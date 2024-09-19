CREATE TABLE Livros (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    data_publicacao DATE NOT NULL,
    categoria VARCHAR(100) NOT NULL
);