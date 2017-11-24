# Mustache示例前端

> 一个简单的**Mustache**模板开发样板

[English](frontend/README.md) | 中文

## 用到的依赖库
  * [mustache^2.3.0](https://mustache.github.io/)
  * [jquery^3.2.1](https://jquery.org/)
  * [bootstrap](http://getbootstrap.com/)
  * [sass](http://sass-lang.com/)
  * [moment-jdateformatparser](https://github.com/MadMG/moment-jdateformatparser) (Translates the `java.text.SimpleDateFormat` date format to the `moment.js` date format)

  * #### 开发构建工具
    * [gulp^3.9.1](https://gulpjs.com/)
    * [browser-sync^2.18.13](https://browsersync.io/)
    * [babeljs ^6.26.0](https://babeljs.io/)

## 部署

  * ### 依赖
    * nodejs
    * npm
    * yarn(optional) (`$ npm i -g yarn`)
    * gulp (`$ npm i -g gulp`)

  * ### 安装
    * `$ git clone https://github.com/adaikiss/mustache-showcase.git mustache-showcase`(skip if you already cloned the repository)
    * `$ cd frontend && npm install` or `$ cd frontend && yarn install`


## 运行

  * ### 开发模式
    * `$ gulp dev`
        > 先执行一遍除压缩和清理之外的所有任务，然后通过`browser-sync`提供web服务，并且监视css、js、image和模板目录，当有文件改动时会通知`browser-sync`实时更新网页

  * ### 构建
    * `$ gulp clean` 清除旧的文件，然后运行
    * `$ gulp` 构建新的最终文件
        > * 编译sass文件为css，压缩成min文件，输出至`/dist/css`目录
        > * 通过babel将es6语法的js文件转换成es5语法，并压缩成min文件，输出至`/dist/js`目录
        > * 复制依赖库至`/dist/vendor`目录
        > * 复制image目录下的文件至`/dist/image`目录

## 介绍
### 目录结构
- js: JS源码
- scss: SASS源码
- image: 图片
- template: Mustache模板，后台打包时会将这个目录下的模板文件复制到后台模板目录（如`classpath:/templates/`）。
    > 命名规则参考了SASS，即非门面模板文件都以`_`为前缀以示区别
- template_dev: 开发时用的Mustache模板，正如其名称后缀，只在开发时使用
    > 因为正式的模板大多是一个模板适用多种数据的（如/template/category/index.mustache用于Pets、Living等多个分类的渲染），这时可以在这个目录下创建一个专门的模板文件，并提供专门的测试数据，如为/template_dev/pets/index.mustache提供一组pet相关的数据，并且可以通过/pets/index.html进行访问
- mock: 开发时用到的mock相关数据都存放在这个目录下
    > index.js为Mustache模板提供模板名称级的数据，和一些[lambda](https://mustache.github.io/mustache.5.html)方法
    > `data`子目录存放mock数据，mock数据查找优先按`index.js`中声明的映射`viewMapper`，如果没有显式映射，则按模板名称以`data`子目录为根目录查找数据文件（这也正是独立出`template_dev`的原因之一），如果也没有找到，则会返回一个空对象
- dist: 众所周知的编译目标目录，除模板以外的所有资源都被输出到此目录，如css, js, image等
- dist_html: 模板输出目录，`/template`和`/template_dev`中的模板编译后都会输出到这个目录

### CSS
* 本项目使用的是[SASS](http://sass-lang.com/)，不过可以轻易地替换为[LESS](http://lesscss.org/)及[Compass](http://compass-style.org/)等，只需要安装相应的gulp插件，并添加一个gulp任务，将现在的`sass`任务替换掉即可
* 所有sass目录下不是`_`前缀的`scss`文件都会被编译成相应的`css`文件，而以`_`前缀的文件被视为局部文件，只用来被其它文件引用

### JS
* JS支持[ES6](http://es6-features.org/)语法，并且通过`babel-preset-env`来转换成[ES5](http://ecma-international.org/ecma-262/5.1/)语法

### Mustache
* Mustache是一个无逻辑模板语言，其[语法](https://mustache.github.io/mustache.5.html)简单到极致
* 本项目主要演示Mustache用法，包括基本语法、片段引入、lambda方法及通过lambda实现布局、声明变量等功能
* `layout_lambda`提供布局功能，它会先渲染被自己包裹的内容，并将结果以`__body__`命名保存在当前context中，然后选择合适的布局模板渲染，在渲染布局模板时，会从context中获取`__body__`变量输出到相应位置
* `variable_lambda`使得可以在Mustache中声明变量
* 其它一些`lambda`大同小异，原理都是将`lambda`标签包裹的内容处理后输出，或存放到context中供其它地方使用
* 如有必要，也可以通过`lambda`来实现一个[OGNL](https://commons.apache.org/proper/commons-ognl/language-guide.html)解析标签，不过前端开发者可以对它不怎么熟悉

## 更多
#### yarn添加新依赖库
```bash
yarn add <name>[@<version>] [-D/--dev]
```

### win10安装gulp-sass
```bash
npm install --global --production windows-build-tools
npm config set msvs_version 2015 --global
npm install --save-dev gulp-sass
```
