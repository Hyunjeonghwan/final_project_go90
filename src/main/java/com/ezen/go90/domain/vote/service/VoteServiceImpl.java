package com.ezen.go90.domain.vote.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.go90.domain.vote.dto.SpareMember;
import com.ezen.go90.domain.vote.dto.Vote;
import com.ezen.go90.domain.vote.dto.VoteResult;
import com.ezen.go90.domain.vote.mapper.VoteMapper;

import lombok.RequiredArgsConstructor;

/**
 * 설문 관련 비즈니스 코드 및 트랜젝션 처리 구현체
 *
 * @author 현정환
 * @since 2023. 9. 9.
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

	private final VoteMapper voteMapper;

	@Override
	@Transactional
	public List<SpareMember> loadMember(int voteId) {
		return voteMapper.findSpareMember(voteId);
	}

	@Override
	@Transactional
	public List<Vote> loadVote() {
		return voteMapper.findByAllVote();
	}

	@Override
	@Transactional
	public void registVote(Vote vote) {
		voteMapper.create(vote);
	}

	@Override
	@Transactional
	public void doVote(VoteResult voteResult) {
		voteMapper.attend(voteResult);
	}

	@Override
	@Transactional
	public List<VoteResult> findVoteByMemberId(String memberId) {
		return voteMapper.findByMemberId(memberId);
	}

//	@Override
//	public VoteResult findVoteMember(String memberId, int voteId) {
//		return voteMapper.findVoteMember(memberId, voteId);
//	}

	@Override
	@Transactional
	public void connectPlan(Vote vote) {
		voteMapper.connectPlan(vote);
	}

	@Override
	@Transactional
	public void cancelVote(String memberId, int voteId) {
		voteMapper.cancelVote(memberId, voteId);
	}

	@Override
	public Vote findVoteId(int voteId) {
		Vote vote = voteMapper.findVote(voteId);
		return vote;
	}

}