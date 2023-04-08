# Spring Boot Mongo DB Crud

Demo TODO application built on SpringBoot and MongoDB
<br>
[![](https://i.ytimg.com/vi/WUMIplH9_NE/hqdefault.jpg?sqp=-oaymwEcCNACELwBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLB12aiw7DL96WX0BFPty9uYMtWESg)](https://www.youtube.com/playlist?list=PLA7e3zmT6XQUjrwAoOHvNu80Axuf-3jft)

## APIs

The server runs on Tomcat, on <http://localhost:8090>
Route | METHOD | Description
---------------------------- | ------ | -------------
/todos | GET | Get all TODOs
/todos/{todoId} | GET | Get TODO by id
/todos | POST | Add/Create a new TODO
/todos/{todoId} | PUT | Update TODO
/todos/{todoId} | DELETE | Delete TODO

## Dependencies

* [Spring Boot Starter Data MongoDB](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-mongodb)
* [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
* [Spring Boot Devtools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)
* [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)
* [Spring Boot Starter Test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test)
* [Spring Boot Starter Validation](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation)
