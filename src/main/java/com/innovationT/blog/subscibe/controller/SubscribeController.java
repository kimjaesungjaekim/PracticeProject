package com.innovationT.blog.subscibe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subscribe")
public class SubscribeController {
	
	@GetMapping
	public String Subscribe() {
		return "page/subscribe/subscribeHome";
	}

}
