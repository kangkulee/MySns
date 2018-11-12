package member;

import static org.junit.Assert.assertTrue;

import java.util.Date;

public class MemberTest {

	public static void main(String[] args) {
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
