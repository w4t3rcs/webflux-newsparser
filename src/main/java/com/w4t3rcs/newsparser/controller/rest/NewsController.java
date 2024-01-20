package com.w4t3rcs.newsparser.controller.rest;

import com.w4t3rcs.newsparser.model.common.transformer.Parser;
import lombok.Data;
import org.jsoup.nodes.Document;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Data
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class NewsController {
    private final Document document;
    private final Parser<Flux<Map<String, String>>, Document> reactiveParser;

    @GetMapping
    public Flux<Map<String, String>> getNews() {
        return getParsedToFlux();
    }

    @GetMapping("{id}")
    public Mono<Map<String, String>> getNews(@PathVariable Integer id) {
        return getParsedToFlux().elementAt(id);
    }

    public Flux<Map<String, String>> getParsedToFlux() {
        return reactiveParser.parse(document);
    }
}
