# Mustache Showcase Backend

> A spring web server using mustache template

## Libraries used

* #### spring-boot-starter-webflux

* #### spring-boot-starter-mustache

* #### lombok

## Setup

* ### Requires
    * JDK
    * Maven

* ### Installation
    * `$ git clone https://github.com/adaikiss/mustache-showcase.git mustache-showcase`
    * `$ cd mustache-showcase/backend && mvn clean package`

## Run (before this, go to frontend directory and run gulp serve to serve static resources)
- `$ mvn clean spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.mustache.prefix=file:..\\frontend\\template\\ -Dspring.profiles.active=dev"`
- add this to `argLine` to use another port `-Dserver.port=8888`

