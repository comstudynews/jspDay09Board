<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>게시 글 상세 보기</h1>
<table>
	<tr>
		<th>작성자</th><td>${board.author }</td>
	</tr>
	<tr>
		<th>전자메일</th><td>${board.email }</td>
	</tr>
	<tr>
		<th>제목</th><td>${board.title }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${board.content }</td>
	</tr>
</table>
<a href="modify.do?num=1">글 수정</a> | 
<a href="delete.do?num=1">글 삭제</a> |
<a href="list.do">목록</a>

</body>
</html>