
# Folha de Pagamento | API RESTful com Spring + PostgreSQL 🎲🌿

Nesse projeto de estudo eu construo um projeto simples para representar uma API de Folha de Pagamento 💸.

Ele é construído em Java 21 seguindo os padrões REST, e utilizando ferramentas do ecossistema Spring como Spring Boot e Spring HATEOAS. No banco de dados, utilzo o PostgreSQL 17.

Utilizei a documentação e tutorial de projeto [disponível em aqui nesse link](spring.io/guides/tutorials/rest) 🔗.

## Atualizações 🔁

+ 10/02/2025 - Criação do repositório. Criação da estrutura inicial que expõe a Entidade `Empregado.class`
+ 11/02/2025 - Atualização dos métodos de `EmpregadoController` para `public` para acessar fora do pacote. Criação do `EmpregadoModelAssembler` que converte `Empregado` pra um `EntityModel`, tipo usado pra criar os links, com o método `toModel()`. "Today is also my birthday! 🥳"


## Instalação ⚙️

1 - Obter o código desse repositório:
```bash
  git clone https://github.com/lvcaspacifico/folhadepagamento
  cd /folhadepagamento 
```

2 - Em `application.properties` substituir as tags:

* `${DB_PATH}` pela string de conexão do seu banco de dados
* `${DB_USER}` pelo usuário do seu banco (ex: "admin")
* `${DB_PASS}` pela senha do seu banco (ex: "123admin")

3 - Rodar o projeto e começar a mandar as requisições :)

## Documentação da API 📖

`Empregado` - Interações com dados de empregados

| Endpoint   | Método | Parâmetro | Body | Descrição |
| :---------- | :--------- | :--------- | :--------- |:--------- |
| `/api/v1/empregados` | `GET` | - | - | Retorna todos os empregados     |
| `/api/v1/empregados/{id}` | `GET` | id **(obrigatório)** | - | Retorna empregado do Id específicado     |
| `/api/v1/empregados` | `POST` | - | **"nome"** e **"cargo"** | Cria um empregado informando nome e cargo|
| `/api/v1/empregados/{id}` | `PUT` | id **(obrigatório)** | **"nome"** e/ou **"cargo"** | Altera um empregado de Id especificado |
| `/api/v1/empregados/{id}` | `DELETE` | id **(obrigatório)** | - | Excluí um empregado de Id especificado |

