package com.ezen.go90;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ezen.go90.domain.member.dao.MemberDao;
import com.ezen.go90.domain.member.dto.Member;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberDaoTest {
	
	@Autowired
	private MemberDao memberDao;
	
	@Test
	@Disabled
	public void getCountTest() {
		int count = memberDao.getCount();
		log.info("회원수 : {}", count);
	}
	
	@Test
//	@Disabled
	public void findByAllTest() {
		List<Member> list = memberDao.findByAll();
		for (Member member : list) {
			log.info(member.toString());
		}
	}
	
	@Test
//	@Disabled
	public void findByIdTest() {
		String memberId = "bangry";
		Member member = memberDao.findById(memberId);
		log.info("회원정보 : {}", member.toString());
	}
	
}
