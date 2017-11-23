package com.whaleread.showcase.mustache.web;

import com.whaleread.showcase.mustache.model.Category;
import com.whaleread.showcase.mustache.repository.ArticleRepository;
import com.whaleread.showcase.mustache.repository.CategoryRepository;
import com.whaleread.showcase.mustache.repository.PictureRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dolphin on 2017/11/22
 */
@Component
public class CategoryHandler {
    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;
    private final PictureRepository pictureRepository;

    public CategoryHandler(CategoryRepository categoryRepository, ArticleRepository articleRepository, PictureRepository pictureRepository) {
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
        this.pictureRepository = pictureRepository;
    }

    public Mono<ServerResponse> index(ServerRequest request) {
        String categoryId = request.pathVariable("category");
        final Map<String, Object> data = new HashMap<>();
        return categoryRepository.findById(categoryId)
                .doOnNext(record -> data.put("record", record))
                .flux()
                .flatMap(record -> articleRepository.findByCategoryId(record.getId(), 10))
                .collectList()
                .doOnNext(list -> data.put("list", list))
                .flatMap(list -> ServerResponse.ok().render("category/index", data));
    }

    public Mono<ServerResponse> pictures(ServerRequest request) {
        final Map<String, Object> data = new HashMap<>();
        data.put("record", new Category("pictures", "Pictures"));
        return pictureRepository.findAll(0, 20)
                .collectList()
                .doOnNext(list -> data.put("list", list))
                .flatMap(list -> ServerResponse.ok().render("category/pictures", data));
    }
}
