package com.w4t3rcs.newsparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: @w4t3rcs
 * <a href="https://news.ycombinator.com/">Hacker News</a> parser to REST API
 **/

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
