# Quake log parser 


A aplicação tem como função de ler o arquivo games.log, agrupar os dados de cada jogo em um ranking geral, com informações de todas as partidas disputadas; como players que disputaram, o total de mortes ocorridas durante a partida e quem causou e sofreu a morte.

## Requisitos

Para gerar e rodar a aplicação, você precisará ter instalado em sua máquina:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (local)
- [Maven 3](https://maven.apache.org) (local)
- [Docker](https://docs.docker.com/)

## Como rodar a aplicação

Antes de executar, altere o caminho do arquivo de log no [application.yml](./src/main/resources/application.yml)
```app:
   quakeLog:
     fileName: nomeArquivoLog.log
     filePath: /camiho/fisico/do/arquivo/log/
```
Uma das maneiras de executar um aplicavo Spring Boot em sua máquina local é executando o método main, na classe com.game.gamequake.GameQuakeApplication em sua IDE.

Você pode usar, alternativamente, o [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) com o comando:

```shell
mvn spring-boot:run
```

## Rodando a aplicação nos container

Todos os recursos da aplicação podem ser executados com os seguintes comandos no docker-compose.yml;

```
docker-compose build && docker-compose up
```

Para desligar
```
docker-compose down
``` 