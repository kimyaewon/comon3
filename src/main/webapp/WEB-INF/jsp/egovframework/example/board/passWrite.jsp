<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"	   uri="http://java.sun.com/jsp/jstl/core" %>    




    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>암호입력화면</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>

<script>
//기본모양 -> 암호일치검사/공백검사
$(function(){
	
	$("#delBtn").click(function(){   //id값을 가지고 있는 요소를 클릭했을 때 /이 함수에 뭔가 기능구현을 하려면 function을 반드시 써줘야함
		
		var pass = $("#pass").val();	//pass라는 변수가 값을 갖게됨 아래에 집어넣음
		pass = $.trim(pass);			//trim으로 pass를 감쌈 / pass라는 변수에게 변수는 화면으로부터  데이터값을 얻게되는데 앞뒤 공백 제거까지 당함
		if(pass == "") {				//pass가 공백과 같은가
			alert("암호를 입력해주세요.");
			$("#pass").focus();			//id(pass)에 커서가 간다
			return false;
		}
	
		//json type 설정
		var sendData = "unq=${unq}&pass="+pass; 
		console.log(sendData)
		//unq와 pass 삭제하는 곳에 보낼거임 ajax
		$.ajax({  
			/*전송 전 세팅 */
			type:"POST", 		//type은 POST타입
			data:sendData,		// 전송데이터를 세팅하는 곳 formData // 두개의 데이터타입이 전송이됨 -> json설정
			url:"boardDelete.do", //url 어디로 전송할건지
			dataType:"text", 	// 리턴 타입 
			
			/* 전송 후 세팅 */
			success: function(result) { // controller에서 준 매개변수로 data가 넘어옴 //function 키워드 반드시 포함 / controller - > 1
				if(result == "1") {	// controller -> "ok","fail" ->fail error로 가는게 아님 sucess로감/ 1이면 저장완료
					alert("삭제완료");  //리턴타입에서 주도
					location="boardList.do";
				} 
				
				else if(result == "-1") {		//1이나 -1이 떠야함 그게아니면 비정상
					alert("암호가 일치하지 않습니다.");
				}	
			
				else {
					alert("삭제실패\n관리자에게 연락해주세요 .");
				}
			},
			error: function() { 	//	장애발생
				alert("오류발생");	
			}		
		});	
	}); 	
});


</script>

<body>

<table>
	<tr>
		<th>암호입력</th>
		<td><input type="password" id="pass"></td>
		<td><button type="button" id="delBtn">삭제하기</button></td>
	</tr>
</table>

</body>
</html>