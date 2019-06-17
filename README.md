# Desafio Beblue - Engenheiro Back-end

Desafio para ingresso como desenvolvedor back-end no Beblue: API REST para um loja de discos de vinil que resolveu oferecer cashback em suas vendas.

### Tecnologias
- [Maven](https://maven.apache.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [H2 Database Engine](https://www.h2database.com/) (banco de dados)
- [Lombok](https://projectlombok.org/)
- [JUnit](https://junit.org/junit5/)
- [AssertJ](https://joel-costigliola.github.io/assertj/)
- [Mockito](https://site.mockito.org/)

## Instruções de execução

### Clone
```https://github.com/henriqueneves/api-discos-beblue.git```


## Documentação

### Discos
* Importação: realizada automaticamente.

* Inserir disco: persiste um novo disco.
  * Content-Type: application/json
  * POST: [http://localhost:8080/discs](http://localhost:8080/discs)
  * Exemplo BODY - RAW:
  ```
  {
    "name": "Disco personalizado",
    "genre": "ROCK",
    "price": 42.42
  }
  ```
  
* Editar disco: edita um disco existente.
  * Content-Type: application/json
  * PUT: [http://localhost:8080/discs](http://localhost:8080/discs)
  * Exemplo BODY - RAW:
  ```
  {
    "id": 201,
    "name": "Disco personalizado",
    "genre": "ROCK",
    "price": 42.42
  }
  ```
* Deletar disco: remove um disco existente.
  * Content-Type: application/json
  * DELETE: [http://localhost:8080/discs/**10**](http://localhost:8080/discs/10)
  * Exemplo BODY - RAW: null
  
* Buscar disco por identificador
   * Content-Type: application/json
   * GET: [http://localhost:8080/api/discs/**5**](http://localhost:8080/discs/5)


* Busca paginada: retorna N discos de determinado gênero utilizando filtros
  * Content-Type: application/json
  * Parâmetros opcionais: page, size (max = 1000)
  * GET: [http://localhost:8080/discs/search/**ROCK**?page=**1**&size=**10**](http://localhost:8080/discs/search/ROCK?page=1&size=10)

## Observações:

* Para testar a API recomenda-se o uso de um API Client.
* Para facilitar os testes, as tabelas do banco de dados são recriadas a cada execução.
* A API não exige autenticação para acesso as suas funcionalidades.
