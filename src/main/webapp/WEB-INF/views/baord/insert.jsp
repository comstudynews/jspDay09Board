<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>새 게시 글 작성</h1>
	<form action="insert.do" method="post">
		<table>
			<tr>
				<th>작성자</th><td><input type="text" name="author" value="user01"/></td>
			</tr>
			<tr>
				<th>전자메일</th><td><input type="text" name="email" value="user01@test.com"/></td>
			</tr>
			<tr>
				<th>제목</th><td><input type="text" name="title" size="60" value="글 입력 연습"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" cols="50" rows="10">글 입력 연습 내용입니다</textarea></td>
			</tr>
			<tr>
				<th>비밀번호</th><td><input type="password" name="password" value="12345" /></td>
			</tr>
			<tr>
				<th> </th>
				<td>
					<input type="submit" value="Save" /> 
					<a href="list.do">목록</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>