# Mustache Showcase Frontend

> A simple boilerplate for **Mustache** template development.

## Libraries used
  * [mustache^2.3.0](https://mustache.github.io/)
  * [jquery^3.2.1](https://jquery.org/)
  * [bootstrap](http://getbootstrap.com/)
  * [sass](http://sass-lang.com/)
  * [moment-jdateformatparser](https://github.com/MadMG/moment-jdateformatparser) (Translates the `java.text.SimpleDateFormat` date format to the `moment.js` date format)

  * #### build tools
    * [gulp^3.9.1](https://gulpjs.com/)
    * [browser-sync^2.18.13](https://browsersync.io/)
    * [babeljs ^6.26.0](https://babeljs.io/)

## Setup

  * ### Requirements
    * nodejs
    * npm
    * yarn(optional) (`$ npm i -g yarn`)
    * gulp (`$ npm i -g gulp`)

  * ### How to / Installation
    * `$ git clone https://github.com/adaikiss/mustache-showcase.git mustache-showcase`(skip if you already cloned the repository)
    * `$ cd frontend && npm install` or `$ cd frontend && yarn install`


## Run

  * ### dev
    * `$ gulp dev`

  * ### build
    * `$ gulp clean` and then
    * `$ gulp`

## More
#### yarn add new dependency
```bash
yarn add <name>[@<version>] [-D/--dev]
```

### win10 install gulp-sass
```bash
npm install --global --production windows-build-tools
npm config set msvs_version 2015 --global
npm install --save-dev gulp-sass
```
