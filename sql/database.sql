-- Tabela de usuários do cleitin mercados
--d
CREATE TABLE usuarios (
    id_usuarios SERIAL PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR (50) NOT NULL CHECK(length(senha) >= 8),
    tipo_usuario INT CHECK(usuarios IN(1,2))
);
