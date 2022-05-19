<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl 연결하는내용 -->    
<%@ taglib prefix="c"	   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"	   uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<style>
body {
	font-size:9pt;
}
button {
	font-size:9pt;
}
table {
	width:600px;
	border-collapse:collapse;
}
th,td {
	border:1px solid #cccccc;
	padding:3px;
}
.input1{
	width:98%;
}
.textarea {
	width:98%;
	height:70px;
}
</style>

<body>
<!-- 번호, 제목, 글쓴이, 등록일, 조회수 -->
<table>
	<caption>
	<div>일반게시판 목록</div>
	</caption>
	<tr>
		<th width="45%">제목</th>
		<th width="15%">글쓴이</th>
		<th width="15%">등록일</th>
		<th width="15%">조회수</th>	
	</tr>
	 <!-- forEach는 반복문/var는 내부에서사용할 변수 설정/ 그리고 items는 외부데이터 받는 속성값 설정 -->
	 
	 
	<c:forEach var="result" items="${resultList }">	

		<tr align="center">
			<td align="Left">
				<a href="boardDetail.do?unq=${result.unq }"><c:out value="${result.title }"/></a>
			</td>
			<td><c:out value="${result.name }" /></td>
			<td><c:out value="${result.rdate }" /></td>
			<td><c:out value="${result.hits }" /></td>
		</tr>
		

	</c:forEach>
		
</table>
<!-- 페이지 번호 -->
<div style="width:600px;margin-top:5px; text-align:center; ">
	
	
	
</div>

<div style="width:600px;margin-top:5px; text-align:right; ">
	<button type="button" onclick="location='boardWrite.do'">글쓰기</button>
</div>



</body>
</html>









