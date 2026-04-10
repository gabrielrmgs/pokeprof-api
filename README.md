# pokeprof-api

Este projeto é uma API desenvolvida para a disciplina de Sistemas Multimídias, sob orientação do professor Dario Calçada, na UESPI-PHB. A aplicação utiliza gamificação baseada no universo Pokémon para gerenciar o progresso acadêmico de alunos.

## Descrição

A API permite que professores lancem notas para atividades específicas. À medida que os alunos acumulam pontos, seus respectivos Pokémons evoluem automaticamente, seguindo regras de negócio baseadas em pontuações pré-definidas.

## Tecnologias

*   Java 21
*   Quarkus Framework (3.x)
*   Hibernate Panache (ORM)
*   PostgreSQL (Banco de dados)
*   Jackson (Serialização JSON)

## Requisitos

*   JDK 21 ou superior
*   Maven 3.9+
*   Instância do PostgreSQL ativa

## Configuração

1. Clone o repositório
2. Configure as credenciais do banco de dados no arquivo `src/main/resources/application.properties`:

```properties
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/nome_do_banco
quarkus.datasource.username=seu_usuario
quarkus.datasource.password=sua_senha
```

3. Execute a aplicação em modo de desenvolvimento:

```bash
./mvnw quarkus:dev
```

## Endpoints da API

### Alunos (`/alunos`)
*   `POST /alunos`: Cadastra um novo aluno e seu Pokémon inicial.
*   `GET /alunos`: Lista todos os alunos ativos no sistema.
*   `GET /alunos/{id}/pontos`: Retorna o ID e a soma total de pontos de um aluno específico.
*   `GET /alunos/ranking`: Retorna o ranking de alunos (suporta filtros por `pokemonNome` e `atividadeId`).
*   `GET /alunos/{id}/notas`: Retorna o histórico completo de notas de um aluno.

### Atividades (`/atividades`)
*   `POST /atividades`: Registra uma nova atividade (ex: Prova, Trabalho).
*   `GET /atividades`: Lista todas as atividades cadastradas no banco de dados.

### Notas (`/notas`)
*   `POST /notas`: Lança uma nota para um aluno em uma atividade específica. Este endpoint aciona a lógica de evolução e retorna um objeto detalhando o estado atual do aluno e se houve mudança de estágio do Pokémon.

## Estrutura do Projeto

*   `model`: Entidades JPA que representam as tabelas do banco de dados.
*   `dto`: Objetos de transferência de dados para requisições e respostas limpas.
*   `resource`: Endpoints REST da aplicação.
*   `service`: Lógica de negócio, incluindo o motor de evolução.

## Créditos

Desenvolvido como atividade prática para a disciplina de Sistemas Multimídias.<br>
Instituição: Universidade Estadual do Piauí (UESPI).<br>
Campus: Parnaíba (PHB).<br>
Professor: Dario Calçada.
