<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>게시 글 목록</h1>
<table border="1" width="600">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>이메일</th>
		<th>날짜</th>
	</tr>
<c:forEach var="board" items="${list }">
	<tr>
		<td><c:out value="${board.num }"></c:out></td>
		<td><a href="detail.do?num=${board.num }">
			<c:out value="${board.title }"></c:out>
		</a></td>
		<td><c:out value="${board.author }"></c:out></td>
		<td><c:out value="${board.email }"></c:out></td>
		<td><c:out value="${board.writeday }"></c:out></td>
	</tr>
</c:forEach>
</table>

<a href="input.do">글 쓰기</a> |  

</body>
</html>