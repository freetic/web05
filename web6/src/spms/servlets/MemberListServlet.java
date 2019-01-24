package spms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;

//@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();
			
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			response.setContentType("text/html; charset=UTF-8");
			
			// request에 회원 목록 데이터를 보관한다.
			request.setAttribute("members", memberDao.selectList());
			
			// JSP로 출력을 위임한다.
			RequestDispatcher rd =request.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/ErrorList.jsp");
			rd.forward(request, response);
		}
	}
}
