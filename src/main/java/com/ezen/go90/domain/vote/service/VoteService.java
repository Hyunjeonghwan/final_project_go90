package com.ezen.go90.domain.vote.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.go90.domain.plan.dto.Plan;
import com.ezen.go90.domain.vote.dto.SpareMember;
import com.ezen.go90.domain.vote.dto.Vote;
import com.ezen.go90.domain.vote.dto.VoteResult;

/**
 * 설문 관련한 비즈니스코드 및 트랜젝션처리를 위한 명세
 *
 * @author 현정환
 * @since  2023. 9. 9.
 * @version 1.0
 */
public interface VoteService {
   public List<SpareMember> loadMember(int voteId);
   
   public List<Vote> loadVote();
   
   public void registVote(Vote vote);
   
   public void doVote(VoteResult voteResult);
   
   public void connectPlan(Vote vote);
   
   public void cancelVote(String memberId, int voteId);
   
   public List<VoteResult> findVoteByMemberId(String memberId);
   
//   public VoteResult findVoteMember(String memberId, int voteId);
   
   public Vote findVoteId(int voteId);
}