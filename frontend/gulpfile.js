'use strict';

const gulp = require('gulp');
const sass = require('gulp-sass');
const fileData = require('gulp-data');
const browserSync = require('browser-sync').create();
const header = require('gulp-header');
const cleanCSS = require('gulp-clean-css');
const rename = require("gulp-rename");
const uglify = require('gulp-uglify');
const pkg = require('./package.json');
const babel = require('gulp-babel');
const clean = require('gulp-clean');
const mustache = require("gulp-mustache");
const path = require('path');


const mustacheDir = 'template/';
const mustacheSuffix = '.mustache';
const devMustacheDir = 'template_dev/';

const dist = 'dist/';
const vendor = dist + 'vendor/';
const mustacheDist = 'dist_html/';

// Set the banner content
const banner = ['/**', '\n * ', pkg.title, '\n * ', '@version: ', pkg.version, '\n * ', '@author: ', pkg.author, '\n * ', '@license: ', pkg.license, '\n * ', '@description: ', pkg.description, '\n **/\n'].join('');

gulp.task('clean', function () {
    return gulp.src(dist, {read: false}).pipe(clean());
});

// Compile SCSS files from /scss into /css
gulp.task('sass', function () {
    return gulp.src(['scss/**/*.scss'])
        .pipe(sass())
        .pipe(header(banner, {pkg: pkg}))
        .pipe(gulp.dest(dist + 'css'))
        .pipe(browserSync.reload({
            stream: true
        }))
});

// Minify compiled CSS
gulp.task('minify-css', ['sass'], function () {
    return gulp.src([dist + '/css/**/*.css', '!' + dist + '/css/**/*.min.css'])
        .pipe(cleanCSS({compatibility: 'ie8'}))
        .pipe(rename({suffix: '.min'}))
        .pipe(gulp.dest(dist + 'css'))
        .pipe(browserSync.reload({
            stream: true
        }))
});

// Copy JS to dist
gulp.task('js', function () {
    return gulp.src(['js/**/*.js'])
        .pipe(babel({
            presets: ['env']
        }))
        .pipe(header(banner, {pkg: pkg}))
        .pipe(gulp.dest(dist + 'js'))
        .pipe(browserSync.reload({
            stream: true
        }))
});

// Minify JS
gulp.task('minify-js', ['js'], function () {
    return gulp.src('js/**/*.js')
        .pipe(babel({
            presets: ['env']
        }))
        .pipe(uglify())
        .pipe(header(banner, {pkg: pkg}))
        .pipe(rename({suffix: '.min'}))
        .pipe(gulp.dest(dist + 'js'))
        .pipe(browserSync.reload({
            stream: true
        }))
});

gulp.task('copy-image', function () {
    gulp.src(['image/**/*']).pipe(gulp.dest(dist + 'image')).pipe(browserSync.reload({
        stream: true
    }));
});

// Copy vendor libraries from /bower_components into /vendor
gulp.task('copy-vendor', function () {

    gulp.src(['node_modules/jquery/dist/jquery.js', 'node_modules/jquery/dist/jquery.min.js'])
        .pipe(gulp.dest(vendor + 'jquery'));

    gulp.src(['node_modules/bootstrap/dist/**/*', '!**/npm.js', '!**/bootstrap-theme.*', '!**/*.map'])
        .pipe(gulp.dest(vendor + 'bootstrap'));
    gulp.src(['node_modules/popper.js/dist/umd/*.js'])
        .pipe(gulp.dest(vendor + 'popperjs'));

    gulp.src('node_modules/masonry-layout/dist/*.js')
        .pipe(gulp.dest(vendor + 'masonry-layout'));
});

gulp.task('mustache', function () {
    const mustacheBasePath = path.resolve(mustacheDir);
    const devMustacheBasePath = path.resolve(devMustacheDir);
    //clean previous required cache of data
    delete require.cache[require.resolve('./mock')];
    const mockData = require('./mock')(mustacheDir, mustacheSuffix);
    // noinspection JSUnusedLocalSymbols
    return Promise.all([
        new Promise(function (resolve, reject) {
            gulp.src(mustacheDir + "**/[!_]*" + mustacheSuffix)
                .pipe(fileData(file => {
                    const templateName = file.path.substring(mustacheBasePath.length + 1, file.path.length - mustacheSuffix.length).replace(/\\/g, '/');
                    // console.log(file.path, templateName);
                    return mockData(templateName);
                }))
                .pipe(mustache({}))
                .pipe(rename(function (opt) {
                    opt.extname = '.html';
                    return opt;
                }))
                .pipe(gulp.dest(mustacheDist))
                .on('end', stream => resolve(stream));
        }),
        new Promise(function (resolve, reject) {
            gulp.src(devMustacheDir + "**/[!_]*" + mustacheSuffix)
                .pipe(fileData(file => {
                    const templateName = file.path.substring(devMustacheBasePath.length + 1, file.path.length - mustacheSuffix.length).replace(/\\/g, '/');
                    // console.log(file.path, templateName);
                    return mockData(templateName);
                }))
                .pipe(mustache({}))
                .pipe(rename(function (opt) {
                    opt.extname = '.html';
                    return opt;
                }))
                .pipe(gulp.dest(mustacheDist))
                .on('end', (stream) => resolve(stream));
        })
    ]).then(function () {
        browserSync.reload();
    });
});

// Run everything
gulp.task('default', ['minify-css', 'minify-js', 'copy-vendor', 'copy-image']);

// Just serve static resources(seems like spring web locks template files while running)
gulp.task('serve', ['sass', 'js', 'copy-vendor', 'copy-image'], function(){
    browserSync.init({
        server: {
            baseDir: dist,
            routes: {
                '/static': dist,
                '/res': 'mock/res'
            }
        }
    });

    gulp.watch("js/**/*.js", ['js']);
    gulp.watch("scss/**/*.scss", ['sass']);
    gulp.watch("image/**/*", ['copy-image']);
});

// Static Server + watching scss/html files
gulp.task('dev', ['sass', 'js', 'copy-vendor', 'copy-image', 'mustache'], function () {

    browserSync.init({
        server: {
            baseDir: dist,
            routes: {
                '/': mustacheDist,
                '/static': dist,
                '/res': 'mock/res'
            }
        }
    });

    gulp.watch("js/**/*.js", ['js']);
    gulp.watch("scss/**/*.scss", ['sass']);
    gulp.watch("image/**/*", ['copy-image']);
    gulp.watch([path.join(mustacheDir, "**/*"), path.join(devMustacheDir, '**/*'), 'mock/**/*.js*'], ['mustache']);
    // we notify browser-sync in mustache task
    // gulp.watch(path.join(mustacheDist, "**/*.html")).on('change', browserSync.reload);
});