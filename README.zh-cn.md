# [Mustache](https://mustache.github.io/) 示例

> 一个演示如何在前端(Node)与后台(Java)使用同一份Mustache模板的例子

[English](/README.md) | 中文

Mustache是一个无逻辑的模板语言，有多个语言的实现。

本项目演示如何使用spring-webflux书写网站后台代码，而将mustache模板和css/js留给前端同事来完成。

## 快速尝试
```bash
// 检出代码
$ git clone https://github.com/adaikiss/mustache-showcase.git mustache-showcase mustache-showcase
// 进入前端代码目录
$ cd frontend
// 安装npm依赖
$ npm i
// 运行静态资源服务
$ gulp serve
// 进入后台代码目录
$ cd ../backend
// 运行后台服务
$ mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.mustache.prefix=file:..\\frontend\\template\\ -Dspring.profiles.active=dev" 
```

## [后台](backend/README.md)


## [前端](frontend/README.md)