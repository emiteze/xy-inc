# XY-inc

## Introduction

> XY Inc. is a company specializing in the production of excellent GPS receivers (Global Positioning System) and this project was developed to maintain a backend for their new brand system, which intends to help people locate Points of Interest (POI). 

## Quick Start

> 1.Install [MongoDB](https://www.mongodb.com/download-center), [Java](https://www.java.com/pt_BR/download/) and [Maven](https://maven.apache.org/download.cgi)

PS: To see this project in NodeJS, click [here](https://github.com/emiteze/node-xy-inc) 
>
> 2.Clone this repository using ` git clone https://github.com/emiteze/xy-inc.git `
>
> 3.On the root of the project, run:
>
> ``` mkdir data && mkdir data/db (this creates the project database path used by mongo to store data) ```
>
> ``` mongod --dbpath=data/db (this will initiate a mongodb server at localhost:27017) ```
>
> ``` mvn dependency:resolve (this will handle the dependencies of the project) ```
>
> ``` mvn clean install (this will build the project) ```
>
> ``` mvn spring-boot:run (this will start the webservice at localhost:8080) ```
>
> 4.Open in your browser the URL ` http://localhost:8080/swagger-ui.html ` to test the webservice or use any HttpClient that you want.

## Endpoints

> ` GET /get-point `
>
> Search for a specific Point Of Interest if given "id" via query parameter, or a list of Point Of Interest if given "name" via query parameter.
>
> Expected parameters:
>
> UUID id, String name (both optional)
>
> Example: /get-point?id=5cfd917e-d1c8-48cd-b0a2-072b137cb40a or /get-point?name=Churrascaria
>
> If both are sent, the endpoint will use the id parameter in the query.
>
> ` GET /get-points `
>
> Search for all Point Of Interest saved in the database and don't take any parameters.
>
> ` GET /get-points-nearby `
>
> Search for a list of Point Of Interest given a location reference and a maximum distance that the backend will query given that reference.
>
> Expected parameters:
>
> int coordx, int coordy, double maxDistance (all three required)
>
> Example: /get-points-nearby?coordx=15&coordy=15&maxDistance=10
>
> ` POST /save-point `
>
> Saves a Point Of Interest in the database.
>
> Expected parameter:
```
Point Of Interest object
{
    "name": "Place",
    "coordx": 10,
    "coordy": 10
}
```
> In order to update a specific point already added to the database, just sent the object you want to update in the same endpoint that saves a new point, but passing the id in the object, as follow:
```
Point Of Interest object
{
    "id": 5cfd917e-d1c8-48cd-b0a2-072b137cb40a,
    "name": "Place",
    "coordx": 10,
    "coordy": 10
}
```
> ` DELETE /delete-point `
>
> Delete a specific Point Of Interest if given "id" via query parameter or the object itself via body request, or a list of Point Of Interest if given "name" via query parameter.
>
>Expected parameters:
>
> UUID id, String name, Point Of Interest object
>
> Example: /delete-point?id=5cfd917e-d1c8-48cd-b0a2-072b137cb40a or /delete-point?name="Churrascaria"
>
PS: All endpoints returns status 200 (ok) if everything goes well and 400 (bad request) if something goes wrong`

## Technologies

> Backend
> * [SpringBoot](http://projects.spring.io/spring-boot/)
>
>Database
> * [MongoDB](https://www.mongodb.org/)
>
>Testing
> * [JUnit](http://junit.org/)
> * [Mockito](http://mockito.org/)
>
> API Tooling
> * [Swagger](https://swagger.io/)
>
> Git Repository
> * [GitHub](https://github.com/)

## Future Improvements

> Dockerize our project to simplify the build and run process to just one line command.
> * [Docker](http://www.docker.com/)
> * [Docker Compose](http://www.docker.com/products/docker-compose)
