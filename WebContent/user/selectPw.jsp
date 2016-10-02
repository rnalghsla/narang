<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>naran 비밀번호 찾기</title>
<style type="text/css">
input.hn_type{
margin: 10px; 
height: 40px; 
width: 250px;
font-size: 30px;
color:#B3B3B3;
}
.hn_font{
font-size:25px;
font-family: "나눔명조";
}
</style>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
	window.onload = init; 

	function init() {
		document.getElementById("uId").focus();
	}

	function loginFormform() {
		var form = document.selectPwForm;
		
		if(!form.uId.value) {
			alert('아이디를 입력해주세요')
			form.uId.focus();
			return;
		}
		
		if(!form.name.value) {
			alert('이름을 입력해주세요')
			form.name.focus();
			return;
		}
		form.submit();
	}
</script>
</head>
<body >

<div align="center" class="hn_font" >
<h3 class="hn_font">비밀번호 찾기</h3>
<form method="post" name="selectPwForm" action="<%=request.getContextPath() %>/Controller?action=selectPw" >
<input type="text" class="hn_type hn_font" id="uId" name="uId" placeholder="아이디"><br>
<input type="text" class="hn_type hn_font" id="name" name="name" placeholder="이름"><br><br>
<input type="button" value="다음" style="width: 100px; height: 50px; " class="hn_font" onclick="loginFormform()">
<div>
<%
String error = request.getParameter("error");
%>
<% if (error != null) { %>
<%=error %>
<%} %>
</div>
</form>
</div>
</body>
</html>