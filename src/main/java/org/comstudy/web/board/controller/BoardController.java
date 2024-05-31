package org.comstudy.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comstudy.web.board.model.BoardDao;
import org.comstudy.web.board.model.BoardDaoImpl;
import org.comstudy.web.board.model.BoardDto;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardDao boardDao = new BoardDaoImpl();
	
	private String mkUrlPattern(HttpServletRequest req) {
		
		String reqUri = req.getRequestURI();
		String ctxPath = req.getContextPath();
		int i = ctxPath.length();
		String urlPattern = reqUri.substring(i);
			
		return urlPattern;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String urlPattern =  mkUrlPattern(req);
		
		String viewName = "/WEB-INF/views/baord/list.jsp";
			
		switch(urlPattern) {
		case "/board/list.do": 
			List<BoardDto> list = boardDao.selectAll();
			req.setAttribute("list", list);
			; break;
		case "/board/input.do": 
			viewName = "/WEB-INF/views/baord/insert.jsp";
			; break;
		case "/board/detail.do": 
			int num = Integer.parseInt(req.getParameter("num"));
			System.out.println(">>> num : " + num);
			BoardDto board = boardDao.findByNum(num);
			req.setAttribute("board", board);
			viewName = "/WEB-INF/views/baord/select.jsp";
			; break;
		case "/board/modify.do": 
			viewName = "/WEB-INF/views/baord/update.jsp";
			; break;
		case "/board/delete.do": 
			viewName = "/WEB-INF/views/baord/delete.jsp";
			; break;
		default: viewName = "/WEB-INF/views/baord/list.jsp";
		}
		
		RequestDispatcher view = req.getRequestDispatcher(viewName);
		view.forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String author = req.getParameter("author");
		String email = req.getParameter("email");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String passwd = req.getParameter("passwd");
		
		BoardDto boardDto = new BoardDto(author, email, title, content, passwd);
		System.out.println(boardDto);
		boardDao.insert(boardDto);
		
		resp.sendRedirect("list.do");
	}
}
