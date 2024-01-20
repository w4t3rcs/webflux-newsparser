package com.w4t3rcs.newsparser.model.service.parser;

import com.w4t3rcs.newsparser.model.common.transformer.Parser;
import com.w4t3rcs.newsparser.model.entity.News;
import lombok.Data;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Data
@Service
public class HTMLToNewsEntityParserService implements Parser<Flux<News>, Document> {
    private final Parser<News, Elements> elementsToNewsEntityParser;

    @Override
    public Flux<News> parse(Document document) {
        Elements start = document.getElementsByClass("athing");
        return Flux.fromIterable(start)
                .map(Element::children)
                .map(elementsToNewsEntityParser::parse);
    }
}
