<%@page import="java.util.Date"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<%@ include file="/WEB-INF/views/common/common_lib.jsp" %> 
	<link href="${cp }/css/dashboard.css" rel="stylesheet">
	<link href="${cp }/css/blog.css"rel="stylesheet">
</head>

<body>

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
						<h2 class="sub-header">전체 사용자</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>사용자 아이디</th>
									<th>사용자 이름</th>
									<th>사용자 별명</th>
									<th>등록일시</th>
								</tr>
								<%--
								<%
									for(UserVo user : (List<UserVo>)request.getAttribute("liset"))
									List<UserVo> list = (List<UserVo>) request.getAttribute("list");
									for (int i = 0; i < list.size(); i++) {
										UserVo vo = list.get(i);
								%>
									<tr>
										<td><%=vo.getUserid()%></td>
										<td><%=vo.getUsernm()%></td>
										<td><%=vo.getAlias()%></td>
										<td><%= vo.getReg_dt_fmt() %></td>
									</tr>
								<%
								}
								%>
								 --%>
								 <c:forEach items="${userList }" var="user">
									<tr>
										<td>${user.userid }</td>
										<td>${user.usernm }</td>
										<td>${user.alias }</td>
										<td><fmt:formatDate value="${user.reg_dt }" pattern="yyyy.MM.dd"/></td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<a class="btn btn-default pull-right">사용자 등록</a>

						<div class="text-center">
							<ul class="pagination">
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
							</ul>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>

</html>