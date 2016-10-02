<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>narang 아이디찾기</title>
<style type="text/css">
input.hn_type{
margin: 10px; 
height: 40px; 
width: 300px;
font-size: 20px;
color:#B3B3B3;
}
.hn_font{
font-size:25px;
font-family: "나눔명조";
}
</style>
</head>
<body>

<div align="center" class="font">
<h3 class="hn_font">아이디 찾기</h3>
<form method="post" name="selectPwForm" action="<%=request.getContextPath() %>/Controller?action=selectId" >
<input type="number" class="hn_type hn_font" id="mobile" name="mobile" placeholder="핸드폰번호 (숫자만입력)"><br>
<input type="text" class="hn_type hn_font" id="name" name="name" placeholder="이름"><br><br>
<input type="submit" value="다음" style="width: 100px; height: 50px; " class="hn_font">
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