<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시판</h1>
	<div>
		<a href="/board/addBoard">글쓰기</a>
	</div>
	<div>
		<c:forEach var="m" items="${localNameList}">
			<a href="/board/boardList?localName=${m.localName}">${m.localName}(${m.cnt})</a>
		</c:forEach>
	</div>
	
	<table border="1">
		<tr>
			<th>localName</th>
			<th>boardTitle</th>
			<th>memberId</th>
			<th>createdate</th>
		</tr>
		<c:forEach var="b" items="${boardList}">
		<tr>
			<td>${b.localName}</td>
			<td><a href="/board/boardOne?boardNo=${b.boardNo}">${b.boardTitle}</a></td>
			<td>${b.memberId}</td>
			<td>${b.createdate}</td>
		</tr>
		</c:forEach>
	</table>
	<c:if test="${currentPage > 1 }">
		<a href="/board/boardList?currentPage=${currentPage - 1}">이전</a>
	</c:if>
	&nbsp;<span>${currentPage}</span>
	
	<c:if test="${lastPage > currentPage }">
		<a href="/board/boardList?currentPage=${currentPage + 1}">다음</a>
	</c:if>
</body>
</html>