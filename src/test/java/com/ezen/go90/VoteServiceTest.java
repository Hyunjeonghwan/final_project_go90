package com.ezen.go90;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ezen.go90.domain.vote.dto.SpareMember;
import com.ezen.go90.domain.vote.dto.Vote;
import com.ezen.go90.domain.vote.service.VoteService;

import lombok.extern.slf4j.Slf4j;

/**
 * 설문기능 테스트 클래스
 *
 * @author 윤동진
 * @since  2023. 9. 9.
 * @version 1.0
 */
@SpringBootTest
@Slf4j
public class VoteServiceTest {

   @Autowired
   private VoteService voteService;
   
   @Test
   @DisplayName("예상 출전선수 명단 메서드")
//      @Disabled
   public void loadMemberTest() {
      List<SpareMember> list = voteService.loadMember(1);
      log.info("예상 출전명단 : {}",list);
   }
   
   @Test
   @DisplayName("전체 설문 리스트 출력")
   @Disabled
   public void loadVoteTest(){
      List<Vote> list = voteService.loadVote();
      log.info("전체 설문 리스트 : {}",list);
   }
   
   
}