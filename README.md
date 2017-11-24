# [Mustache](https://mustache.github.io/) Showcase

> A simple mustache showcase illustrates how to use the same mustache templates in both frontend(Node) and backend(Java).

English | [中文](README.zh-cn.md)

Mustache is a logic-less templates, implemented in many languages. 

This project shows how we can write a web project's backend codes with spring-webflux and leave mustache templates and css/js codes to the frontend colleagues.

## Quick try
```bash
// checkout the project
$ git clone https://github.com/adaikiss/mustache-showcase.git mustache-showcase mustache-showcase
// go to frontend directory
$ cd frontend
// install npm dependencies
$ npm i
// run static resource server
$ gulp serve
// go to backend directory
$ cd ../backend
// run backend server
$ mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.mustache.prefix=file:..\\frontend\\template\\ -Dspring.profiles.active=dev" 
```

## [Backend](backend/README.md)


## [Frontend](frontend/README.md)