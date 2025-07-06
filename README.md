# 📚 Sistema de Gerenciamento de Biblioteca Acadêmica

## 🧾 Descrição Geral

Este projeto foi desenvolvido como trabalho prático da disciplina **Engenharia de Software I (MATA62)**. O objetivo é aplicar conceitos de **orientação a objetos** e **padrões de projeto** no desenvolvimento de um sistema simples de gerenciamento de biblioteca universitária.

## 🎯 Objetivo

Permitir que alunos e professores realizem **empréstimos**, **devoluções**, **reservas**, **consultas** e **observações** de livros via linha de comando. O sistema deve ser extensível e modular, atendendo a requisitos de boas práticas de design de software.

---

## 🧩 Funcionalidades

- **Empréstimo de livros** (`emp <idUsuario> <idLivro>`)
- **Devolução de livros** (`dev <idUsuario> <idLivro>`)
- **Reserva de livros** (`res <idUsuario> <idLivro>`)
- **Observação de reservas** por professores (`obs <idProfessor> <idLivro>`)
- **Consulta de livro** (`liv <idLivro>`)
- **Consulta de usuário** (`usu <idUsuario>`)
- **Consulta de notificações recebidas** (`ntf <idProfessor>`)
- **Encerramento do sistema** (`sai`)

---

## 👤 Tipos de Usuários

| Tipo                  | Código Exemplo | Limite de Empréstimos | Tempo de Empréstimo |
|-----------------------|----------------|------------------------|---------------------|
| Aluno de Graduação    | 123            | 2 livros               | 4 dias              |
| Aluno de Pós-Graduação| 456            | 3 livros               | 5 dias              |
| Professor             | 100            | Ilimitado              | 8 dias              |

---

## 📘 Livros e Exemplares

Cada **livro** possui:
- Código
- Título
- Editora
- Autores
- Edição
- Ano de publicação

Cada livro pode ter **vários exemplares**, identificados por código e status (`Disponível` ou `Emprestado`).

---

## ⚙️ Regras de Negócio

### Empréstimo
- É permitido apenas se houver exemplar disponível.
- Alunos não podem ter livros em atraso ou exceder o limite.
- Reservas interferem no empréstimo para alunos.
- Professores ignoram reservas e não possuem limite de livros.

### Reserva
- Só garante prioridade entre alunos.
- Registrada com a data de solicitação.

### Observação
- Professores são notificados ao serem registradas mais de 2 reservas simultâneas em um livro observado.

---

## 🧱 Arquitetura e Design

- **Padrão Singleton**: classe `Repositorio` (armazenamento em memória).
- **Padrão Command**: encapsulamento de comandos digitados no console.
- Sem uso de `if` ou `switch` para verificar tipos de usuário.
- Sem banco de dados ou interface gráfica (linha de comando apenas).

---

## 🧪 Dados de Teste

### Usuários

| Código | Tipo                 | Nome                     |
|--------|----------------------|--------------------------|
| 123    | Aluno Graduação      | João da Silva            |
| 456    | Aluno Pós-Graduação  | Luiz Fernando Rodrigues  |
| 789    | Aluno Graduação      | Pedro Paulo              |
| 100    | Professor            | Carlos Lucena            |

### Livros (exemplo)

| Código | Título                                               | Autores                            |
|--------|-------------------------------------------------------|-------------------------------------|
| 100    | Engenharia de Software                                | Ian Sommerville                     |
| 101    | UML - Guia do Usuário                                 | Booch, Rumbaugh, Jacobson           |
| 300    | Refactoring                                           | Martin Fowler                       |
| 400    | Design Patterns                                       | Gamma, Helm, Johnson, Vlissides     |

### Exemplares

| Código do Livro | Código Exemplar | Status     |
|-----------------|------------------|------------|
| 100             | 01               | Disponível |
| 100             | 02               | Disponível |
| 300             | 06               | Disponível |
| 400             | 08               | Disponível |

---

## 🧪 Como Executar

1. Compile o programa.
2. Os dados de teste são carregados automaticamente na inicialização.
3. Utilize os comandos disponíveis no terminal conforme especificado.
4. Digite `sai` para encerrar o sistema.

---

## 🧑‍💻 Tecnologias Utilizadas

- Linguagem: **Java** 
- Paradigma: **Programação Orientada a Objetos**
- Design Patterns: `Singleton`, `Command`, `Polimorfismo`, `Observer (implícito nas notificações)`

---

## 🧾 Autores

- Mikelly Correia
- Ruan Cardoso

---

## 🧑‍🏫 Professor Responsável

**Claudio Nogueira Sant Anna** – MATA62 – Universidade Federal da Bahia (UFBA)

---

