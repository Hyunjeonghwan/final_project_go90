package com.ezen.go90.domain.vote.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ezen.go90.domain.plan.dto.Plan;
import com.ezen.go90.domain.vote.dto.SpareMember;
import com.ezen.go90.domain.vote.dto.Vote;
import com.ezen.go90.domain.vote.dto.VoteResult;


/**
 * 경기 설문과 관련된 추상메서드 명세
 *
 * @author 현정환
 * @since  2023. 9. 9.
 * @version 1.0
 */
@Mapper
public interface VoteMapper {

   /**  */
   public List<SpareMember> findSpareMember(int voteId);

   /** 설문 목록 반환 */
   public List<Vote> findByAllVote();
   
   //설문번호로 설문 조회
   public Vote findVote(int voteId);
   
   /** 설문 생성 */
   public void create(Vote vote);
   
   /** 설문 삭제 */
   public void delete(int votdId);
   
   /** 설문 수정 */
   public void update(Vote vote);
   
   /** 참가 처리 */
   public void attend(VoteResult voteResult);
   
   /** 일정으로 설문 데이터 전송 */
   public void connectPlan(Vote vote);
   
   /** 참가 취소 처리 */
   public void cancelVote(@Param("memberId") String memberId, @Param("voteId") int voteId);
   
   /** 설문에 따른 참가 회원 목록 반환 */
   public List<VoteResult> findByMemberId(String memberId);
   
}