<%@page import="java.util.Date"%>
<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/common/common_lib.jsp"%>
<link href="${cp }/css/dashboard.css" rel="stylesheet">
<link href="${cp }/css/blog.css" rel="stylesheet">

<script>
	//문서 로딩이 완료되고 나서 실행되는 영역
	$(function() {
		$(".user").on("click", function() {
			var userid = $(this).data("userid");
			$("#userid").val(userid);
			$("#frm").submit();
		});
	});
</script>

</head>

<body>

	<form id="frm" action="${cp }/user/detailUser">
		<input type="hidden" id="userid" name="userid" value="" />
	</form>

	<!-- header부분 include -->
	<%@ include file="/WEB-INF/views/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<!-- left 부분 include -->
				<%@ include file="/WEB-INF/views/common/left.jsp"%>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자 페이징 리스트</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>사용자 아이디</th>
									<th>사용자 이름</th>
									<th>사용자 별명</th>
									<th>등록일시</th>
								</tr>

								<c:forEach items="${userList }" var="user">
									<tr class="user" data-userid="${user.userid }">
										<td>${user.userid }</td>
										<td>${user.usernm }</td>
										<td>${user.alias }</td>
										<td><fmt:formatDate value="${user.reg_dt }" pattern="yyyy.MM.dd" /></td>
									</tr>
								</c:forEach>

							</table>
						</div>

						<a class="btn btn-default pull-right" href="${cp }/user/registUser">사용자 등록</a>
						<a class="btn btn-default pull-right" href="${cp }/user/excelDownload">사용자 엑셀 다운로드</a>

						<div class="text-center">
							<ul class="pagination">
								<li class="prev">
									<a href="${cp }/user/pagingUser?page=1&pageSize=${pageVo.getPageSize() }">«</a>
								</li>
								<c:forEach begin="1" end="${pagination }" var="i">
									<c:choose>
										<c:when test="${pageVo.getPage() == i }">
											<li class="active"><span>${i }</span></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${cp }/user/pagingUser?page=${i }&pageSize=${pageVo.getPageSize() }">${i }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<li class="next">
									<a href="${cp }/user/pagingUser?page=${pagination }&pageSize=${pageVo.getPageSize() }">»</a>
								</li>
							</ul>
						</div>
							
					</div>
				</div>

			</div>
		</div>
	</div>

</body>

</html>