package com.innovationT.blog.crawling.news;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class News {
    private String image;
    private String subject;
    private String url;
}
