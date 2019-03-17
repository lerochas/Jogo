# Jogo
# Quake log parser


## Requisitos de sistema

Para gerar e rodar a aplicaÃ§Ã£o vocÃª vai precisar ter instalado:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (local)
- [Maven 3](https://maven.apache.org) (local)
- [Docker](https://docs.docker.com/)

## Rodando a aplicaÃ§Ã£o local

**IMPORTANTE:**
Antes de executar a aplicaÃ§Ã£o, altere o caminho do arquivo de log no [application.yml](./src/main/resources/application.yml)
```app:
   quakeLog:
     fileName: nomeArquivoLog.log
     filePath: /camiho/fisico/do/arquivo/log/
```
Existem vÃ¡rias maneiras de executar um aplicativo Spring Boot em sua mÃ¡quina local. Uma maneira Ã© executar o mÃ©todo main na classe com.quakelog.QuakeLogApplication na sua IDE.

Alternativamente, vocÃª pode usar o [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) com o comando:

```shell
mvn spring-boot:run
```

Outra forma Ã© gerar a imagem docker e rodar o container local. Para isso Ã© necessÃ¡rio instalar o docker e seguir os comandos:

```shell
make build
```

Ao receber a mensagem de sucesso execute. A aplicaÃ§Ã£o ficara expota na porta 8080.

```shell
make run
```

## Rodando a aplicaÃ§Ã£o nos container

Toda os recursos da aplicaÃ§Ã£o pode ser executado todando os seguintes comandos no docker-compose.yml;

```
docker-compose build && docker-compose up
```

Para desligar
```
docker-compose down
``` 

### Testando o endpoint
```
curl -v http://localhost:8080/api/stats
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /api/stats HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
> 
< HTTP/1.1 200 
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Sun, 30 Sep 2018 21:06:40 GMT
< 
* Connection #0 to host localhost left intact
```

Detalhes da API no endereÃ§o do swagger

[swagger-ui](http://localhost:8080/swagger-ui.html)

### Testando fallback
Para testar o fallback da aplicaÃ§Ã£o, inicie a aplicaÃ§Ã£o sem o container do Elasticsearsh.

### Rodando os testes da aplicaÃ§Ã£o
Execute o comando:
```
mvn clean test
```

Testes unitÃ¡rios na camada de negÃ³cio e conecxÃ£o com banco de dados.
90% de cobertura desconsiderando classes de domain, json e exception.

![coverage](./extras/coverage.png)

### SoluÃ§Ã£o da aplicaÃ§Ã£o

Uma instancia do Filebeat escuta a pasta de log do servidor, com uma rotina para ler o arquivo e indexar os registros no Elasticsearch.
Ao efetuar a chamada GET na API, a aplicaÃ§Ã£o consulta no elasticsearsh os registros salvos, processa e restorna.

Caso ocorra alguma indisponibilidade por parte do elsaticsearsh, a aplicaÃ§Ã£o possui uma regra de fallback para contingencia, lendo o arquivo do filesystem e fazendo os mesmos processamentos.

**OBSERVAÃ‡ÃƒO:** Como o filebeat nÃ£o lÃª o arquivo linha por linha e o log nÃ£o tem data (dd/mm/yyyy), ele nÃ£o consegue agrupar por ordem cronolÃ³gica, diferente quando Ã© feito a leitura por arquivo, que assume a primeira linha como o primeiro registro, e assim sucessivamente.

Em resumo, quando o processo ocorrer pelo elasticsearch, o **response** vem em ordem diferente quando processo pelo arquivo (fallback, container do elasticsearch desligado).

### Detalhe tÃ©cnico

Filebeat:
    Servidor usado apensa para escutar a pasta configurada no arquivo filebeat.yml. O pattern para leitura do arquivo Ã© feita por uma regex e executando o flush com a mesma regex, fazendo recursividade nos blocos.
    
Elasticsearch:
    Utilizado apenas para armazenar os registros vindo do Filebeat. A consulta Ã© feita pela API do Elastic utilizando o feingClient para possibilitar a implementaÃ§Ã£o da regra de fallback.
    
Quake-log:
    App spring boot, com Java 8 seguindo os padrÃµes do clean architecture.
    Controller documentado usando o swagger, retornando status 200 no caso de sucesso. 
    Camada de usecase para, primeiro, consultar registros e retornar uma lista de String (estratÃ©gia para deixar o processamento do registro genÃ©rico, em caso de novas implementaÃ§Ãµes do gateway).
    Gateway com duas implementaÃ§Ãµes, uma para ler de um arquivo, armazenando as linhas em uma lista. Outra com uma chamada feing e o split do bloco da string para gerar a mesma lista.