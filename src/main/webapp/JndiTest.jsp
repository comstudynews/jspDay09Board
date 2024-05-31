<%@page import="org.comstudy.web.board.dbcp.JdbcUtil"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
/* Context initCtx = new InitialContext();
Context envCtx = (Context) initCtx.lookup("java:comp/env");
DataSource ds = (DataSource)envCtx.lookup("jdbc/BoardDB"); */

Connection conn = JdbcUtil.getConnection();

out.println(conn);

conn.close();

%>
</body>
</html>