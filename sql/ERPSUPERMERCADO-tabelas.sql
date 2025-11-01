--Tabela Tbclientes
CREATE TABLE Tbclientes (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(15),
    data_nascimento DATE
);

--Tabela Tbusuario
CREATE TABLE Tbusuario (
    id_usuario SERIAL PRIMARY KEY,
    id_usuario_character VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    tipo_usuario VARCHAR(20) NOT NULL 
);

--Tabela Tbprodutos
CREATE TABLE Tbprodutos (
    id_produto SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    valor_unit NUMERIC(10, 2) NOT NULL,
    quantidade INTEGER NOT NULL,
    categoria VARCHAR(50),
    codigo VARCHAR(20) UNIQUE 
);

--Tabela Tbvendas
CREATE TABLE Tbvendas (
    id_venda SERIAL PRIMARY KEY,
    id_cliente INTEGER NOT NULL,
    id_usuario INTEGER NOT NULL,
    data_venda DATE NOT NULL DEFAULT CURRENT_DATE,
    valor_total NUMERIC(10, 2) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES Tbclientes(id_cliente),
    FOREIGN KEY (id_usuario) REFERENCES Tbusuario(id_usuario)
);

--Tabela item_venda
CREATE TABLE item_venda (
    id_item SERIAL PRIMARY KEY,
    id_venda INTEGER NOT NULL,
    id_produto INTEGER NOT NULL,
    quantidade INTEGER NOT NULL,
    valor_unit NUMERIC(10, 2) NOT NULL, 

    -- Chave composta para garantir que um produto não seja duplicado na mesma venda
    UNIQUE (id_venda, id_produto),
    FOREIGN KEY (id_venda) REFERENCES Tbvendas(id_venda),
    FOREIGN KEY (id_produto) REFERENCES Tbprodutos(id_produto)
);