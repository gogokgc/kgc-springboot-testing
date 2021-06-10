package com.kgc.board.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들도 칼럼으로 인식하도록 해준다.
@EntityListeners(AuditingEntityListener.class) // Auditing 기능 추가(Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능)
public class BaseTimeEntity { // Posts 에서 상속받을수있도록 설정해준다.

	@CreatedDate
	private LocalDateTime createDate;
	
	@LastModifiedDate
	private LocalDateTime modifiedTime;
}