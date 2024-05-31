package org.comstudy.web.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/*")
public class BaordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	private String mkUrlPattern(HttpServletRequest req) {
		
		String reqUri = req.getRequestURI();
		String ctxPath = req.getContextPath();
		int i = ctxPath.length();
		String urlPattern = reqUri.substring(i);
			
		return urlPattern;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String urlPattern =  mkUrlPattern(req);
		
		String viewName = "/WEB-INF/views/baord/list.jsp";
			
		switch(urlPattern) {
		case "/board/list.do": 
			; break;
		case "/board/input.do": 
			viewName = "/WEB-INF/views/baord/insert.jsp";
			; break;
		case "/board/detail.do": 
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

	}

}
