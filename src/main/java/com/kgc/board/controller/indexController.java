package com.kgc.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

	@GetMapping("/index")
	public String index() {

		return "index";
	}

	@GetMapping("/elemets")
	public String elements() {

		return "elements";
	}

	@GetMapping("/generic")
	public String generic() {

		return "generic";
	}
}
