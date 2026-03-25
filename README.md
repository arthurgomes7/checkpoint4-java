# 🛒 Sistema de Gerenciamento de Produtos (Java + JDBC + Oracle)

Projeto para demonstrar CRUD básico e controle de **estoque** utilizando **Java**, **JDBC** e **Oracle Database** com camadas **DAO** e **Service**.
A tabela `produtos` é verificada/criada automaticamente ao inicializar o DAO.

---

## ✨ Funcionalidades

- Criar tabela `produtos` automaticamente (se não existir).
- Cadastrar produto (`nome`, `preco`, `categoria`, `quantidade`).
- Buscar produto por **ID**.
- Adicionar estoque (incrementa `quantidade`).
- Remover estoque (decrementa `quantidade` com validação no service).
- Registro do **ID gerado** pelo banco no objeto `Produto`.

---

## 🧱 Modelo de Dados

**Tabela:** `produtos`

| Coluna     | Tipo            | Restrições            | Descrição                    |
|------------|------------------|-----------------------|------------------------------|
| `id`       | NUMBER (IDENTITY)| PK                    | Identificador do produto     |
| `nome`     | VARCHAR2(100)    | NOT NULL              | Nome do produto              |
| `preco`    | NUMBER(10,2)     | NOT NULL              | Preço                        |
| `quantidade` | NUMBER         | NOT NULL              | Estoque disponível           |
| `categoria`| VARCHAR2(100)    | —                     | Categoria do produto         |

> **Observação:** as operações de estoque atuam **na coluna `quantidade`**.

---

## 🗂 Estrutura do Projeto
```src/
├─ dao/
│  ├─ ProdutoDAO.java
│  └─ ProdutoDaoImpl.java
├─ exceptions/
│  └─ EstoqueException.java
├─ model/
│  └─ Produto.java
├─ service/
│  └─ ProdutoService.java
├─ util/
│  └─ ConexaoDB.java
└─ TesteProduto.java
```
---

## ⚙️ Pré‑requisitos

- **Java 11+** (recomendado 17+).
- **Oracle JDBC driver** (`ojdbc11.jar` para JDK 11+ ou `ojdbc8.jar` para JDK 8).
- Acesso ao Oracle FIAP (ou outro Oracle) com credenciais válidas.

---
Integrantes do Grupo:
 - Arthur Gomes
 - Pedro Estevam
 - Samuel Becker
