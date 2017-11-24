'use strict';

const Mustache = require('mustache');
const fs = require('fs');
const moment = require('moment');
require('moment-jdateformatparser');

/**
 * read data by path
 * @param path data file path
 * @returns {*}
 */
const readData = (path) => {
    const file = './mock/data/' + path + '.js';
    return fs.existsSync(file) ? eval(fs.readFileSync(file, 'utf-8')) : null;
};

/**
 * customize view data mapping
 */
const viewMapper = {
    'food/index': readData('food_index')
};

/**
 * specify layout mapping
 */
const layoutMapper = {
    'food': 'food/_layout',// custom layout
    'pets': 'category/_layout',// custom layout
    'default': '_layout'
};

module.exports = function (mustacheDir, mustacheSuffix) {
    return function (templateName) {
        // read mock data
        const data = viewMapper[templateName] || readData(templateName) || {};

        data.res_base = '/res';
        data.static_base = '';

        // dev_lambda, render content when in dev mode, always true here.
        data.dev_lambda = () => (text, render) => {
        };

        // calc_lambda
        data.calc_lambda = () => (text, render) => {
            // simply use eval as we only run it in dev
            return eval(render(text));
        };

        // variable_lambda, declare variable in current context
        data.variable_lambda = () => (text, render) => {
            let result = render(text);
            let index = result.indexOf('|');
            if (index !== -1) {
                data[result.substring(0, index).trim()] = result.substring(index + 1).trim();
            }
        };

        // format_date_lambda, use moment-jdateformatparser to translate the java.text.SimpleDateFormat format to moment format
        data.format_date_lambda = () => (text, render) => {
            let result = render(text);
            const splitIndex = result.indexOf('|');
            let date, format;
            if (splitIndex === -1) {
                date = result;
                format = 'yyyy-MM-dd';
            } else {
                date = result.substring(0, splitIndex);
                format = result.substring(splitIndex + 1);
            }
            return moment.unix(parseInt(date) / 1000.0).formatWithJDF(format);
        };

        // upload_url_lambda, render real upload url
        data.res_url_lambda = () => (text, render) => {
            let result = render(text);
            if (result.startsWith('http')) {
                return result;
            }
            return data.res_base + result;
        };

        // title_lambda, pass title to layout
        data.title_lambda = () => (text, render) => {
            data.__title__ = render(text);
        };

        data.footer_lambda = () => (text, render) => {
            // console.log(render(text, data));
            data.__footer__ = render(text, data);
        };

        // nav_active_lambda, render nav activation by data.__nav__
        data.nav_active_lambda = () => (text, render) => {
            // console.log(data);
            return data.__nav__ === render(text) ? 'active' : '';
        };

        // nav_name_lambda, pass nav name to layout
        data.nav_name_lambda = () => (text, render) => {
            // console.log('nav_name_lambda');
            data.__nav__ = render(text);
        };

        const layoutPrefixIndex = templateName.lastIndexOf('/'); // level specific layout, simply only support last level
        const layoutKey = layoutPrefixIndex === -1 ? templateName : templateName.substring(0, layoutPrefixIndex);
        // find layout by templateName, relative path, or default as fallback
        const layoutName = layoutMapper[templateName] || layoutMapper[layoutKey] || layoutMapper.default;

        console.log(mustacheDir + layoutName + mustacheSuffix);

        // layout_lambda lambda
        // noinspection JSUnusedLocalSymbols
        data.layout_lambda = () => (text, render) => {
            // console.log(templateName + '\'s layout:', layoutName);
            data.__body__ = Mustache.to_html(text, data);
            // console.log(data.__body__);
            return Mustache.to_html('{{> ' + layoutName + '}}', data, (partialName) => fs.readFileSync(mustacheDir + partialName + mustacheSuffix, 'utf-8'));
        };
        // console.log(templateName, data);
        return data;
    }
};