package com.w4t3rcs.newsparser.controller.rest;

import com.w4t3rcs.newsparser.model.common.transformer.Parser;
import com.w4t3rcs.newsparser.model.entity.News;
import lombok.Data;
import org.jsoup.nodes.Document;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@CrossOrigin("http://localhost:8080")
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class NewsController {
    private final Document document;
    private final Parser<Flux<News>, Document> htmlToNewsParser;

    @GetMapping
    public Flux<News> getNews() {
        return getParsedToFlux();
    }

    @GetMapping("{id}")
    public Mono<News> getNews(@PathVariable Integer id) {
        return getParsedToFlux().elementAt(id - 1);
    }

    public Flux<News> getParsedToFlux() {
        return htmlToNewsParser.parse(document);
    }
}
