package com.w4t3rcs.newsparser.model.service.parser;

import com.w4t3rcs.newsparser.controller.rest.NewsController;
import com.w4t3rcs.newsparser.model.common.transformer.Parser;
import com.w4t3rcs.newsparser.model.entity.News;
import lombok.Data;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@Service
public class ElementsToNewsEntityParserService implements Parser<News, Elements> {
    @Override
    public News parse(Elements elements) {
        News news = News.builder()
                .rank(Objects.requireNonNull(elements.first()).text().split("[.]")[0])
                .title(Objects.requireNonNull(elements.last()).text())
                .build()
                .add();

        news.add(linkTo(NewsController.class).slash(news.getRank()).withSelfRel());
        return news;
    }
}
