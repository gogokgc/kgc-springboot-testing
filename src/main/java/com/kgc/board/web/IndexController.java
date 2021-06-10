package com.kgc.board.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kgc.board.service.posts.PostsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
	
	private final PostsService postsService;

	@GetMapping("/index")
	public String index(Model model) {
		
		model.addAttribute("posts", postsService.findAllDesc());

		return "index";
	}

	@GetMapping("/elements")
	public String elements() {

		return "elements";
	}

	@GetMapping("/generic")
	public String generic() {

		return "generic";
	}
	
	@GetMapping("/update")
	public String update() {
		
		return "update";
	}
}
