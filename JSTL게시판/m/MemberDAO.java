package member.mvc.model;

//import static board.mvc.model.BoardSQL.LIST;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mvc.domain.Member;

public class MemberDAO {
	private DataSource ds;
	MemberDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne){
			System.out.println("#tomcat이 만든 dbcp객체 이름(jdbc/myoracle)을 못찾음"); 
		}
	}
	ArrayList<Member> list(){ 
		ArrayList<Member> list = new ArrayList<>();
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		String sql = "select * from MVCMEMBER order by mnum desc";
        try{
		   con = ds.getConnection();
		   stmt = con.createStatement();
           rs = stmt.executeQuery(sql);
           while(rs.next()){
              int mnum = rs.getInt(1);
              String name = rs.getString(2);
              String id = rs.getString(3);
			  String pwd = rs.getString(4);
			  int athur = rs.getInt(5);
              list.add(new Member(mnum, name, id, pwd, athur));
              System.out.println(" mnum: "+mnum+" name: "+name+" id: "+id
            		  +" pwd: "+pwd+" athur: "+athur);
           }
           return list;
        }catch(SQLException se){
        	se.printStackTrace();
        	return null; 
        }finally{
           try{
              if(rs != null) rs.close();
              if(stmt != null) stmt.close();
              if(con != null) con.close();
           }catch(SQLException se){}
        }
	}
	void insert(Member dto) {
		  Connection con =null;
		  PreparedStatement pstmt = null;
		  String sql = "insert into MVCMEMBER values(MEMBER_SEQ.nextval, ?, ?, ?, default)";
		  try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPwd());
			pstmt.executeUpdate(); 
		  }catch(SQLException se){
			  se.printStackTrace();
		  }
	}
}
