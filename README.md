# XY-inc

## Introduction

> XY Inc. is a company specializing in the production of excellent GPS receivers (Global Positioning System) and this project was developed to maintain a backend for their new brand system, which intends to help people locate Points of Interest (POI).

## Quick Start

> 1. Install [Compose](http://gradle.org/gradle-download/)
2. Clone this repository using ` git clone https://github.com/emiteze/xy-inc.git `
3. Execute the command ` docker-compose up ` in the root of the project
4. Open in your browser the URL ` http://localhost:8080/swagger-ui.html `

## Endpoints

> 1. GET /get-point
Search for a specific Point Of Interest if given "id" via query parameter, or a list of Point Of Interest if given "name" via query parameter.
Expected parameters:
UUID id, String name (both optional)
Example: /get-point?id=5cfd917e-d1c8-48cd-b0a2-072b137cb40a or /get-point?name=Churrascaria
If both are sent, the endpoint will use the id parameter in the query.

> 2. GET /get-points
Search for all Point Of Interest saved in the database and don't take any parameters.

> 3. GET /get-points-nearby
Search for a list of Point Of Interest given a location reference and a maximum distance that the backend will query given that reference.
Expected parameters:
int coordx, int coordy, double maxDistance (all three required)
Example: /get-points-nearby?coordx=15&coordy=15&maxDistance=10

> 4. POST /save-point
Saves a Point Of Interest in the database.
Expected parameter:
Point Of Interest object
{
    "name": "Place",
    "coordx": 10,
    "coordy": 10
}

> 5. DELETE /delete-point
Delete a specific Point Of Interest if given "id" via query parameter or the object itself via body request, or a list of Point Of Interest if given "name" via query parameter.
Expected parameters:
UUID id, String name, Point Of Interest object
Example: /delete-point?id=5cfd917e-d1c8-48cd-b0a2-072b137cb40a or /delete-point?name="Churrascaria"

> 6. PUT /update-point
Update a specific Point Of Interest in the database passing the object with the new values.
Expeted parameter:
Point Of Interest object
{
    "id": 5cfd917e-d1c8-48cd-b0a2-072b137cb40a,
    "name": "Place",
    "coordx": 10,
    "coordy": 10
}


## Technologies

> Backend
* [SpringBoot](http://projects.spring.io/spring-boot/)

>Database
* [MongoDB](https://www.mongodb.org/)

>Testing
* [JUnit](http://junit.org/)
* [Mockito](http://mockito.org/)

> API Tooling
* [Swagger](https://swagger.io/)

> Git Repository
* [GitHub](https://github.com/)

> Deploy
* [Docker](http://www.docker.com/)
* [Docker Compose](http://www.docker.com/products/docker-compose)