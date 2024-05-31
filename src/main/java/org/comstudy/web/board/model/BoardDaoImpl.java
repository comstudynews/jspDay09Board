package org.comstudy.web.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.comstudy.web.board.dbcp.JdbcUtil;

public class BoardDaoImpl implements BoardDao {
	
	public static final String SELECT = "SELECT * FROM BOARD ORDER BY num DESC";
	public static final String FIND_BY_NUM = "SELECT * FROM BOARD WHERE num=?";
	public static final String INSERT = "INSERT INTO BOARD(author,email,title,content,passwd) VALUES (?,?,?,?,?)";
	public static final String UPDATE = "UPDATE BOARD SET author=?, email=?, title=?, content=?  WHERE num=2";
	public static final String UPDATE_CNT = "UPDATE BOARD SET readcnt=(SELECT readcnt from board WHERE num=?)+1 WHERE num=?";
	public static final String DELETE = "DELETE FROM board WHERE num=?";
	public static final String PASSWD_CHK = "SELECT NUM, PASSWD FROM BOARD WHERE NUM=? AND PASSWD=?";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	private List<BoardDto> boardList;
	private BoardDto board;
	
	@Override
	public List<BoardDto> selectAll() {
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT);
			boardList = new ArrayList<BoardDto>();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String author = rs.getString("author");
				String email = rs.getString("email");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String passwd = rs.getString("passwd");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int rep_root = rs.getInt("rep_root");
				int rep_step = rs.getInt("rep_step");
				int rep_indent = rs.getInt("rep_indent");
				boardList.add(new BoardDto(num, author, email, title, content, passwd, writeday, readcnt, rep_root, rep_step, rep_indent));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, stmt, rs);
		}
		
		return boardList;
	}
	
	@Override
	public BoardDto findByNum(int num) {
		try {
			conn = JdbcUtil.getConnection();
			System.out.println(">>>> conn : " + conn);
			pstmt = conn.prepareStatement(FIND_BY_NUM);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String author = rs.getString("author");
				String email = rs.getString("email");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String passwd = rs.getString("passwd");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int rep_root = rs.getInt("rep_root");
				int rep_step = rs.getInt("rep_step");
				int rep_indent = rs.getInt("rep_indent");
				board = new BoardDto(num, author, email, title, content, passwd, writeday, readcnt, rep_root, rep_step, rep_indent);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return board;
	}
	
	@Override
	public void insert(BoardDto dto) {
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, dto.getAuthor());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getPasswd());
			int cnt = pstmt.executeUpdate();
			if(cnt > 0 ) {
				System.out.println(">>> Board 입력 성공!");
			} else {
				System.out.println(">>> Board 입력 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	
	public void updateCnt(BoardDto dto) {
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(UPDATE_CNT);
			pstmt.setInt(1, dto.getNum());
			pstmt.setInt(2, dto.getNum());
			int cnt = pstmt.executeUpdate();
			if(cnt > 0 ) {
				System.out.println(">>> Board 카운터 증가 성공!");
			} else {
				System.out.println(">>> Board 카운터 증가 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	
	@Override
	public void update(BoardDto dto) {
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(PASSWD_CHK);
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getPasswd());
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				System.out.println(">>> 패스워드 불일치 글 수정 실패!");
				return;
			}
			// author, email, title, content, num
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, dto.getAuthor());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getNum());
			int cnt = pstmt.executeUpdate();
			if(cnt > 0 ) {
				System.out.println(">>> Board 수정 성공!");
			} else {
				System.out.println(">>> Board 수정 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	
	@Override
	public void delete(BoardDto dto) {
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, dto.getNum());
			int cnt = pstmt.executeUpdate();
			if(cnt > 0 ) {
				System.out.println(">>> Board 데이터 삭제 성공!");
			} else {
				System.out.println(">>> Board 데이터 삭제 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
}
