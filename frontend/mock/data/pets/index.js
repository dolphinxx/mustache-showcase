(function () {
    const mapper = {
        "0": "7",
        "1": "d",
        "2": "q",
        "3": "j",
        "4": "m",
        "5": "o",
        "6": "r",
        "7": "u",
        "8": "1",
        "9": "4",
        "a": "0",
        "b": "8",
        "c": "5",
        "d": "2",
        "e": "v",
        "f": "s",
        "g": "n",
        "h": "k",
        "i": "h",
        "j": "e",
        "k": "b",
        "l": "9",
        "m": "6",
        "n": "3",
        "o": "w",
        "p": "t",
        "r": "p",
        "s": "l",
        "t": "i",
        "u": "f",
        "v": "c",
        "w": "a",
        "_z2c$q": ":",
        "_z2C$q": ":",
        "AzdH3F": "/",
        "_z&e3B": "."
    };
    const markRegex = /(_z2C\$q|_z2c\$q|_z&e3B|AzdH3F)/g;
    const letterRegex = /([a-w\d])/g;
    const decodeBaiduUrl = (url) => {
        let result = url.replace(markRegex, function (g, m) {
            return mapper[m] || m;
        });
        result = result.replace(letterRegex, function (g, m) {
            return mapper[m] || m;
        });
        return result;
    };
    const Item = require('./Item.js');
    const request = require('sync-request');
    const moment = require('moment');
    const list = [];
    const res = request('GET', 'https://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord=cats&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=-1&z=&ic=0&word=cats&s=&se=&tab=&width=&height=&face=0&istype=2&qc=&nc=1&fr=&pn=1&rn=30&gsm=96&1511519936124=');
    const parsedData = JSON.parse(res.getBody());
    parsedData.data.forEach((record) => {
        if (record.di === undefined) {
            return;
        }
        list.push(new Item({
            "id": record.di,
            "title": record.fromPageTitleEnc,
            "thumb": record.thumbURL,
            "createTime": moment(record.bdImgnewsDate).unix() * 1000,
            "visits": 0,
            "tags": [],
            "source": decodeBaiduUrl(record.fromURL)
        }));
    });
    return {
        "record": {
            "id": "pets",
            "name": "Pets"
        },
        "list": list
    };
}());