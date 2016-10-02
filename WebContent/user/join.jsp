<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>narang 회원가입</title>
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
sapn.red{
color:red}
</style>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<!-- ajax.js 자바스크립트 라이브러리 설정 -->
<script type="text/javascript" src="../js/ajax.js"></script>
<script type="text/javascript">

	window.onload = init; 
	
	function init() {
 		document.getElementById("uId").focus();
 	}
	
	//비밀번호 도움말 메세지 출력
 	function showUpw() {
//  		var help="영문자 시작,숫자/특수문자 조합 16자리사용";
//  		document.getElementById('messageUpw').innerHTML = help;
 	}
 	
 	/*  비밀번호 표시
		비밀번호와 비밀번호 확인 보이이기/ 감추기
	*/
	function checkboxUpw() {
		if(document.getElementById("isShow").checked) {
			document.getElementById("uPw").type="text";
			document.getElementById("uPwConfirm").type="text";
		} else {
			document.getElementById("uPw").type="password";
			document.getElementById("uPwConfirm").type="password";
		}
	}
 	
 	/*비밀번호 확인 일치, 불일치
	  --키업, 메세지 출력
	*/
	function uPwCheck() {
 		if(document.getElementById("uPw").value != "" || document.getElementById("uPwConfirm").value != "")
		if(document.getElementById("uPw").value == document.getElementById("uPwConfirm").value) {
			document.getElementById("messageUpwConfirm").style.color="blue"
			document.getElementById("messageUpwConfirm").innerHTML = "비밀번호가 일치합니다";
			document.getElementById("btnJoin").disabled = false;
		} else {
			document.getElementById("messageUpwConfirm").style.color="red"
			document.getElementById("messageUpwConfirm").innerHTML = "비밀번호가 일치하지 않습니다";
			document.getElementById("btnJoin").disabled = true;
		}
	}
	
 	//패스워드 확인 포커스 잃었을 때 메시지 안보임
 	function uPwConfirmOnblur() {
 		document.getElementById("messageUpwConfirm").innerHTML = "";
 	}

 	//패스워드 포커스 잃었을 때 메시지 안보임
 	function uPwOnblur() {
 		document.getElementById("messageUpw").innerHTML = "";
 	}
 	
    function imgRefresh(){
        $("#captchaImg").attr("src", "<%=request.getContextPath() %>/captcha?action=hani&id=" + Math.random());
    }
    
    function secu() {
		var url = "../captcha";
 		var answer = document.getElementById("answer").value;
 		var params = "";
 		params += "action=mc"
 		params += "&answer=" + answer;
 		
 		//응답 데이터 타입이 plain text
 		params += "&responseType=text";
 		var callback = responseText;

 		
 		var method = "GET";
 		
 		//js/ajax.js 스크립트 이용해서 ajax 서버 요청
 		new ajax.xhr.Request(url, params, callback, method);
    }
    
 	function responseText(xhr) {
 		if(xhr.readyState == 4 && xhr.status == 200) {
 			var result = xhr.responseText;
 			if(result == 'true') {
 				document.getElementById("answerMessage").style.color = "blue";
 				document.getElementById("answerMessage").innerHTML = "일치";
 			} else if(result == 'false'){
 				document.getElementById("answerMessage").style.color = "red";
 				document.getElementById("answerMessage").innerHTML = "불일치";
 			} else if(result == 'required') {
 				document.getElementById("answerMessage").innerHTML = "미입력";
 			} 
 		} 
 	}
 	
 	function btnJoinClick() {
 		if(document.getElementById("uId").value == "" ) {
 			document.getElementById('message').innerHTML = "아이디는 필수입력항목입니다";
 			document.getElementById("uId").focus();
 		}else if(document.getElementById("uPw").value == "" ) {
 			document.getElementById('message').innerHTML = "비밀번호는 필수입력항목입니다";
 			document.getElementById("uPw").focus();
 		}else if(document.getElementById("uPwConfirm").value == "" ) {
 			document.getElementById('message').innerHTML = "비밀번호 확인은 필수입력항목입니다";
 			document.getElementById("uPwConfirm").focus();
 		} else if(document.getElementById("name").value == "") {
 			document.getElementById('message').innerHTML = "이름은 필수입력항목입니다";
 			document.getElementById("name").focus();
 		} else if(document.getElementById("mobile").value == ""){
 			document.getElementById('message').innerHTML = "핸드폰번호는 필수입력항목입니다";
 			document.getElementById("mobile").focus();
 		} else if(document.getElementById("answer").value == ""){
 			document.getElementById('message').innerHTML = "보안문자는 필수입력항목입니다";
 			document.getElementById("answer").focus();
 		} else {
 			if(!document.getElementById('uInfo').checked) {
 	 			document.getElementById('message').innerHTML = "개인정보 수집 및 이용에 대한 안내에 동의해주세요";
 	 			return;
 	 		}
 		}
 		
 		document.join.submit();
 	}
 
</script>
</head>
<body>
<!-- * @param u_id 아이디
	 * @param u_pw 비밀번호
	 * @param name 이름(닉네임)
	 * @param gender 성별
	 * @param age 나이
	 * @param mobile 모바일
	 * @param email 이메일
	 * @param u_img 프로필사진 
	 padding:50%
	 images/user/pic1.jpg
	 
	 -->

<form method="post" action="<%=application.getContextPath() %>/Controller?action=join" class="hn_font" enctype="multipart/form-data" name="join">
<table align="center" >
	<tr bordercolor="black">
		<td rowspan="9" bordercolor="black">
			<div style="height: 230px; width:300px; border:1px solid black;" id="holder">
				<img id="profile" name="file_path" alt="" src="" style="width:100%; height: 100%;" onerror="this.src='../images/user/normalPro.png'" >
				</div>
				<br><br>
			<input type="file" id="uImg" name="uImg" onchange="" style="height: 20px;">
				<script>
var upload = document.getElementsByTagName('input')[0],
    holder = document.getElementById('holder'),
    state = document.getElementById('status');

upload.onchange = function (e) {
  e.preventDefault();

  var file = upload.files[0],
      reader = new FileReader();
  reader.onload = function (event) {
    var img = new Image();
    img.src = event.target.result;
    if (img.width > 560) { // holder width
      img.width = 300;
      img.height= 230;
    }
    holder.innerHTML = '';
    holder.appendChild(img);
  };
  reader.readAsDataURL(file);

  return false;
};
</script>
		</td>
		<td align="right">
			<span class="" style="color:red;"><b>*</b></span>아이디
		</td> 
		<td>
			<input type="text" id="uId" name="uId" class="hn_type" onkeyup="clearUserid()" onblur="isUserid()">
		</td>
		<td colspan="2">
			<span id="messageUserid"></span><br/>
		</td>	
	</tr>

	<tr>	
		<td align="right">
			<span class="" style="color:red;"><b>*</b></span>비밀번호 
		</td>
		<td>
			<input type="password" id="uPw" name="uPw" class="hn_type" onfocus="showUpw()" onkeyup="userpwPattern()" onblur="uPwOnblur()"><br>
		</td>

		<td colspan="2" >
			<input type="checkbox" id="isShow" name="isShow" onclick="checkboxUpw()" >비밀번호표시<br>
		</td>	
		
		<td width="300px">
			<span id="messageUpw" style="color:red; font-size: 15px;"></span><br/>
		</td>
	</tr>	
	<tr>	
		<td align="right">
			&nbsp;&nbsp;<span class="" style="color:red;"><b>*</b></span>비밀번호확인
		</td>
		<td>
			<input type="password" id="uPwConfirm" name="uPwConfirm" class="hn_type" onblur="uPwConfirmOnblur()">
		</td>
		<td colspan="3">
			<span id="messageUpwConfirm"></span><br/><br/>
		</td>
	</tr>	
	<tr>	
		<td align="right">
			<span class="hn_type" style="color:red;"><b>*</b></span>이름
		</td>
		<td >
			 <input type="text" id="name" name="name" class="hn_type" onfocus="uPwCheck()"><br/>
		</td>
		<td colspan="2">
		
		</td>
	</tr>
	<tr>
		<td  align="right">
		나이</td>
		<td>
		 <input class="hn_type" type="text" name="age" id="age" style="width: 50px;">
		
		살/ &nbsp;
		
		성별
		<select style="font-size: 20px; height: 40px;">
				<option value="null">선택</option>
				<option value="M">남</option>
				<option value="M">여</option>
			</select>
		</td>

	</tr>
	<tr>	
		<td align="right">
			<span class="" style="color:red;"><b>*</b></span>&nbsp;핸드폰
		</td>
		<td>
			 <input type="number" id="mobile" name="mobile" class="hn_type" onfocus="" placeholder="숫자만입력"><br/>
		</td>
	<tr>	
		<td align="right">
			&nbsp;이메일
		</td>
		<td>
			 <input type="text" id="email" name="email" class="hn_type" onfocus=""><br/>
		</td>
	</tr>
	<tr >
	<td align="right"><span class="hn_type" style="color:red;"><b>*</b></span>자동가입 방지
	</td>
	<td align="center">
	    <!-- 서블릿을 호출해서 image 형태의 자동가입방지 문자 생성 -->
    <img id="captchaImg" name="captchaImg" src="<%=request.getContextPath() %>/captcha?action=hani" alt="Captcha Image" height="45" /><br>
	</td>
	<td>
	<input type="button" value="새로고침" onclick="imgRefresh()">
	</td>

	</tr>
	<tr>
	<td>
	</td>
	<td>
		<input type="text" class="hn_type" name="answer" id="answer" onblur="secu()">
	</td>
	<td>
		<span id="answerMessage" style="font-size: 20px;"></span><br/>
	</td>
	</tr>
	<tr>
	<td></td>
		<td colspan="3" align="center"><br><input type="checkbox" id="uInfo"> 개인정보 수집 및 이용에 대한 안내(필수)<span class="hn_type" style="color:red;"><b>*</b><br><hr>
			<textarea rows="10px" cols="50px" style="font-size: 20px;" class="hn_font">
정보통신망법 규정에 따라 'NARANG'에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.
1. 수집하는 개인정보
- 회원 가입 시에 ‘아이디, 비밀번호, 이름’를 필수항목으로 수집합니다. 그리고 선택항목으로 이메일 주소, 핸드폰 번호를 수집합니다.
서비스 이용 과정에서 이용자로부터 수집하는 개인정보는 아래와 같습니다.
'NARANG' 내의 개별 서비스 이용, 이벤트 응모 및 경품 신청 과정에서 해당 서비스의 이용자에 한해 추가 개인정보 수집이 발생할 수 있습니다. 추가로 개인정보를 수집할 경우에는 해당 개인정보 수집 시점에서 이용자에게 ‘수집하는 개인정보 항목, 개인정보의 수집 및 이용목적, 개인정보의 보관기간’에 대해 안내 드리고 동의를 받습니다.
서비스 이용 과정에서 IP 주소, 쿠키, 방문일시·불량 이용 기록 등의 서비스 이용 기록, 기기정보가 생성되어 수집될 수 있습니다.
구체적으로 1) 서비스 이용 과정에서 이용자에 관한 정보를 정보통신서비스 제공자가 자동화된 방법으로 생성하여 이를 저장(수집)하거나, 2) 이용자 기기의 고유한 정보를 원래의 값을 확인하지 못하도록 안전하게 변환한 후에 수집하는 경우를 의미합니다.
			</textarea><br>
		</td>

	</tr>
	<tr style="height: 70px">
		<td>
		</td>
		<td colspan="3">
		<span id="message" style="color:red;"></span>
		</td>
	</tr>
	<tr>
		<td>
			
		</td>
		<td colspan="2" align="">
			<input type="button" value="가입" id="btnJoin" style="width: 100px; height: 70px; font-size: 27px; background-color: #FBE600" onclick="btnJoinClick()">&nbsp; 
			<input type="reset" value="취소"  style="width: 100px; height: 70px; font-size: 27px; background-color: #FBE600">
		</td>
	</tr>

</table>
</form>
</body>
</html>