package org.comstudy.web.board.dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcUtil {
	private static Connection conn;
	
	public static Connection getConnection() {
		// web에서는 JDBC보다는 아파치에서 제공하는 Jndi를 사용하는것이 좋다.
		// jdbc는 웹이 아닌 일반 Application 환경에서도 사용 가능.
		// Jndi는 톰캣에서 제공 합니다.(웹환경에서 사용하는것이 필수)
		//if(conn == null) {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/BoardDB");

			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//}
		
		return conn;
	}
	
	public static void close(Connection o) {
		try {
			if(o != null) o.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement o) {
		try {
			if(o != null) o.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet o) {
		try {
			if(o != null) o.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		close(conn);
		close(stmt);
		close(rs);
	}
}
