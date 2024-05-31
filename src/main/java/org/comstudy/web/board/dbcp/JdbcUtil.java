package org.comstudy.web.board.dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	private static Connection conn;
	
	public static Connection getConnection() {
		if(conn == null) {
			// web에서는 JDBC보다는 아파치에서 제공하는 Jndi를 사용하는것이 좋다.
			// jdbc는 웹이 아닌 일반 Application 환경에서도 사용 가능.
			// Jndi는 톰캣에서 제공 합니다.(웹환경에서 사용하는것이 필수)
			
		}
		
		return conn;
	}
	
	public void close(Connection o) {
		try {
			if(o != null) o.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(Statement o) {
		try {
			if(o != null) o.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet o) {
		try {
			if(o != null) o.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(Connection conn, Statement stmt, ResultSet rs) {
		close(conn);
		close(stmt);
		close(rs);
	}
}
