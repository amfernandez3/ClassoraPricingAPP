Pricing Service - Prueba Classora

Este proyecto implementa una API REST para la consulta de precios de productos en base a una fecha de aplicación, identificador de producto e identificador de cadena (brand).

Arquitectura:
Se ha utilizado Arquitectura Hexagonal (Ports & Adapters) para garantizar un desacoplamiento total entre la lógica de negocio y la infraestructura tecnológica:

Domain: Contiene los modelos de negocio (Records de Java).

Application: Contiene los casos de uso y las definiciones de los puertos (interfaces).

Infrastructure: Implementa los adaptadores (JPA, REST) y la configuración de Spring.

Tecnologías:
Java 21 (con uso de Records y sintaxis moderna).

Spring Boot 3.x.

Spring Data JPA.

H2 Database (Base de datos en memoria).

Lombok.

JUnit 5 & MockMvc para pruebas de integración.

Requisitos e Instalación:
Para ejecutar el proyecto necesitas tener instalado JDK 21 y Maven.

Clona el repositorio o descomprime el archivo.

Ejecuta la aplicación:

Bash mvn spring-boot:run La aplicación estará disponible en http://localhost:8080.

Ejecución de Tests Se han implementado los 5 casos de prueba solicitados en el enunciado mediante tests de integración que validan el flujo completo (Controller -> UseCase -> DB).
Para ejecutar los tests:

Bash mvn test

Endpoints Principales:
Consulta de precio (ejemplo): GET /api/v1/prices?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1

Consola H2: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:testdb)
