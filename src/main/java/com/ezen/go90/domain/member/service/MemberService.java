package com.ezen.go90.domain.member.service;

import java.util.List;

import com.ezen.go90.domain.member.dto.Member;

/**
 * 
 * @Project final_project_go90
 * @Author 현정환, 주성민
 * @Date 2023. 9. 8.
 */
public interface MemberService {
	
	public void register(Member member);
	public Member isMember(String id, String passwd, String rank);
	public List<Member> getMemberList();
	public Member getMember(String id);
	public void editMember(Member member);
	public void secession(String memberId);

}
