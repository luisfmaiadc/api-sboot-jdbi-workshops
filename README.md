<h1 align="center">api-sboot-jdbi-workshops</h1>

<p align="center" style="margin-bottom: 20;"> 
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" /> 
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" alt="Springboot" /> 
  <img src="https://img.shields.io/badge/apache%20maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white" alt="Maven" /> 
  <img src="https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />
  <img src="https://img.shields.io/badge/flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white" alt="Flyway" /> 
</p> 

<p align="center">A <b>api-sboot-jdbi-workshops</b> é uma API REST desenvolvida em Java com Spring Boot para gerenciar oficinas mecânicas, suas especialidades e fabricantes associadas. Ela permite o cadastro, atualização e consulta de oficinas, além de suas especializações e marcas atendidas.</p>

<h2>📌 Visão Geral</h2> <p align="justify">Este projeto utiliza a abordagem <b>Contract First</b>, com especificação OpenAPI servindo como base para geração automática de modelos via Swagger Codegen. Os dados são persistidos em um banco relacional <b>MySQL</b> com versionamento gerenciado por <b>Flyway</b>. A comunicação com o banco é feita por meio do <b>JDBI</b>.</p>

<p>Esta API faz parte de um ecossistema integrado, sendo consumida por uma camada de orquestração com <b>Apache Camel</b> que intermedia a comunicação entre a API de veículos e esta API de oficinas.</p>

<h2>🚀 Tecnologias Utilizadas</h2>

- <b>Java 21 + Spring Boot 3.4.5</b>
- <b>Spring Web</b>
- <b>JDBI</b> (acesso ao banco de dados relacional)
- <b>MySQL</b> (persistência dos dados)
- <b>Flyway</b> (controle de versionamento do banco)
- <b>OpenAPI + Swagger Codegen</b> (geração de contratos a partir de especificações)

<h2>🏛️ Arquitetura do Sistema</h2>

```bash
    1. [Cliente/API Consumers] --> HTTP (api-sboot-jdbi-vehicles)
    2. [api-sboot-jdbi-vehicles] --> HTTP (api-sboot-camel-vehicle)
    3. [api-sboot-camel-vehicle] --> HTTP (api-sboot-jdbi-workshops)
```

- api-sboot-jdbi-vehicles → Responsável por veículos e manutenções.
- api-sboot-camel-vehicle → Camada de orquestração utilizando Apache Camel.
- api-sboot-jdbi-workshops → API independente que gerencia oficinas e serviços.

<h2>🏗️ Estrutura do Projeto</h2>

```bash
api-sboot-jdbi-workshops
│-- src/main/java/com/portfolio/luisfmdc/api-sboot-jdbi-workshops
│   ├── config/            # Configuração do JDBI
│   ├── controller/        # Endpoints REST
│   ├── mapper/            # Mapeamento de entidades
│   ├── model/             # Classes de modelo
│   ├── repository/        # Interfaces JDBI e mapeamentos SQL
│   ├── service/           # Regras de negócio
│   ├── WorkshopsApplication.java  # Classe principal
│-- src/main/resources
│   ├── api-sboot-jdbi-workshops\src\main\resources\com\portfolio\luisfmdc\api-sboot-jdbi-workshops\repository\WorkshopRepository  # Queries SQL utiilizadas
│   ├── application.properties  # Configurações do projeto
│   ├── db/migration            # Scripts de migration (Flyway)
│   ├── openapi.yml             # Arquivo de contrato OpenAPI
│-- pom.xml                     # Dependências do projeto
```

<h2>🛠️ Configuração e Execução</h2>

<h3>📌 Pré-requisitos</h3>

1. Ter o <b>MySQL</b> instalado e rodando localmente.
2. O Flyway criará as tabelas automaticamente com base nos scripts localizados em <code>db/migration</code>.

<h3>📜 Configuração do Banco de Dados (<code>application.properties</code>)</h3>

```properties
spring.application.name=api-sboot-jdbi-workshops
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/dbOficinas?createDatabaseIfNotExist=true
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

<h3>🚀 Executando a API</h3>

```sh
git clone https://github.com/luisfmaiadc/api-sboot-jdbi-workshops.git
cd api-sboot-jdbi-workshops

mvn clean install
mvn spring-boot:run
```

<h2>🧩 OpenAPI / Contract First</h2>

<p>O contrato OpenAPI está definido em <code>resources/openapi.yml</code>. As classes de request e response são geradas automaticamente utilizando <b>Swagger Codegen</b> com base nesse contrato.</p>

<h2>📚 Mais Informações</h2>

<p>Este projeto é parte de um estudo sobre arquitetura modular e boas práticas em integração de microsserviços com Java moderno, uso de OpenAPI, JDBI, e práticas como versionamento de banco com Flyway e orquestração com Apache Camel.</p>