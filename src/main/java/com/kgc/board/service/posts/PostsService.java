package com.kgc.board.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgc.board.domain.posts.Posts;
import com.kgc.board.domain.posts.PostsRepository;
import com.kgc.board.web.dto.PostsListResponseDto;
import com.kgc.board.web.dto.PostsResponseDto;
import com.kgc.board.web.dto.PostsSaveRequestDto;
import com.kgc.board.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {

	private final PostsRepository postsRepository;
	
	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		
		Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("There is No Posting. id : " + id)); 
		
		posts.update(requestDto.getTitle(),	requestDto.getContent());
		
		return id;
	}

	public PostsResponseDto findById(Long id) {
		
		Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("There is No Posting. id : " + id));
		
		return new PostsResponseDto(entity);
	}

	
	public List<PostsListResponseDto> findAllDesc(){
		return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
	}
	
	@Transactional
	public void delete(Long id) {
		
		Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("There is No Posting. id : " + id));
		
		postsRepository.delete(posts);
	}
}