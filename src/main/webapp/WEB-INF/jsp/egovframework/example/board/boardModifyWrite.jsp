<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"	   uri="http://java.sun.com/jsp/jstl/core" %>    
 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 수정 화면</title>

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


<body>
<form id="frm">

<input type="hidden" name="unq" value="${boardVO.unq }">

<table>
	<caption>게시판 수정 화면</caption>
	<tr>
		<th width="20%"><label for="title">제목</label></th>
		<td width="80%"><input type="text" name="title" id="title" class="input1"
											value="${boardVO.title }"></td> <!--controller에서 boardVO라는 이름으로  데이터 보냄  title이라는 변수안에 데이터 들어감-->
	</tr>
	
	<tr>
		<th><label for="pass">암호</label></th>
		<td><input type="password" name="pass" id="pass"/></td> 
	</tr>
	<tr>
		<th>글쓴이</th>
		<td><input type="text" name="name" id="name" value="${boardVO.name }"></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea name="content" id="content" class="textarea">${boardVO.content }</textarea>
		</td>
	</tr>
	
	<tr>
		<th colspan="2">
		<button type="submit" onclick="fn_submit();return false;">저장</button>
		<button type="reset">취소></button>
		</th>
		
	</tr>
</table>
</form>

</body>


<script>
function fn_submit() {
	
	// trim() -> 앞뒤 공백 제거, java, jsp / javascript는 trim이 없음.
	
	// document.frm.title.value == "" 
	// $("#title").val() == "" ->jquery 내장함수
	// #=id값/ title이라는 id 값을 가지고 있는 요소의 값이 공백과 같으면 이렇게 처리함.
	// $.trim("  aaabbcc  ") == $.trim($("#title").val()) == "" 으로 써야함

	if( $.trim($("#title").val()) == "" ) {  //오류만 체크 문자열 앞뒤로 공백이 있으면 제거/ 오류체크하는이유가  문자열 앞뒤로 공백이 있는 것을 방지
		alert("제목을 입력해주세요.");
		// document.frm.title.focus();
		$("#title").focus(); //focus는 커서를 앞에다 댐
		return false;
	}

	
	$("#title").val( $.trim($("#title").val()) ); //앞뒤 공백을 실제로 제거/ //안써도됨 위에는 오류만 체크한거고 공백이 있을 때 제거하는 거임
	
	if( $.trim($("#pass").val()) == "") { 
		alert("암호를 입력해주세요.");
		$("#pass").focus();
		return false;
	}
	
	$("#pass").val( $.trim($("#pass").val()) ); 
	
	var formData = $("#frm").serialize(); //serialize는 form에 설정하는 함수/ serialize는 form안에 있는 값을 가져옴

	
	// ajax : 비동기 전송방식의 기능을 가지고 있는 jquery의 함수
	//6가지 필수요소
	$.ajax({  
		/*전송 전 세팅 */
		type:"POST", 		//type은 POST타입
		data:formData,		// 전송데이터를 세팅하는 곳 formData
		url:"boardModifySave.do", //url 어디로 전송할건지
		dataType:"text", 	// 리턴 타입 
		
		/* 전송 후 세팅 */
		success: function(result) { // controller에서 준 매개변수로 data가 넘어옴 //function 키워드 반드시 포함 / controller - > 1
			if(result == "1") {	// controller -> "ok","fail" ->fail error로 가는게 아님 sucess로감/ 1이면 저장완료
				alert("저장완료");  //리턴타입에서 주도
				location="boardList.do";
			} 
			
			else if(result == "-1") {
				alert("암호가 일치하지 않습니다.");
			}	
		
			else {
				alert("저장실패\n관리자에게 연락해주세요 .");
			}
		},
		error: function() { 	//	장애발생
			alert("오류발생");	
		}		
	});
	
}

</script>



</html>