# Mustache 示例后台

> 一个使用mustache作为模板的spring web服务

## 用到的依赖库

* #### spring-boot-starter-webflux

* #### spring-boot-starter-mustache

* #### lombok

## 部署

* ### 依赖
    * JDK
    * Maven

* ### 安装
    * `$ git clone https://github.com/adaikiss/mustache-showcase.git mustache-showcase`
    * `$ cd mustache-showcase/backend && mvn clean package`

## 运行
> 在此之前，先到frontend目录运行`gulp serve`来提供静态资源服务，以便能请求到依赖的css/js。
- `$ mvn clean spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.mustache.prefix=file:..\\frontend\\template\\ -Dspring.profiles.active=dev"`
- add this to `argLine` to use another port `-Dserver.port=8888`

