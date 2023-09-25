package com.ezen.go90.web.home.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.go90.domain.board.dto.BoardDTO;
import com.ezen.go90.domain.board.service.BoardService;
import com.ezen.go90.domain.match.dto.Participant;
import com.ezen.go90.domain.match.service.MatchService;

import lombok.RequiredArgsConstructor;

/**
 * 홈페이지 요청을 처리하는 세부 컨트롤러 구현 클래스
 *
 * @author 현정환
 * @since 2023. 9. 6.
 * @version 1.0
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {

//	@GetMapping("")
//	public String home(Model model) {
//		return "index";
//	}

	private final BoardService boardService;
	private final MatchService matchService;

	@GetMapping("")
	public String home(Model model) {
		List<BoardDTO> boardList = boardService.load();
		model.addAttribute("boardList", boardList);
		return "index";
	}

	@GetMapping("/map")
	public String map(Model model) {
		return "board/club/map";
	}

	/** 클럽소개 페이지로 이동 */
	@GetMapping("/club")
	public String club(Model model) {
		List<Participant> match = matchService.loadOnlyMatch();
		model.addAttribute("match", match);
		return "board/club/club";
	}

}
