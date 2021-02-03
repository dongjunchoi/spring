<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/mvc/fileupload/upload" enctype="multipart/form-data" method="post">
		userid : <input type="text" value="brown" name="userid"/><br>
		picture : <input type="file" name="picture"/><br>
		<input type="submit" value="전송"/>
	</form>
	
</body>
</html>