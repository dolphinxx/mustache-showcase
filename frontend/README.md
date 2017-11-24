# Mustache Showcase Frontend

> A simple boilerplate for **Mustache** template development.

English | [中文](/frontend/README.zh-cn.md)

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
        > it will execute all tasks expect the compress ones, then serve web service through `browser-sync`, and watch css, js, image and template directories as well, when there is a file modification, the `browser-sync` will be notified and reload page resources automatically

  * ### build
    * `$ gulp clean` to clean old built files and then
    * `$ gulp` to build fresh dist files
        > * compile sass files to css files, minimize them to min files, and output to `/dist/css` directory
        > * transform es6 grammar js files to es5's, and minimize them to min files, and then output to `/dist/js` directory
        > * copy dependencies to `/dist/vendor` directories
        > * copy all files under image to `/dist/image`

## Intro
### File structure
- js: Javascript source codes
- scss: sass source codes
- image: All images
- template: Mustache template files, which will be copied to backend template directory while server building(eg: `classpath:/templates/`)
    > Using sass-style naming rule, means all partial files prefixed with `_`
- template_dev: Mustache template files used only during develop, as the name indicates
    > Since most of product templates are generic, one template renders several type of data(eg: `/template/category/index.mustache` is responsible for rendering generic category index pages such as Pets, Living and so on), we can create a specific template file under this directory, and prepare specific mock data for it, `/template_dev/pets/index.mustache` for example, so we are able to see the 'real' rendering result by visiting `/pets/index.html`, instead of `/category/index.html`
- mock: directory for mock data
    > index.js supplies template name specific mock data and several [lambda](https://mustache.github.io/mustache.5.html) functions
    > mock data is located in `data` subdirectory, mock data is first searched in `viewMapper` in `index.js`, and then searched in the `data` subdirectory using the template name(that's one of the reasons we separate the `template_dev` directory), and at last an empty object will be returned as fallback
- dist: the well-known build target directory, all resources expect templates will be output here, such as css, js, image and so on
- dist_html: target directory for templates, templates under `/template` and `/template_dev` will be output to this directory

### CSS
* [SASS](http://sass-lang.com/) is used in this project, but it can be easily replaced with [LESS](http://lesscss.org/) and [Compass](http://compass-style.org/), just need to install specific gulp plugin, and add a gulp task to compile the vendor css grammar to normal css
* all files under `sass` without the prefix `_` will be compiled to a normal `css` file, and leave the files prefixed with  `_` as partial files intended to be imported by other sass files

### JS
* [ES6](http://es6-features.org/) language level is used in this project, and they will be transformed to [ES5](http://ecma-international.org/ecma-262/5.1/) grammar through `babel-preset-env`

### Mustache
* Mustache is a logical-less template language, who's [Grammar](https://mustache.github.io/mustache.5.html) is really extremely simple
* The main target of this project is to illustrate usages of Mustache, including basic syntax, partial including, lambdas, as well as layout and variable declaration through lambda
* `layout_lambda` provides layout ability, it will first render inner body, and put the result into current context named with `__body__`, and then choose suitable layout template to render, when rendering layout template, it will fetch the `__body__` variable, and write it to the corresponding place(`{{__body__}}`), which is done by mustache naturally
* `variable_lambda` provides the ability of declaring variables
* Other `lambda` functions are more or less similar with each other, the underlying implementation is rendering inner body of a `lambda` tag, transforming it and writing it to the place where the tag stands, or exposing it to the current context so as to be used anywhere else
* We can also write an [OGNL](https://commons.apache.org/proper/commons-ognl/language-guide.html) interpreting `lambda` to provide the powerful(also complex) OGNL support, but frontend developers may not familiar to it


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
