BEGIN;

-- Criação da tabela de clientes
CREATE TABLE IF NOT EXISTS public."Tbclientes"
(
    id_cliente serial NOT NULL,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    cpf character varying(14) COLLATE pg_catalog."default" NOT NULL,
    telefone character varying(15) COLLATE pg_catalog."default",
    data_nascimento date NOT NULL,
    CONSTRAINT "Tbclientes_pkey" PRIMARY KEY (id_cliente),
    CONSTRAINT un_cliente UNIQUE (cpf)
);

-- Criação da tabela de produtos
CREATE TABLE IF NOT EXISTS public."Tbprodutos"
(
    id_produto serial NOT NULL,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    valor_unit numeric(10, 2) NOT NULL,
    quantidade integer NOT NULL,
    categoria character varying(50) COLLATE pg_catalog."default" NOT NULL,
    codigo character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Tbprodutos_pkey" PRIMARY KEY (id_produto),
    CONSTRAINT un_produtos UNIQUE (codigo)
);

-- Criação da tabela de usuários
CREATE TABLE IF NOT EXISTS public."Tbusuario"
(
    id_usuario serial NOT NULL,
    usuario character varying(100) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(255) COLLATE pg_catalog."default" NOT NULL,
    tipo_usuario numeric(1) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Tbusuario_pkey" PRIMARY KEY (id_usuario)
);

-- Criação da tabela de vendas
CREATE TABLE IF NOT EXISTS public."Tbvendas"
(
    id_venda serial NOT NULL,
    id_usuario integer NOT NULL,
    id_cliente integer NOT NULL,
    data_venda date NOT NULL,
    valor_total numeric(10, 2) NOT NULL,
    CONSTRAINT "Tbvendas_pkey" PRIMARY KEY (id_venda)
);

-- Criação da tabela de itens de venda
CREATE TABLE IF NOT EXISTS public.item_venda
(
    id_item serial NOT NULL,
    id_venda integer NOT NULL,
    id_produto integer NOT NULL,
    quantidade integer NOT NULL,
    valor_unit numeric(10, 2) NOT NULL,
    CONSTRAINT item_venda_pkey PRIMARY KEY (id_item)
);

-- Adicionando as chaves estrangeiras
ALTER TABLE IF EXISTS public."Tbvendas"
    ADD CONSTRAINT fk_cliente FOREIGN KEY (id_cliente)
    REFERENCES public."Tbclientes" (id_cliente) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public."Tbvendas"
    ADD CONSTRAINT fk_usuario FOREIGN KEY (id_usuario)
    REFERENCES public."Tbusuario" (id_usuario) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.item_venda
    ADD CONSTRAINT fk_produto FOREIGN KEY (id_produto)
    REFERENCES public."Tbprodutos" (id_produto) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS public.item_venda
    ADD CONSTRAINT fk_venda FOREIGN KEY (id_venda)
    REFERENCES public."Tbvendas" (id_venda) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

COMMIT;
