package com.whaleread.showcase.mustache.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whaleread.showcase.mustache.model.Picture;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Dolphin on 2017/11/23
 */
@Repository
public class PictureRepository {
    private final ObjectMapper objectMapper;
    private WebClient webClient = WebClient.create();

    public PictureRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Flux<Picture> findAll(int page, int size) {
        return webClient.get()
                .uri("http://huaban.com/search/?q={query}&jacdgcmj&page={page}&per_page={size}&wfl=1", "表情包", page, size)
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders -> {
                    httpHeaders.add("Host", "huaban.com");
                    httpHeaders.add("X-Requested-With", "XMLHttpRequest");
                    httpHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
                })
                .retrieve()
                .bodyToMono(String.class)
                .map(string -> {
                    try {
                        return objectMapper.readTree(string);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMapMany(json -> (Publisher<Picture>) s -> {
                    json.get("pins").forEach(pin -> {
                        JsonNode fileNode = pin.get("file");
                        String thumb = "http://img.hb.aicdn.com/" + fileNode.get("key").asText();
                        long id = pin.get("board_id").asLong();
                        Date createTime = new Date(pin.get("created_at").asLong() * 1000);
                        String source = "http://huaban.com/pins/" + pin.get("pin_id").asText();
                        String title = pin.get("raw_text").asText();
                        int likeCount = pin.get("like_count").asInt();
                        s.onNext(new Picture(id, title, thumb, source, likeCount, createTime));
                    });
                    s.onComplete();
                });
    }
}
