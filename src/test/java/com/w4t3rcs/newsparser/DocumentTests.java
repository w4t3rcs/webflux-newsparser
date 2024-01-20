package com.w4t3rcs.newsparser;

import com.w4t3rcs.newsparser.model.service.parser.DocumentToRankTitleParserService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootTest
class DocumentTests {
    private static Document document;
    private final ApplicationContext applicationContext;

    @Autowired
    public DocumentTests(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @BeforeAll
    public static void init() {
        try {
            Connection connection = Jsoup.connect("https://news.ycombinator.com/");
            document = connection.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test() {
//        System.out.println(document.toString());
        var bean = applicationContext.getBean(DocumentToRankTitleParserService.class);
        bean.parse(document)
                .subscribe(System.out::println);
    }
}
