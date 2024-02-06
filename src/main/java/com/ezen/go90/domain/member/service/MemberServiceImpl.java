package com.ezen.go90.domain.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.go90.domain.member.dto.Member;
import com.ezen.go90.domain.member.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @Project final_project_go90
 * @Author 현정환, 주성민
 * @Date 2023. 9. 8.
 */
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper memberMapper;
	
	@Override
	@Transactional
	public void register(Member member) {
		memberMapper.create(member);
	}

	@Override
	@Transactional
	public Member isMember(String id, String passwd, String rank) {
		return memberMapper.findByIdAndPasswd(id, passwd, rank);
	}

	@Override
	@Transactional
	public List<Member> getMemberList() {
		return memberMapper.findByAll();
	}

	@Override
	@Transactional
	public Member getMember(String id) {
		return memberMapper.findById(id);
	}

	@Override
	@Transactional
	public void editMember(Member member) {
		memberMapper.update(member);
	}

	@Override
	@Transactional
	public void secession(String memberId) {
		memberMapper.transfer(memberId);
		memberMapper.delete(memberId);
	}
	
}
