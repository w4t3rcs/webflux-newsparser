package com.w4t3rcs.newsparser.model.service.parser;

import com.w4t3rcs.newsparser.model.common.transformer.Parser;
import lombok.Data;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
public class DocumentToRankTitleParserService implements Parser<Flux<Map<String, String>>, Document> {
    @Override
    public Flux<Map<String, String>> parse(Document document) {
        Elements start = document.getElementsByClass("athing");
        return Flux.fromStream(start.stream())
                .map(Element::children)
                .map(elements -> Map.of(Optional.ofNullable(elements.first()), Optional.ofNullable(elements.last()))
                        .entrySet()
                        .stream()
                        .map(entry -> {
                            String key = entry.getKey().orElseThrow().text();
                            String value = entry.getValue().orElseThrow().text();
                            return Map.entry(key, value);
                        })
                        .collect(Collectors.toMap(Object::toString, Object::toString))
                );
    }
}
