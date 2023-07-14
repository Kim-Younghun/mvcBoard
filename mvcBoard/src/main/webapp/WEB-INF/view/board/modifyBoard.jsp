<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/board/addBoard">
		<h1>update</h1>
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
		</table>
		<button type="submit">글수정</button>
	</form>
</body>
</html>