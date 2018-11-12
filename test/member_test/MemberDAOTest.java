package member_test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import member.MemberDAO;
import member.MemberDTO;

public class MemberDAOTest {

	@Test
	public void addMemberTest() {
		MemberDTO mDto = new MemberDTO();
		MemberDAO mDao = new MemberDAO();
		Date date = new Date();
		
		mDto.setUid("kklee");
		mDto.setName("¿Ã∞≠±∏");
		mDto.setPasswd("java");
		mDto.setEmail("ezen@naver.com");
		mDto.setDate(date);
		assertTrue(mDao.addMember(mDto));
	}

}
