package com.ezen.go90.domain.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.go90.domain.board.dto.BoardDTO;

/**
 * 
 * 게시판 관련 비즈니스 로직 및 트랜젝션 처리
 *
 * @Project final_project_go90
 * @Author 장원종, 김지연
 * @Date 2023. 9. 11.
 */
@Service
public interface BoardService {

	/** 게시판 목록 반환 */
	public List<BoardDTO> load();

	/** BoardId로 조회 */
	public List<BoardDTO> searchByBoardId(int boardId);
	
}
