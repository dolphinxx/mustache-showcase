package com.whaleread.showcase.mustache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.RenderingResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by Dolphin on 2017/11/22
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan(basePackages = "com.whaleread.showcase.mustache")
public class Application {
    @Bean
    public HandlerFilterFunction<ServerResponse, ServerResponse> mustacheFilter() {
        return (request, next) -> next.handle(request).flatMap(response -> {
            if (RenderingResponse.class.isAssignableFrom(response.getClass())) {
                return RenderingResponse.from((RenderingResponse) response).modelAttribute("__template_name__", ((RenderingResponse) response).name()).build();
            } else {
                return Mono.just(response);
            }
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
