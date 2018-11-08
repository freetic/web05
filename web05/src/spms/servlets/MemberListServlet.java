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
//			3. 커넥션 객체로부터 SQL을 던질 객체를 준비하라.
//			stmt = conn.createStatement();							MemberDao의 역할로 변경
			
//			4. SQL을 던지는 객체를 사용하여 서버에 질의하라.
//			rs = stmt.executeQuery(
//					"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
//					" FROM MEMBERS" +
//					" ORDER BY MNO ASC");							MemberDao의 역할로 변경
			
			response.setContentType("text/html; charset=UTF-8");
//			ArrayList<Member> members = new ArrayList<Member>();	MemberDao의 역할로 변경
			
			
			//5. 서버에 가져온 데이터를 사용하여 HTML을 만들어서 웹브라우저로 출력하라.
			// -> 데이터베이스에서 회원정보를 가져와 Member에 담는다. 그리고 Member객체를 ArrayList에 추가한다.
//			while(rs.next()) {
//				members.add(new Member()
//						.setNo(rs.getInt("MNO"))
//						.setName(rs.getString("MNAME"))
//						.setEmail(rs.getString("EMAIL"))
//						.setCreatedDate(rs.getDate("CRE_DATE")) );	MemberDao의 역할로 변경
//			}
			
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
