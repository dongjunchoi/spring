<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <%@ include file="/WEB-INF/views/common/common_lib.jsp" %>

	<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
	    
    <!-- Custom styles for this template -->
    <link href="${cp }/css/signin.css" rel="stylesheet">

	<script>
		function getCookieValue(cookieStr, cookieName){
			console.log("getCookieValue");
			var cookies = cookieStr.split("; ");
			
			for(var i in cookies){
				var cookie = cookies[i].split("=");
				
				if(cookie[0]==cookieName) {
					return cookie[1];
				}
			}
			return "";
		}
		
		//cookieName : 추가하고자 하는 쿠기이름
		//cookieValue : 쿠키의 값
		//expires : 유효기간(일수)
		function addCookie(cookieName, cookieValue, expires){
			var dt = new Date();	//현재 날짜 ==> expires만큼 미래 날짜로 변경
			dt.setDate(dt.getDate() + parseInt(expires));
			console.log(dt);
			
			document.cookie = cookieName + "=" + cookieValue + "; "
								+ "path=/; expires=" + dt.toGMTString
		}
		
		function deleteCookie(cookieName){
			addCookie(cookieName, "", -1);
		}

		//html 문서 로딩이 완료 되고 나서 실행 되는 코드
		$(function(){
			<c:if test="${msg != null }">
				alert("${msg}" + "ra");
// 				<c:remove var="msg"/>
			</c:if>
// 			if("${msg}" != ""){
// 				alert("${msg}");
// 			  	<c:remove var="msg"/>
<%-- 				<% session.removeAttribute("msg"); %> --%>
// 			}
			
			//userid, rememberme 쿠키를 확인하여 존재할 경우 값설정, 체크
			if(Cookies.get("userid") != undefined){
				$("#userid").val(Cookies.get("userid"));
				$("#rememberme").prop("checked", true);
			}
			
			//signin 아이디를 select
			$("#signin").on("click", function(){
				//remember me 체크박스가 체크 되어 있는지 확인

				// 체크되어 있을 경우
				if($("#rememberme").is(":checked") == true){
					// userid input에 있는 값을 userid쿠키로 저장
					Cookies.set("userid", $("#userid").val());
					
					// rememberme 쿠키로 Y값을 저장
					Cookies.set("rememberme", "Y");
				}
				//체크 해제되어 있는 경우 : 더이상 사용하지 않는다는 의미 이므로 userid, rememberme쿠키 삭제
				else{
					Cookies.remove("userid");					
					Cookies.remove("rememberme");					
				}
				$("#frm").submit();
			});
			
		});
		</script>
	
  </head>

  <body>

	<div class="container">
	
		<form class="form-signin" id="frm" action="${cp }/login/process" method="post">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="userid" class="sr-only">userId</label> 
				<input type="text" id="userid" class="form-control" placeholder="userid" required autofocus name="userid"> 
				<label for="inputPassword" class="sr-only">Password</label> 
				<input type="password" id="inputPassword" class="form-control" name="pass" placeholder="Password" required>
			<div class="checkbox">
				<label> 
					<input type="checkbox" id="rememberme" value="remember-me">Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="button" id="signin">Sign in</button>
		</form>

	</div>
	<!-- /container -->

</body>
</html>
