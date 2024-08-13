# API Desafio PicPay Backend Spring Boot

### Projeto desenvolvido para solucionar um desafio de backend. A solução é uma versão simplificada de um sistema de transações.
* projeto baseado nesse desafio
[Desafio picpay - backend](https://github.com/PicPay/picpay-desafio-backend?tab=readme-ov-file)

## Tecnologias
- Spring Boot
- Spring Data JDBC
- Spring for Apache Kafka
- Docker Compose
- SQL
- H2
- Swagger


## Como Instalar e Executar

1. Clone este repositório:
   `git clone clone https://github.com/Mizaeldouglas/picpay-desafio-backend-java.git`
2. Navegue até a pasta do projeto:
   `cd picpay-desafio-backend-java`
3. Instale as dependências do projeto utilizando Maven:
   `mvn clean install`
4. Configure as variáveis de ambiente:
   ```ini
    spring.application.name=picpay-desafio-backend
    spring.datasource.url=jdbc:h2:file:./data/picpay
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=

    spring.h2.console.enabled=true
    spring.h2.console.path=/h2-console

    spring.sql.init.mode=always
    spring.kafka.bootstrap-servers=localhost:9094
    spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
    spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
    spring.kafka.consume
   ```
5. Inicie a aplicação:
   `mvn spring-boot:run`
6. Acesse o console do H2:
   `http://localhost:8080/h2-console`
7. A aplicação estará disponível em `http://localhost:8080` ou no swagger `http://localhost:8080/swagger-ui/index.html#/`


## Usando Docker
1. Clone este repositório: 
    ```
        git clone https://github.com/Mizaeldouglas/picpay-desafio-backend-java.git
    ```
2. Navegue até a pasta do projeto: 
    ```
        cd picpay-desafio-backend-java
    ```
3. Construa e inicie os contêineres Docker: 
    ```
      docker-compose up --build
    ```
4. A aplicação estará disponível em: 
    ```
        http://localhost:8080
    ```
    ou no swagger
    ```
        http://localhost:8080/swagger-ui/index.html#/
    ```


## Arquitetura
<br>

![Captura de Tela 2024-08-12 às 21 17 55](https://github.com/user-attachments/assets/27c2aeb2-c4e0-43ab-ac0e-15672a859b26)


![Captura de Tela 2024-08-12 às 21 18 15](https://github.com/user-attachments/assets/b3862b23-16db-4fbd-a72e-20d7146f74be)





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
