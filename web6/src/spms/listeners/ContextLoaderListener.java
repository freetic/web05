package spms.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import spms.dao.MemberDao;
import spms.util.DBConnectionPool;

public class ContextLoaderListener  implements ServletContextListener{
//	Connection conn;

	DBConnectionPool connPool;
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext sc = event.getServletContext();
			connPool = new DBConnectionPool(
					sc.getInitParameter("driver"), 
					sc.getInitParameter("url"), 
					sc.getInitParameter("username"), 
					sc.getInitParameter("password"));
			
			MemberDao memberDao = new MemberDao();
			memberDao.setDbConnectioinPool(connPool);
			sc.setAttribute("memberDao", memberDao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		connPool.closeAll();
	}
}
