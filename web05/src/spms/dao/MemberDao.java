package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import spms.vo.Member;

public class MemberDao {
	Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public List<Member> selectList() throws Exception{
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select MNO, MNAME, EMAIL, CRE_DATE from MEMBERS order by MNO asc");
			
			ArrayList<Member> members = new ArrayList<>();
			
			while(rs.next()) {
				members.add(new Member()
						.setNo(rs.getInt("MNO"))
						.setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL"))
						.setCreatedDate(rs.getDate("CRE_DATE")));
			}
			return members;
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(rs != null) rs.close(); } catch (Exception e2) {}
			try { if(stmt != null) stmt.close(); } catch (Exception e2) {}
		}
	}
	
	public int insert(Member member) throws Exception{
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(
			          "INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
			                  + " VALUES (?,?,?,NOW(),NOW())");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(stmt != null) stmt.close(); } catch (Exception e2) {}
		}
	}
	
	public int delete(int no) throws Exception{
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			return stmt.executeUpdate("delete from members where mno="+no);
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(stmt != null) stmt.close(); } catch (Exception e2) {}
		}
	}
	
	public Member selectOne(int no) throws Exception{
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select mno, email, mname, cre_date from members where mno="+no);
			if(rs.next()) {
				return new Member()
						.setNo(rs.getInt("mno"))
						.setEmail(rs.getString("email"))
						.setName(rs.getString("mname"))
						.setCreatedDate(rs.getDate("cre_date"));
			} else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(rs != null) rs.close(); } catch (Exception e2) {}
			try { if(stmt != null) stmt.close(); } catch (Exception e2) {}
		}
	}
	
	public int update(Member member) throws Exception{
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(
					"update members set email=?, mname=?, mod_date=now() where mno=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(stmt != null) stmt.close(); } catch (Exception e2) {}
		}
	}
	
	public Member exist(String email, String password) throws Exception{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.prepareStatement(
			          "SELECT MNAME,EMAIL FROM MEMBERS"
			                  + " WHERE EMAIL=? AND PWD=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return new Member()
						.setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL"));
			} else {
				return null;
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
	      try {if (rs != null) rs.close();} catch (Exception e) {}
	      try {if (stmt != null) stmt.close();} catch (Exception e) {}
	    }
	}

}
