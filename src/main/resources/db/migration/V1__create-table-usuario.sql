CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE Usuarios (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    data_cadastro DATE NOT NULL CHECK (data_cadastro <= CURRENT_DATE),
    telefone VARCHAR(15) NOT NULL
);