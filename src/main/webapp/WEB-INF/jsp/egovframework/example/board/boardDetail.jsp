<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"	   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"	   uri="http://java.sun.com/jsp/jstl/functions" %>        
   
 <% pageContext.setAttribute("newline","\n"); %>  
    
 <c:set var="content" value="${fn:replace(boardVO.content,newline,'<br>' ) } "/>   
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 상세 화면</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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

<script>
/*
 * 
 function(){

		$("#title").val("제목입력");
		
	};
 
 */


function fn_submit() {
	
	// trim() -> 앞뒤 공백 제거, java, jsp / javascript는 trim이 없음.
	
	// #=id값/ title이라는 id 값을 가지고 있는 요소의 값이 공백과 같으면 이렇게 처리함.
	
	
	if( $.trim($("#title").val()) == "" ) {
		alert("제목을 입력해주세요.");
		$("#title").focus();
		return false;
	}
	$("#title").val( $.trim($("#title").val()) );

	if( $.trim($("#pass").val()) == "") {
		alert("암호를 입력해주세요.");
		$("#pass").focus();
		return false;
	}
	$("#pass").val( $.trim($("#pass").val()) );
	
	console.log($("#pass").val());
	
	var formData = $("#frm").serialize();
	console.log('!!!!!!!');
	// ajax : 비동기 전송방식의 기능을 가지고 있는 jquery의 함수
	$.ajax({  
		type:"POST",
		data:formData,
		url:"boardWriteSave.do", 
		dataType:"text", 	// 리턴 타입
		/* 전송 후 세팅 */
		success: function(data) { // controller에서 준 매개변수로 data가 넘어옴
			if(data == "ok") {	// controller -> "ok","fail" ->fail error로 가는게 아님 sucess로감
				alert("저장완료");
				location="boardList.do";
			} else {
				alert("저장실패");
			}
		},
		error: function() { 	//	장애발생
			alert("오류발생");	
		}		
	});
	
}




	/*if(document.frm.title.value == ""){
		alert("제목을 입력해주세요!");
		document.frm.title.focus();
		return false;
	
	}

	if(document.frm.pass.value == ""){
		alert("암호를 입력해주세요!");
		document.frm.pass.focus();
		return false;	
	}*/
	
	//document.frm.submit(); // 동기전송방식
	
	


</script>


<body>
<form id="frm">
<table>
	<caption>게시판 상세</caption>
	<tr>
		<th width="20%">제목</th>
		<td width="80%">${boardVO.title }</td> <!--controller에서 boardVO라는 이름으로  데이터 보냄  title이라는 변수안에 데이터 들어감-->
	</tr>
	
	<tr>
		<th>글쓴이</th>
		<td><c:out value="${boardVO.name }"/></td> <!-- 위에 c태그 없으면 사용하지 못함 -->
	</tr>
	<tr>
		<th>내용</th>
		<td height="50"> <!-- aaa  console \n -> <br> 인식-->
		${content }
		</td>
	</tr>
	<tr>
		<th>등록일</th>
		<td>${boardVO.rdate }</td>
	</tr>
	<tr>
		<th colspan="2">
		<button type="button" onclick="location='boardList.do'">목록</button>
		<button type="button" onclick="location='boardModifyWrite.do?unq=${boardVO.unq}'">수정</button>
		<button type="button" onclick="location='passWrite.do?unq=${boardVO.unq}'">삭제</button>
		</th>
		
	</tr>
</table>
</form>

</body>
</html>