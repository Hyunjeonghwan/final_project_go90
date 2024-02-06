package com.ezen.go90.web.myteam.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.go90.domain.vote.dto.SpareMember;
import com.ezen.go90.domain.vote.dto.Vote;
import com.ezen.go90.domain.vote.service.VoteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 팀관리 요청에 대한 세부컨트롤러
 *
 * @author 윤동진
 * @since  2023. 9. 11.
 * @version 1.0
 */
@Controller
@RequestMapping("/myteam")
@RequiredArgsConstructor
@Slf4j
public class MyTeamController {
	
	private final VoteService voteService;
	
	/**
	 * 팀관리 메뉴 클릭 시 요청 처리
	 * @param model 뷰에서필요한 데이터 저장
	 * @return 논리적 뷰이름
	 */
	@GetMapping("")
	public  String prematchList(Model model) {
		List<Vote> voteList = voteService.loadVote();
		model.addAttribute("voteList",voteList);
		return "myteam/prematch";
	}
	/**
	 * 
	 * 설문 목록 클릭시 해당하는 번호의 예상 참여명단 요청 처리
	 * @param model 뷰에서 필요한 데이터 저장
	 * @param voteId 설문번호 식별
	 * @return 논리적 뷰이름
	 */
	 @GetMapping("/management/{voteId}")
	   public String teammanagement(Model model, @PathVariable("voteId") int voteId) {
	      List<SpareMember> spareMember = voteService.loadMember(voteId);
	      Vote vote = voteService.findVoteId(voteId);
	      model.addAttribute("spareMember",spareMember);
	      model.addAttribute("vote",vote);
	      return "myteam/management";
	   }
	/**
	 * 
	 * 자바스크립트에서 비동기 통신을 통한 데이터 받기
	 * @param model 뷰에서 필요한 데이터 저장
	 * @param voteId 설문번호 식별 (js에서 설정 후 요청)
	 * @return 해당하는 데이터 응답
	 */
	@GetMapping("/management/load/{voteId}")
	@ResponseBody
	public List<SpareMember> spareMemberList(Model model ,  @PathVariable("voteId") int voteId){
		 List<SpareMember> spareMembers = voteService.loadMember(voteId);
		return spareMembers;
	}
}
