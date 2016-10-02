<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript">
	function loginFormform() {
		var form = document.loginForm;
		
		if(!form.uId.value) {
			alert('아이디를 입력해주세요')
			form.uId.focus();
			return;
		}
		
		if(!form.uPw.value) {
			alert('비밀번호를 입력해주세요')
			form.uPw.focus();
			return;
		}
		form.submit();
	}
</script>
<title>narang login</title>
<link rel="stylesheet" href="<%=application.getContextPath() %>/css/index.css">
<style type="text/css">
input.hn_type{
margin: 10px; 
height: 70px; 
width: 500px;
font-size: 30px;
color:#B3B3B3;
}
.hn_font{
font-family: "나눔명조";
}
</style>

</head>
<body>
<div class="ibody" align="center">
<h1 class="title"> <img src="<%=application.getContextPath() %>/image/logos.png" style="width:25%;margin-top:-20px;"> </h1>
</div>

<div align="center" class="hn_font">

<form method="post" action="<%=application.getContextPath() %>/Controller?action=login" name="loginForm">
<table align="center">
<tr>
	<td colspan="2" align="center">
	<input type="text" class="hn_type hn_font" placeholder="아이디" name="uId" id="uId">
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
	<input type="password" class="hn_type hn_font" placeholder="비밀번호" name="uPw" id="uPw">
	</td>
</tr>
<tr>
	<td  colspan="2" style="font-size: 20px;">
<!-- 	&nbsp;&nbsp;&nbsp;<input type="checkbox" value="로그인상태 유지" >로그인상태 유지 -->
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
	<input type="button" value="로그인" class="hn_type hn_font" style="color:white ; background-color: #FF7C80; height: 60px;" onclick="loginFormform()">
	<br><br> 
	
	</td>
</tr>
<tr >
	<td align="center">
	<a id="kakao-login-btn"></a>
<!-- <input type="button" value="카카오톡로그아웃" onclick="kakaologout()"> -->
<script type='text/javascript'>
  //<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('f88da61a4fc18726121bb69d202dfb17');
    // 카카오 로그인 버튼을 생성합니다.
    Kakao.Auth.createLoginButton({
      container: '#kakao-login-btn',
      success: function(authObj) {
        // 로그인 성공시, API를 호출합니다.
        Kakao.API.request({
          url: '/v1/user/me',
          success: function(res) {
            console.log(res.id)
            console.log(res.properties.nickname)
            console.log(res.properties.profile_image)
            alert(res.id)
            
            window.location.href = 'Controller?action=kakaoDupliUser&uId=' + res.id
            		+'&name=' + res.properties.nickname
            		+'&uImg=' + res.properties.profile_image;
          
          },
          fail: function(error) {
            alert(JSON.stringify(error));
          }
        });
      },
      fail: function(err) {
        alert(JSON.stringify(err));
      }
    });
    
    function kakaologout(){
    	Kakao.Auth.logout(function() { console.log("logged out."); });
    }
  //]]>
</script>
	</td>
	
	<td align="center" style="margin-bottom: 100px">
	<div id="naver_id_login"></div>
<script type="text/javascript">
	var naver_id_login = new naver_id_login("NAYZo8oqBDu6m24mXbtK","http://localhost:8090/narang/login.jsp");
	var state = naver_id_login.getUniqState();
	naver_id_login.setButton("white",2.5,50);
	naver_id_login.setDomain(".service.com");
	naver_id_login.setState(state);
	naver_id_login.setPopup();
	naver_id_login.init_naver_id_login();
	// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	function naverSignInCallback() {
		window.location.href = 'Controller?action=naverDupliUser&uId=' + naver_id_login.getProfileData('id')
							+'&email=' + naver_id_login.getProfileData('email')
							+'&name=' + encodeURI(naver_id_login.getProfileData('nickname'),"UTF-8")
							+'&age=' + naver_id_login.getProfileData('age')
							+'&gender=' + naver_id_login.getProfileData('gender')
							+'&uImg=' + naver_id_login.getProfileData('profile_image');
	}

	// 네이버 사용자 프로필 조회
	naver_id_login.get_naver_userprofile("naverSignInCallback()");
</script>
	</td>
</tr>
<tr>
	<td colspan="2" style="font-size: 20px; color:black">
	<br><br>
	아이디를 잊으셨나요? <a href="selectId.jsp">아이디 찾기</a> <p>
	비밀번호를 잊으셨나요? <a href="selectPw.jsp">비밀번호 찾기</a><p>
	계정이 없으신가요? <a href="join.jsp">가입하기</a>
	</td>
</tr>

</table>
</form>
<p>

</div>
</body>
</html>