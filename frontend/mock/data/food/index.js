(function () {
    const Item = require('./Item.js');
    const _raw = [
        new Item({
            "id": "1",
            "title": "8 Tips Transitioning Vegan Plant Based Diet",
            "thumb": "http://cdn.skim.gs/image/upload/c_fill,f_auto,fl_lossy,h_518,q_auto,w_694/carrots_0",
            "createTime": "1511311130611",
            "visits": "12332",
            "tags": ["food", "health", "living"],
            "source": "http://www.sheknows.com/community/health/8-tips-transitioning-vegan-plant-based-diet",
            "summary": "Making the transition to a vegan/plant based diet can leave your feeling overwhelmed at times. There are so many questions… Is this good? Is that bad? Why not this? How come that? It’s a whirlwind of questions and information that can make your head ache and get on your very last nerve. It can leave you feeling uncertain, frustrated and it can even make you feel like giving up!"
        }),
        new Item({
            "id": "2",
            "title": "HOW TO PLAN AND MAKE A VIRAL TIMELAPSE VIDEO",
            "thumb": "https://23527-presscdn-pagely.netdna-ssl.com/wp-content/uploads/2017/11/nathaniel_dodson.jpg",
            "createTime": "1511311130611",
            "visits": "891",
            "tags": [" cinematography", "filmmaking", "Nathaniel Dodson", "timelapse", "timelapse tutorial", "timelapse video", "Tutvid", "Video", "Viral"],
            "source": "https://www.diyphotography.net/plan-make-viral-timelapse-video/",
            "summary": "With so many timelapse films being created now, it can be difficult to make yours stand out. But those that do go viral often do not do so all by themselves. There’s a lot of time and planning that goes into them before the first frame is even created. Then there’s more effort that goes into their promotion after they get published."
        }),
        new Item({
            "id": "3",
            "title": "THREE TECHNIQUES TO CREATE DRAMATIC BLACK & WHITE IMAGES",
            "thumb": "https://23527-presscdn-pagely.netdna-ssl.com/wp-content/uploads/2017/11/dramatic_black_and_white-745x419.jpg",
            "createTime": "1511311130611",
            "visits": "12332",
            "tags": ["Black & White", "Blake Rudis", "colour", "f64 Academy", "photoshop", "tutorial"],
            "source": "https://www.diyphotography.net/three-techniques-create-dramatic-black-white-images/",
            "summary": "Making the transition to a vegan/plant based diet can leave your feeling overwhelmed at times. There are so many questions… Is this good? Is that bad? Why not this? How come that? It’s a whirlwind of questions and information that can make your head ache and get on your very last nerve. It can leave you feeling uncertain, frustrated and it can even make you feel like giving up!"
        }),
        new Item({
            "id": "4",
            "title": "NATIONAL PARK ADMISSION PRICES MAY INCREASE UP TO $70 IN THE NEW YEAR",
            "thumb": "https://23527-presscdn-pagely.netdna-ssl.com/wp-content/uploads/2017/11/national_parks-745x419.jpg",
            "createTime": "1511311130611",
            "visits": "12332",
            "tags": ["Department of the Interior", "government", "National Park", "National Park Service"],
            "source": "https://www.diyphotography.net/national-park-admission-prices-increase-70-new-year/",
            "summary": "According to BRProud, the US Department of the Interior is proposing to double or almost triple the price of admission to 17 America’s most popular national parks.  They say that the prices will double or almost triple the current admission fees during peak season."
        })
    ];
    const list = [];
    for(let i = 0;i < 50;i++) {
        list.push(_raw[i%_raw.length]);
    }
    return {
        "list": list
    };
}());