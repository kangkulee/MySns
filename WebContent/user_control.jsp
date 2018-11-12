<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="mDto" class="member.MemberDTO" />
<jsp:setProperty name="mDto" property="*" />
<jsp:useBean id="mDao" class="member.MemberDAO" />
<%
	String action = request.getParameter("action");
	switch (action) {

	case "new":
		if (mDao.addMember(mDto)) {
			out.println("<script>alert('정상적으로 등록 되었습니다.');opener.window.location.reload();window.close();</script>");
		} else {
			out.println("<script>alert('같은 아이디가 존재 합니다.');history.go(-1);</script>");
		}
		break;
	case "login":
		if (mDao.login(mDto.getUid(), mDto.getPasswd())) {
			session.setAttribute("uid", mDto.getUid());
			response.sendRedirect("sns_control.jsp?action=getall");
		}
		else {
			out.println("<script>alert('아이디나 비밀번호가 틀렸습니다.');history.go(-1);</script>");
		}
		break;
	case "logout":
		session.removeAttribute("uid");
		response.sendRedirect("sns_control.jsp?action=getall");
		break;
	}
%>