#<center>Desafio EKAM</center>

<center>Este projeto é uma API RESTful para gerenciar beneficiários de um plano de saúde, desenvolvida com Spring Boot, Spring Data JPA, Spring Security, e documentada com Springdoc OpenAPI.</center>

## Tecnologias Utilizadas

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Spring Boot 2.7.4](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- [H2 Database](https://www.h2database.com/html/main.html)
- [Lombok](https://projectlombok.org/)
- [Springdoc OpenAPI](https://springdoc.org/)
- [Maven](https://maven.apache.org/)

## Pré-requisitos

- Java 17
- Maven 3.6.3 ou superior

## Instalação

### 1. Clone o repositório

```sh
git clone https://github.com/jader75/desafio-ekam.git
cd desafio-ekam
```

### 2. Compile o projeto usando Maven:

```sh
mvn clean install
```

## Executando a Aplicação

Para executar a aplicação, use o comando abaixo:

```sh
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## Configuração do Spring Security e Configuração de Usuários

A aplicação utiliza autenticação básica (Basic Authentication) para proteger as rotas da API. <br/>
Os usuários estão configurados em memória para fins de teste. <br/>
As configurações de segurança estão definidas na classe `SecurityConfig`.
Você pode modificar a configuração conforme necessário.


```
Usuário: admin
Senha: password
```

### Configuração de Usuários


### Acessando o Swagger UI

O Swagger UI está disponível em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html). Para acessar e testar os endpoints protegidos, utilize autenticação básica com as credenciais configuradas.

## Endpoints da API

### Beneficiários

- `POST /beneficiarios` - Cadastrar um novo beneficiário
- `GET /beneficiarios` - Listar todos os beneficiários
- `GET /beneficiarios/{id}` - Buscar beneficiário por ID e lista os documentos
- `PUT /beneficiarios/{id}` - Atualizar dados de um beneficiário
- `DELETE /beneficiarios/{id}` - Remover um beneficiário

## Testando a Aplicação

### Utilizando Postman

1. Abra o Postman e configure a autenticação básica com as credenciais definidas no `SecurityConfig`.
2. Faça requisições para os endpoints da API utilizando o Postman.

### Exemplos de Requisições

#### POST /beneficiarios

```json
{
  "nome": "João da Silva",
  "telefone": "5555-1234",
  "dataNascimento": "22/01/1990",
  "documentos": [
    {
      "tipoDocumento": "CPF",
      "descricao": "123.456.789-00"
    },
    {
      "tipoDocumento": "RG",
      "descricao": "SP87654321"
    },
    {
      "tipoDocumento": "CNH",
      "descricao": "SP76543210"
    }
  ]
}
```

#### GET /beneficiarios

Sem corpo de requisição.

#### GET /beneficiarios/{id}

Sem corpo de requisição.

#### PUT /beneficiarios/{id}

```json
{
  "nome": "João da Silva",
  "cpf": "123.456.789-00",
  "dataNascimento": "1990-01-01"
}
```

#### DELETE /beneficiarios/{id}

Sem corpo de requisição.

## Tratamento de Erros

A aplicação lança exceções customizadas para cenários específicos, como beneficiário não encontrado. Essas exceções são tratadas e retornam respostas apropriadas para o cliente.

## Contato

Para mais informações, entre em contato com [jader.java.contato@gmail.com](mailto:jader.java.contato@gmail.com).
