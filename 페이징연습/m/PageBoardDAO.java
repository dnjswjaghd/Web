package page.board.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import page.board.domain.PageBoard;

public class PageBoardDAO {
	private DataSource ds;
	PageBoardDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne){
			System.out.println("#tomcat이 만든 dbcp객체 이름(jdbc/myoracle)을 못찾음"); 
		}
	}
	ArrayList<PageBoard> list(){
		ArrayList<PageBoard> list = new ArrayList<>();
		Statement stmt = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = "select * from PAGE_BOARD order by idx desc";
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			stmt.executeQuery(sql);
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
		
		return list;
	}
	ArrayList<PageBoard> list(int a){
		ArrayList<PageBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = "select idx, subject, writer, writedate, readnum  from (select rownum rnum , aa.* from " +
				"(select * from page_board order by refer desc, sunbun asc ) aa ) " +
				"where rnum > ? and rnum <= ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, a);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int idx = rs.getInt(1);
				String subject = rs.getString(2);
				String writer = rs.getString(3);
				Date writedate = rs.getDate(4);
				int readnum = rs.getInt(5);
				
				list.add(new PageBoard(idx, writer, null, null, null, subject, null, writedate, readnum, null, null, -1, -1, -1, -1));
			}
			return list;
			
		}catch(SQLException se){
        	se.printStackTrace();
        	return null; 
        }finally{
           try{
              if(rs != null) rs.close();
              if(pstmt != null) pstmt.close();
              if(con != null) con.close();
           }catch(SQLException se){}
        }
	}

}
