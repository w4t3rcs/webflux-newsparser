package com.w4t3rcs.newsparser.model.entity;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News extends RepresentationModel<News> {
    private String rank;
    private String title;
}
