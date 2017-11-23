package com.whaleread.showcase.mustache.repository;

import com.whaleread.showcase.mustache.model.Article;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Dolphin on 2017/11/22
 */
@Repository
public class ArticleRepository {
    private List<Article> articles;

    @PostConstruct
    private void init() {
        articles = new ArrayList<>();
        Article record;
        record = new Article();
        record.setId(1L);
        record.setTitle("8 Tips Transitioning Vegan Plant Based Diet");
        record.setThumb("http://cdn.skim.gs/image/upload/c_fill,f_auto,fl_lossy,h_518,q_auto,w_694/carrots_0");
        record.setCreateTime(new Date(1511311130611L));
        record.setVisits(12332);
        record.setTags(Arrays.asList("food", "health", "living"));
        record.setSource("http://www.sheknows.com/community/health/8-tips-transitioning-vegan-plant-based-diet");
        record.setSummary("Making the transition to a vegan/plant based diet can leave your feeling overwhelmed at times. There are so many questions… Is this good? Is that bad? Why not this? How come that? It’s a whirlwind of questions and information that can make your head ache and get on your very last nerve. It can leave you feeling uncertain, frustrated and it can even make you feel like giving up!");
        articles.add(record);
        record = new Article();
        record.setId(2L);
        record.setTitle("HOW TO PLAN AND MAKE A VIRAL TIMELAPSE VIDEO");
        record.setThumb("https://23527-presscdn-pagely.netdna-ssl.com/wp-content/uploads/2017/11/nathaniel_dodson.jpg");
        record.setCreateTime(new Date(1511311134611L));
        record.setVisits(891);
        record.setTags(Arrays.asList("cinematography", "filmmaking", "Nathaniel Dodson", "timelapse", "timelapse tutorial", "timelapse video", "Tutvid", "Video", "Viral"));
        record.setSource("https://www.diyphotography.net/plan-make-viral-timelapse-video/");
        record.setSummary("With so many timelapse films being created now, it can be difficult to make yours stand out. But those that do go viral often do not do so all by themselves. There’s a lot of time and planning that goes into them before the first frame is even created. Then there’s more effort that goes into their promotion after they get published.");
        articles.add(record);
        record = new Article();
        record.setId(3L);
        record.setTitle("THREE TECHNIQUES TO CREATE DRAMATIC BLACK & WHITE IMAGES");
        record.setThumb("https://23527-presscdn-pagely.netdna-ssl.com/wp-content/uploads/2017/11/dramatic_black_and_white-745x419.jpg");
        record.setCreateTime(new Date(1511311140611L));
        record.setVisits(55443);
        record.setTags(Arrays.asList("Black & White", "Blake Rudis", "colour", "f64 Academy", "photoshop", "tutorial"));
        record.setSource("https://www.diyphotography.net/three-techniques-create-dramatic-black-white-images/");
        record.setSummary("Making the transition to a vegan/plant based diet can leave your feeling overwhelmed at times. There are so many questions… Is this good? Is that bad? Why not this? How come that? It’s a whirlwind of questions and information that can make your head ache and get on your very last nerve. It can leave you feeling uncertain, frustrated and it can even make you feel like giving up!");
        articles.add(record);
        record = new Article();
        record.setId(4L);
        record.setTitle("NATIONAL PARK ADMISSION PRICES MAY INCREASE UP TO $70 IN THE NEW YEAR");
        record.setThumb("https://23527-presscdn-pagely.netdna-ssl.com/wp-content/uploads/2017/11/national_parks-745x419.jpg");
        record.setCreateTime(new Date(1511311930611L));
        record.setVisits(66443);
        record.setTags(Arrays.asList("Department of the Interior", "government", "National Park", "National Park Service"));
        record.setSource("https://www.diyphotography.net/national-park-admission-prices-increase-70-new-year/");
        record.setSummary("According to BRProud, the US Department of the Interior is proposing to double or almost triple the price of admission to 17 America’s most popular national parks.  They say that the prices will double or almost triple the current admission fees during peak season.");
        articles.add(record);
    }

    public Flux<Article> findByCategoryId(String categoryId, int size) {
        return Flux.interval(Duration.ofMillis(10)).map(tick -> articles.get(tick.intValue() % articles.size())).take(size);
    }

    public Flux<Article> findAll(int size) {
        return Flux.interval(Duration.ofMillis(10)).map(tick -> articles.get(tick.intValue() % articles.size())).take(size);
    }
}
