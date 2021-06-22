package com.kgc.board.web;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.kgc.board.service.posts.PostsService;
import com.kgc.board.web.dto.PostsResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class IndexController {
	
	private final PostsService postsService;

	@GetMapping("/")
	public String index(Model model, @PageableDefault(size = 6, sort = "id", direction = Direction.DESC)Pageable pageable) {
		
		model.addAttribute("posts", postsService.findAllPaging(pageable));
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
		model.addAttribute("next", pageable.next().getPageNumber());
		model.addAttribute("pageCheck", postsService.pageCheck(pageable));

		return "index";
	}

	@GetMapping("/posts/search")
	public String search(Model model,@PageableDefault(size = 6, sort = "id", direction = Direction.DESC)Pageable pageable, @RequestParam String keyword) {

		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
		model.addAttribute("next", pageable.next().getPageNumber());
		model.addAttribute("pageCheck", postsService.pageCheck(pageable));
		model.addAttribute("searchResults", postsService.searchByTitleContent(pageable, keyword, keyword));
		model.addAttribute("keyword", keyword);

		return "searchResult";
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

		int hit = postsService.updateHit(id);
		
		PostsResponseDto dto = postsService.findById(id);

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
