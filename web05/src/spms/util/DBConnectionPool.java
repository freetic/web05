package spms.util;

import java.sql.Connection;
import java.util.ArrayList;

public class DBConnectionPool {
	String url;
	String username;
	String password;
	ArrayList<Connection> connList = new ArrayList<>();

	public DBConnectionPool(String driver, String url, String username, String password) throws Exception{
		this.url = url;
		this.username = username;
		this.password = password;
		Class.forName(driver);
	}
	
}
