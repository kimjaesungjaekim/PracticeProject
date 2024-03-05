package com.innovationT.blog.board.controller;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {
	
	@GetMapping("/boardList")
	public String boardListPage() {
		return "/board/boardList";
	}
	
}
