package com.ezen.go90.domain.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.go90.domain.board.dto.BoardDTO;

/**
 * 
 * Board 테이블 관련 명세
 *
 * @Project final_project_go90
 * @Author 김지연, 장원종
 * @Date 2023. 9. 8.
 */
@Mapper
public interface BoardMapper {
	
	/** 신규 게시판 생성 */
	public void create(BoardDTO board);
	
	/** 전체 게시판 목록 반환 */
	public List<BoardDTO> findAll();
	
	/** 게시판 번호로 해당하는 게시판 반환*/
	public List<BoardDTO> findByBoardId(int boardId);

}
