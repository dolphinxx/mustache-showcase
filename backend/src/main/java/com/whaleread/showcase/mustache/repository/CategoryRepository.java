package com.whaleread.showcase.mustache.repository;

import com.whaleread.showcase.mustache.model.Category;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dolphin on 2017/11/22
 */
@Repository
public class CategoryRepository {
    private Map<String, Category> categories;

    @PostConstruct
    private void init() {
        categories = new HashMap<>();
        categories.put("food", new Category("food", "Food"));
        categories.put("pets", new Category("pets", "Pets"));
        categories.put("beauty_style", new Category("beauty_style", "Beauty & Style"));
        categories.put("living", new Category("living", "Living"));
    }

    public Mono<Category> findById(String id) {
        return Mono.fromCallable(() -> {
            Thread.sleep(10);
            return categories.get(id);
        });
    }
}
