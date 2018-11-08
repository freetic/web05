<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%!
	public static class MyMember{
		int no;
		String name;
		
		public int getNo(){
			return this.no;
		}
		public void setNo(int no){
			this.no = no;
		}
		public String getName(){
			return this.name;
		}
		public void setName(String name){
			this.name = name;
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl 테스트</title>
</head>
<body>
	<h2>c:out 테스트</h2>
	1) <c:out value="안녕하세요!"/><br>
	2) <c:out value="${null}">반갑습니다.</c:out><br>
	3) <c:out value="안녕하세요!">반갑습니다.</c:out><br>
	4) <c:out value="${null }"/><br><br>
	<h2>값 설정 방식</h2>
	<c:set var="username1" value="홍길동"/>
	<c:set var="username2">임꺽정</c:set>
	1) ${username1 }<br>
	2) ${username2 }<br><br>
	<h3>기본 보관소 - page</h3>				<!-- 보관소 지정 안하면 JspContext(page)에 저장 -->
	3) ${pageScope.username1 }<br>
	4) ${requestScope.username2 }<br><br>
	<h3>보관소 지정 - scope</h3>
	<c:set var="username3" scope="request">일지매</c:set>
	5) ${pageScope.username3 }<br>
	6) ${requestScope.username3 }<br><br>
	<h3>기존의 값 덮어씀</h3>
	<% pageContext.setAttribute("username4", "똘이장군"); %>
	7) 기존 값=${username4 }<br>
	<c:set var="username4" value="주먹대장"/>
	8) 덮어 쓴 값=${username4 }<br><br>
<%
	MyMember member = new MyMember();
	member.setNo(100);
	member.setName("홍길동");
	pageContext.setAttribute("member", member);
%>
	9) ${member.name}<br>
	<c:set target="${member}" property="name" value="임꺽정"/>
	10) ${member.name }<br><br>
	<h2>보관소에 저장된 값 제거</h2>
	1) ${username1 }<br>
	<c:remove var="username1"/>
	2) ${username1 }<br><br>
	

</body>
</html>