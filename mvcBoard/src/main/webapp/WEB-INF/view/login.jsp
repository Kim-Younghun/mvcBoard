<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인</h1>
	<form method="post" action="${pageContext.request.contextPath}/login">
		<table border="1">
			<tr>
				<td>memberId</td>
				<td><input type="text" name="memberId"> </td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td><input type="password" name="memberPw"> </td>
			</tr>
		</table>
		<button type="submit">로그인</button>
		<a href="${pageContext.request.contextPath}/addMember">회원가입</a>
	</form>
</body>
</html>