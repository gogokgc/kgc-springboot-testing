package com.kgc.board.web.dto;

import com.kgc.board.domain.posts.Posts;

import lombok.Getter;

@Getter
public class PostsResponseDto {

	private Long id;

	private String title;
	
	private String content;
	
	private String author;
	
	private int hit;

	public PostsResponseDto(Posts entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.author = entity.getAuthor();
		this.hit = entity.getHit();
	}
	
}