package com.kgc.board.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kgc.board.service.posts.PostsService;
import com.kgc.board.web.dto.PostsResponseDto;

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
	
	@GetMapping("/posts/save")
	public String postsSave(Model model) {
		
		return "postsSave";
	}
	
	@GetMapping("/posts/update/{id}")
	public String postsUpdate(@PathVariable Long id, Model model) {
		
		PostsResponseDto dto = postsService.findById(id);
		model.addAttribute("post", dto);
		
		return "postsUpdate";
	}
	
	@GetMapping("/posts/detail/{id}")
	public String postsDetail(@PathVariable Long id, Model model) {
		
		PostsResponseDto dto = postsService.findById(id);
		
		int hit = postsService.updateHit(id);

		if(hit == 1) {
		model.addAttribute("post", dto);
		
		return "postsDetail";
		}else {
			return null;
		}
		
		
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
