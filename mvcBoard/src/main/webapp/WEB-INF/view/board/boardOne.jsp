<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>boardOne</h1>
	
	<table border="1">
		<tr>
			<th>boardNo</th>
			<td>${board.boardNo}</td>
		</tr>
		<tr>
			<th>localName</th>
			<td>${board.localName}</td>
		</tr>
		<tr>
			<th>boardTitle</th>
			<td>${board.boardTitle}</td>
		</tr>
		<tr>
			<th>boardContent</th>
			<td>${board.boardContent}</td>
		</tr>
		<tr>
			<th>memberId</th>
			<td>${board.memberId}</td>
		</tr>
		<tr>
			<th>createdate</th>
			<td>${board.createdate}</td>
		</tr>
		<tr>
			<th>updatedate</th>
			<td>${board.updatedate}</td>
		</tr>
	</table>
	
	<table border="1">
		<tr>
			<th>origin_filename</th>
			<th>save_filename</th>
			<th>filetype</th>
			<th>filesize</th>
		</tr>
		<c:forEach var="file" items="${boardfile}">
			<tr>
				<td>${file.originFilename}</td>
				<td>${file.saveFilename}</td>
				<td>${file.filetype}</td>
				<td>${file.filesize}</td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- 삭제 -->
	<form method="post" action="${pageContext.request.contextPath}/board/removeBoard">
		<input type="hidden" name="boardNo" value="${board.boardNo}">	
		<input type="hidden" name="memberId" value="user">	
		<button type="submit">삭제</button>
	</form>
	
	<!-- 수정 -->
	<form method="post" action="${pageContext.request.contextPath}/board/modifyBoard">
		<input type="hidden" name="boardNo" value="${board.boardNo}">	
		<input type="hidden" name="memberId" value="user">	
		<table>
			<tr>
				<td><input type="text" name="boardTitle" placeholder="update boardTitle"></td>
			</tr>
			<tr>
				<td><input type="text" name="boardContent" placeholder="update boardContent"></td>
			</tr>
		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>