<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- application.properties에서 전체 경로 설정 -->
	<form method="post" action="/board/addBoard" enctype="multipart/form-data">
		<h1>insert</h1>
		<table>
			<tr>
				<td><input type="text" name="localName" placeholder="insert localName"></td>
			</tr>
			<tr>
				<td><input type="text" name="boardTitle" placeholder="insert boardTitle"></td>
			</tr>
			<tr>
				<td><input type="text" name="boardContent" placeholder="insert boardContent"></td>
			</tr>
			<tr>
				<td><input type="text" name="memberId" placeholder="insert memberId"></td>
			</tr>
			<tr>
				<td>파일첨부 : <input type="file" name="multipartFile" multiple="multiple"></td>
			</tr>
		</table>
		<button type="submit">글추가</button>
	</form>
</body>
</html>