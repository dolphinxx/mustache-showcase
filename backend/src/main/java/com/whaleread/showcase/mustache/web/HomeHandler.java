package com.whaleread.showcase.mustache.web;

import com.whaleread.showcase.mustache.repository.ArticleRepository;
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
public class HomeHandler {
    private final ArticleRepository articleRepository;

    public HomeHandler(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Mono<ServerResponse> index(ServerRequest request) {
        Map<String, Object> data = new HashMap<>();
        return articleRepository.findAll(20)
                .collectList()
                .doOnNext(list -> data.put("list", list))
                .flatMap(list -> ServerResponse.ok().render("index", data));
    }
}
