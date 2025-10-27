# Projeto Mercado

Sistema simples de **gestão de mercado**, desenvolvido para fins acadêmicos.  
O projeto integra **Java (Swing)** no front-end, **Java puro** no back-end e **PostgreSQL** como banco de dados.

---

## Estrutura de Pastas

Abaixo está a estrutura geral do projeto:

```bash
    mercado/
    │
    ├── src/
    │ ├── model/ # Representa os dados e entidades do sistema
    │ ├── dao/ # Acesso ao banco de dados e manipulação de dados
    │ └── view/ # Telas e interface gráfica (Swing)
    │
    ├── lib/ # Bibliotecas externas (ex: driver do PostgreSQL)
    │
    │── sql/ # Scripts SQL para criação e manipulação do banco
```

## Descrição das Pastas

### **Model**
Contém as **classes de entidades** que representam os dados do sistema.  
Exemplo:  
- `Produto.java`  
- `Cliente.java`  
- `Venda.java`  

Essas classes normalmente possuem:
- Atributos (campos)
- Construtores
- Métodos getters e setters
- Método `toString()`

---

### **Dao (Data Access Object)**
Responsável pela **comunicação com o banco de dados**.  
Aqui ficam as classes que:

- Estabelecem a **conexão com o PostgreSQL** (`Conexao.java`);
- Executam operações **CRUD** (Create, Read, Update, Delete);
- Manipulam os dados entre o Java e o banco.

---

### **View**
Contém todas as **interfaces gráficas (Swing)**.  
Aqui são desenvolvidas as telas do sistema, como:

- `TelaPrincipal.java`  
- `TelaCadastroProduto.java`  
- `TelaVendas.java`  

O front-end será implementado com **Swing**, permitindo interação direta com os dados.

---

### **Lib**
Diretório para **bibliotecas externas** e **drivers** necessários.  
Exemplo:  

- `postgresql-<versão>.jar` → Driver JDBC para conexão com o PostgreSQL.

---

### **Sql**
Armazena os **scripts SQL** utilizados pelo time de banco de dados.  
Inclui:

- `criar_tabelas.sql` → Criação das tabelas;  
- `inserir_dados_iniciais.sql` → Inserção de dados para testes.

Esses scripts devem ser executados antes de rodar o sistema.

---

## Como Executar o Projeto

1. Configure o **PostgreSQL** e execute os scripts da pasta `sql/`.
2. Importe o projeto no seu IDE (Eclipse, IntelliJ ou NetBeans).
3. Adicione o **driver JDBC do PostgreSQL** da pasta `lib/` ao classpath.
4. Execute a classe principal:  
   ```bash
   Main.java
