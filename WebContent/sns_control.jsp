<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sns.*, member.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="m" class="sns.MessageDTO"></jsp:useBean>
<jsp:useBean id="mDao" class="sns.MessageDAO"></jsp:useBean>
<jsp:useBean id="rDto" class="sns.ReplyDTO"></jsp:useBean>
<jsp:setProperty property="*" name="m"/>
<jsp:setProperty property="*" name="rDto"/>
<%
	String action = request.getParameter("action");
	String cnt = request.getParameter("cnt");
	String suid = request.getParameter("suid");
	
	
	String home;
	int mcnt;
	if((cnt != null) && (suid != null)) {
		home = "sns_control.jsp?action=getall&cnt="+cnt+"&suid="+ suid;
		mcnt = Integer.parseInt(request.getParameter("cnt"));
	} else {
		home = "sns_control.jsp?action=getall";
		mcnt = 5;
	}
	request.setAttribute("curmsg", request.getParameter("curmsg"));
	switch(action) {
	case "newmsg":
		if(mDao.newMsg(m)) {
			response.sendRedirect(home);
		} else {
			throw new Exception("메시지 등록 오류");
		}
		break;
	case "delmsg":
		if(mDao.delMsg(m.getMid())) {
			response.sendRedirect(home);
		} else {
			throw new Exception("메시지 등록 오류");
		}
		break;
	case "newreply":
		if (mDao.newReply(rDto)) {
			pageContext.forward(home);
		} else {
			throw new Exception("댓글 등록 오류");
		}
		break;
	case "delreply":
		if (mDao.delReply(rDto.getRid())) {
			pageContext.forward(home);
		} else {
			throw new Exception("메시지 등록 오류");
		}
		break;
	case "getall":
		ArrayList<MessageSetDTO> dataList = mDao.getAll(mcnt, suid);
		ArrayList<String> nusers = new MemberDAO().getNewMembers();
		request.setAttribute("dataList", dataList);
		request.setAttribute("nusers", nusers);
		request.setAttribute("suid", suid);
		request.setAttribute("cnt", mcnt);
		pageContext.forward("sns_main.jsp");
		break;
	case "fav":
		mDao.favorite(m.getMid());
		pageContext.forward(home);
		break;
	}
%>