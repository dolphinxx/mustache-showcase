jQuery(function ($) {
    $('.column-container').each(function () {
        const el = $(this);
        const container = $(this).closest('.container');
        $(window).on('resize', function () {
            const containerWidth = container.width();
            let columnWidth;
            if (containerWidth < 510) {
                columnWidth = containerWidth;
            } else if (containerWidth < 690) {
                columnWidth = containerWidth / 1;
            } else if (containerWidth < 942) {
                columnWidth = containerWidth / 2;
            } else if (containerWidth < 1122) {
                columnWidth = containerWidth / 3;
            } else {// 1122
                columnWidth = containerWidth / 4;
            }
            el.masonry({
                itemSelector: '.figure-wrapper',
                columnWidth
            });
        }).trigger('resize');
    });

});