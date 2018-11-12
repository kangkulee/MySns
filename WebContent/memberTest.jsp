<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.*" %>
<%
	MemberDTO mDto = new MemberDTO();
	MemberDAO mDao = new MemberDAO();
	
	mDto.setUid("kkpark");
	mDto.setName("김강구");
	mDto.setPasswd("java");
	mDto.setEmail("ezen@naver.com");
	mDao.addMember(mDto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>