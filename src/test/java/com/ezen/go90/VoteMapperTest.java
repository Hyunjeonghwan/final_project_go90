package com.ezen.go90;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ezen.go90.domain.member.dto.Member;
import com.ezen.go90.domain.member.dto.MemberSearchCondition;
import com.ezen.go90.domain.member.mapper.MemberMapper;
import com.ezen.go90.domain.vote.dto.Vote;
import com.ezen.go90.domain.vote.mapper.VoteMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class VoteMapperTest {
	
	@Autowired
	private VoteMapper voteMapper;
	
	@Test
	@Disabled
	public void createTest() {
		
		Vote createVote = Vote
				.builder()
				.voteTitle("이번주 경기 참여 조사")
				.voteContent("많은 참여 바랍니다.")
				.build();
		
		voteMapper.create(createVote);
		log.info("투표 생성 완료 : {}", createVote);
	}
	
	@Test
	@Disabled
	public void deleteTest() {
		voteMapper.delete(1);
	}
	
	@Test
	@Disabled
	public void updateTest() {
		
		Vote updatedVote = Vote
				.builder()
				.voteId(1)
				.voteTitle("다음주 경기 참여 조사")
				.voteContent("많은 참여 바랍쇼.")
				.build();
		
		voteMapper.update(updatedVote);
	}
	
	@Test
	@Disabled
	public void listTest() {
		List<Vote> list = voteMapper.findByAllVote();
		for (Vote vote : list) {
			log.info(vote.toString());
		}
	}
	
	

}
