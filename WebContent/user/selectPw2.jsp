<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>narang 비밀번호 찾기2</title>
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
<script type="text/javascript">
	window.onload = init; 

	function init() {
		document.getElementById("newUpw1").focus();
	}

	function uPwCheck() {
 		if(document.getElementById("newUpw1").value != "" || document.getElementById("newUpw2").value != ""){
			if(document.getElementById("newUpw1").value == document.getElementById("newUpw2").value) {
				document.getElementById("message").style.color="blue"
				document.getElementById("message").innerHTML = "비밀번호가 일치합니다";
				document.getElementById("chengePw").disabled = false;
			} else {
				document.getElementById("message").style.color="red"
				document.getElementById("message").innerHTML = "비밀번호가 일치하지 않습니다";
				document.getElementById("chengePw").disabled = true;
			}
 		}
	}
	
	function check() {
		<%
		String result = request.getParameter("result");
		%>
		var result = <%= result %>
		if( result != 1) {
			alert('변경되었습니다')
			location.replace("index.jsp"); 
		} else {
			alert('변경 실패')
		}
		
	}
		 
	
	
</script>
</head>
<body>
<div align="center" class="hn_font"> 
<% String uId = request.getParameter("uId"); %>
<br><b>새 비밀번호를 입력해 주세요.</b><br>
사용하시던 비밀번호는 알 수 없습니다.<br>
비밀번호를 새로 설정해주세요<br><br><p>
<h3>회원님의 아이디는 <%=uId%> 입니다</h3>
<form method="post" name="selectPwForm" action="<%=request.getContextPath() %>/Controller?action=selectPw2" >
<input type="password" class="hn_type hn_font" id="newUpw1" name="newUpw1" placeholder="새 비밀번호"><br>
<input type="password" class="hn_type hn_font" id="newUpw2" name="newUpw2" placeholder="새 비밀번호 확인" onblur="uPwCheck()"><br>
<div id="message" style="height: 40px;">
<%
String error = request.getParameter("error");
%>
<% if (error != null) { %>
<%=error %>
<%} %>
</div>
<input type="submit" value="변경" style="width: 100px; height: 50px; " class="hn_font" id="chengePw" onclick="check()">
<input type="hidden" id="uId" name="uId" value="<%=uId%>">

</form>
</div>
</body>
</html>