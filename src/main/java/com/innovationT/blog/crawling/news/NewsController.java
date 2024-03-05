package com.innovationT.blog.crawling.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping("/news")
	@ResponseBody
	public Map<String, List<News>> news() throws Exception{
		Map<String, List<News>> map = new HashMap<>();
	    List<News> newsList = newsService.getNewsDatas();
	    map.put("newsList", newsList);
	    return map;
	}
}
