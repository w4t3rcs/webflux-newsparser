package com.w4t3rcs.newsparser.config;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class WebConfig {
    @Bean
    public Connection connection() {
        return Jsoup.connect("https://news.ycombinator.com/");
    }

    @Bean
    public Document document(Connection connection) {
        try {
            return connection.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
