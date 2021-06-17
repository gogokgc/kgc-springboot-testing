package com.kgc.board.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Transactional
	public int updateHit(Long id) {
		
		return postsRepository.updateHit(id);
	}

	public Page<Posts> findAllPaging(Pageable pageable){
		return postsRepository.findAll(pageable);
	}
	
	@Transactional
	public boolean pageCheck(Pageable pageable) {
		Page<Posts> page = findAllPaging(pageable);
		Boolean check = page.hasNext();
		
		return check;
	}
	
	public List<PostsListResponseDto> findAllDesc(){
		return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
	}

	@Transactional
	public Page<Posts> searchByTitleContent(Pageable pageable, String title, String content){

		return postsRepository.findByContentContainingOrTitleContaining(pageable, title, content);
	}
	
	@Transactional
	public void delete(Long id) {
		
		Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("There is No Posting. id : " + id));
		
		postsRepository.delete(posts);
	}
}