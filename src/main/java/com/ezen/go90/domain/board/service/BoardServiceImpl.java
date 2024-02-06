package com.ezen.go90.domain.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.go90.domain.board.dto.BoardDTO;
import com.ezen.go90.domain.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

/**
 * 
 * 게시판 관련 비즈니스 로직 처리 및 트랜잭션 관리 구현체
 *
 * @Project final_project_go90
 * @Author 김지연, 장원종
 * @Date 2023. 9. 11.
 */
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

   private final BoardMapper boardMapper;
   
   @Override
   @Transactional
   public List<BoardDTO> load() {
      return boardMapper.findAll();
   }
   
   @Override
   @Transactional
   public List<BoardDTO> searchByBoardId(int boardId) {
      List<BoardDTO> list = boardMapper.findByBoardId(boardId);
      return list;
   }


}

