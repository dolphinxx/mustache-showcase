package com.whaleread.showcase.mustache.config;

import com.whaleread.showcase.mustache.web.CategoryHandler;
import com.whaleread.showcase.mustache.web.HomeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by Dolphin on 2017/11/22
 */
@Configuration
public class Routes {
    @Bean
    public RouterFunction<?> homeRouter(HomeHandler handler, HandlerFilterFunction<ServerResponse, ServerResponse> mustacheFilter) {
        return route(path("/").or(path("/index")), handler::index).filter(mustacheFilter);
    }

    @Bean
    public RouterFunction<?> picturesRouter(CategoryHandler handler, HandlerFilterFunction<ServerResponse, ServerResponse> mustacheFilter) {
        return route(path("/pictures").or(path("/pictures/index")), handler::pictures).filter(mustacheFilter);
    }

    @Bean
    public RouterFunction<?> categoryRouter(CategoryHandler handler, HandlerFilterFunction<ServerResponse, ServerResponse> mustacheFilter) {
        return route(path("/{category}").or(path("/{category}/index")), handler::index).filter(mustacheFilter);
    }
}
