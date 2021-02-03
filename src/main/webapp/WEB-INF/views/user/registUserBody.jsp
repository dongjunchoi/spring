<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!-- Custom styles for this template -->
<link href="${cp }/css/dashboard.css" rel="stylesheet">
<link href="${cp }/css/blog.css" rel="stylesheet">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(function() {
		// 주소 검색 버튼이 클릭되었을 때 다음주소 api 팝업을 연다
		$("#addrBtn").on("click", function() {
			new daum.Postcode({
				oncomplete : function(data) {

					$("#addr1").val(data.roadAddress); //도로주소
					$("#zipcode").val(data.zonecode); //우편번호
					
					// 사용자 편의성을 위해 상세주소 입력 input태그로 focus 설정 -  도로주소를 입력하면 커서가 상세주소로
					$("#addr2").focus();
				}
			}).open();

		})
	})
</script>
<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자 등록</h2>



		<form class="form-horizontal" role="form" method="post"
			action="${cp }/user/registUserTiles" enctype="multipart/form-data">

			<div class="form-group">
				<label class="col-sm-2 control-label">사용자 아이디</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="userid" name="userid"
						placeholder="사용자 아이디" value="${userVo.userid }" /><br> <span
						style="color: red"><form:errors path="userVo.userid" /></span> <input
						type="file" class="form-control" name="profile" />
				</div>
			</div>

			<div class="form-group">
				<label for="usernm" class="col-sm-2 control-label">사용자 이름</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="usernm" name="usernm"
						placeholder="사용자 이름" value="${userVo.usernm }" /><br>
				</div>
			</div>

			<div class="form-group">
				<label for="alias" class="col-sm-2 control-label">별명</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="alias" name="alias"
						placeholder="별명" value="${userVo.alias }" /><br>
				</div>
			</div>


			<div class="form-group">
				<label for="pass" class="col-sm-2 control-label">비밀번호</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="pass" name="pass"
						placeholder="비밀번호" value="${userVo.pass }" /><br>
				</div>
			</div>

			<div class="form-group">
				<label for="addr1" class="col-sm-2 control-label">도로주소</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="addr1" name="addr1"
						placeholder="도로주소" readonly value="${userVo.addr1 }" />
				</div>
				<div class="col-sm-2">
					<button type="button" id="addrBtn" class="btn btn-default">주소검색</button>
					<br> <br>
				</div>
			</div>

			<div class="form-group">
				<label for="addr2" class="col-sm-2 control-label">상세주소</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="addr2" name="addr2"
						placeholder="상세주소" value="${userVo.addr2 }" /><br>
				</div>
			</div>

			<div class="form-group">
				<label for="zipcode" class="col-sm-2 control-label">우편번호</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="zipcode" name="zipcode"
						placeholder="우편번호" readonly value="${userVo.zipcode }" /><br>
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">사용자 등록</button>
				</div>
			</div>
		</form>
	</div>
</div>

