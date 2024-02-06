package com.ezen.go90.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * Board 게시판 컴포넌트
 *
 * @Project final_project_go90
 * @Author 김지연, 장원종
 * @Date 2023. 9. 8.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardDTO {
	
	int boardId;
	String boardName;
	String description;

}
