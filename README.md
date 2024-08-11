# API Desafio PicPay Backend Spring Boot

### Projeto desenvolvido para solucionar um desafio de backend. A solução é uma versão simplificada de um sistema de transações.

## Tecnologias
- Spring Boot
- Spring MVC
- Spring Data JDBC
- Spring for Apache Kafka
- Docker Compose
- H2

## Como Executar
1. Clonar repositório git:
   ```bash
   git clone https://github.com/giuliana-bezerra/picpay-desafio-backend.git
    ```
## Executar o Kafka:
   ```bash
      docker-compose up
   ```

## Executar a aplicação Spring Boot
   ```bash
      Acessar aplicação em http://localhost:8080.
   ```
## Arquitetura
- (AQUI VAI IMAGENS DO DIAGRAMA)
## API
### Criar Transação
- POST /transaction
- Content-Type: application/json
   ```json
    {
     "payee": 2,
     "payer": 1,
     "value": 100.0
    }
   ```



### Resposta:
- HTTP/1.1 200 OK
- Content-Type: application/json

```json
{
  "createdAt": "2024-03-05T16:07:50.749774",
  "id": 20,
  "payee": 2,
  "payer": 1,
  "value": 100.0
}
```

### Listar Transações
- GET /transaction
- Content-Type: application/json

```json
[
  {
    "createdAt": "2024-03-05T16:07:50.749774",
    "id": 20,
    "payee": 2,
    "payer": 1,
    "value": 100.0
  }
]
```