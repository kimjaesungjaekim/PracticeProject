package com.innovationT.blog.crawling.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    private static String News_URL = "https://news.nate.com/total";

    @PostConstruct
    public List<News> getNewsDatas() throws IOException {
        List<News> newsList = new ArrayList<>();
        Document document = Jsoup.connect(News_URL).get();

        Elements contents = document.select("div ul.coverStory li");

        for (Element content : contents) {
            News news = News.builder()
                    .image(content.select("a img").attr("abs:src")) // 이미지
                    .subject(content.select("a h2.subject").text())		// 제목
                    .url(content.select("a").attr("abs:href"))		// 링크
                    .build();
            newsList.add(news);
        }    
        return newsList;
    }
}