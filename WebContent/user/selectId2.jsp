<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>narang 아이디찾기2</title>
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
<div align="center" class="hn_font">
<% String uId = (String)request.getAttribute("uId"); %>
<h3>회원님의 아이디는 <%=uId%> 입니다</h3>
<br><br>
비밀번호를 잊으셨나요? <a href="selectPw.jsp">비밀번호 찾기</a><p>
메인으로 가기 <a href="../index.jsp">메인</a><p> 
로그인하러 가기<a href="login.jsp">로그인</a>
</div>
</body>
</html>