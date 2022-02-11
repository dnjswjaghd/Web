package team1.togather.model;

import static team1.togather.model.BoardSQL.SELECT;

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

import team1.togather.domain.Board; 
public class BoardDAO {
	private DataSource ds;
	BoardDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne){
			System.out.println("#tomcat이 만든 dbcp객체 이름(jdbc/myoracle)을 못찾음"); 
		}
	}
	ArrayList<Board> blist(){
		ArrayList<Board> list = new ArrayList<>();
		Statement stmt = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = SELECT;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				long bnum =rs.getLong(1);
				String bcategory = rs.getString(2);
				String btitle = rs.getString(3);
				long mnum = rs.getLong(4);
				String bcontent = rs.getString(5);
				String bfile = rs.getString(6);
				long blike = rs.getLong(7);
				long bview = rs.getLong(8);
				Date rdate = rs.getDate(9);
				list.add(new Board(bnum, bcategory, btitle, mnum, bcontent, bfile, blike, bview, rdate));
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
	String getNameByMnum(long mnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql ="select MNAME from MEMBER where MNUM=?";
		ResultSet rs = null;
		String name = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, mnum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				name = rs.getString(1);
			}
			return name;
		}catch(SQLException se) {
			return null;
		}finally{
	           try{
	               if(rs != null) rs.close();
	               if(pstmt != null) pstmt.close();
	               if(con != null) con.close();
	            }catch(SQLException se){}
	         }
	}
	Board getContent(Long bnum) {
		Statement stmt = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		String sql = "select * from BOARD where bnum=?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, bnum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bnum =rs.getLong(1);
				String bcategory = rs.getString(2);
				String btitle = rs.getString(3);
				long mnum = rs.getLong(4);
				String bcontent = rs.getString(5);
				String bfile = rs.getString(6);
				long blike = rs.getLong(7);
				long bview = rs.getLong(8);
				Date rdate = rs.getDate(9);
				board= new Board(bnum, bcategory, btitle, mnum, bcontent, bfile, blike, bview, rdate);
			}
			return board;
		}catch(SQLException se){
        	se.printStackTrace();
        	return board; 
        }finally{
           try{
              if(rs != null) rs.close();
              if(stmt != null) stmt.close();
              if(con != null) con.close();
           }catch(SQLException se){}
        }
	}
	ArrayList<Board> Rlist(){
		
		ArrayList<Board> list = new ArrayList<>();
		Statement stmt = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = "select * from REPLY order by ref desc lev asc ";
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
           }catch(SQLException se){
        	   
           }finally{
               try{
                   if(rs != null) rs.close();
                   if(stmt != null) stmt.close();
                   if(con != null) con.close();
                }catch(SQLException se){}
             }
        }
		return list;
	}
	void insert(String bcategory, String btitle, Long mnum ,String bcontent, String bfile) {
		  Connection con =null;
		  PreparedStatement pstmt = null;
		  String sql = "insert into BOARD values(B_NUM_SEQ.nextval, ?, ?, 1, ?, ?, 0, 0, SYSDATE)";
		  try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bcategory);
			pstmt.setString(2, btitle);
			//pstmt.setLong(3, 0L);
			pstmt.setString(3, bcontent);
			pstmt.setString(4, bfile); //로직추가해야할수있음
			pstmt.executeUpdate(); 
		  }catch(SQLException se){
			  se.printStackTrace();
		  }finally{
	           try{
	               if(pstmt != null) pstmt.close();
	               if(con != null) con.close();
	            }catch(SQLException se){
	            	se.printStackTrace();
	            }
	         }
	}
	void delete(Long bnum) {
		Connection con =null;
		PreparedStatement pstmt = null;
		String sql = "delete from BOARD where BNUM=?";
		 try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, bnum);
			pstmt.executeUpdate();
		  }catch(SQLException se){
			  System.out.println("delete문에러");
			  try{
	              if(pstmt != null) pstmt.close();
	              if(con != null) con.close();
	           }catch(SQLException see){
	        	   see.printStackTrace();
	           }finally{ 
	               try{
	                   if(pstmt != null) pstmt.close();
	                   if(con != null) con.close();
	                }catch(SQLException see){}
	             }
		  } 
	}
	void updateDo(Board dto) {
		  Connection con =null;
		  PreparedStatement pstmt = null;
		  PreparedStatement pstmt2 = null;
		  String sql = "update BOARD set bcategory= ?, btitle = ?, bcontent= ?, bfile= ? where bnum=?";
		  try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBcategory());			
			pstmt.setString(2, dto.getBtitle());			
			pstmt.setString(3, dto.getBcontent());			
			pstmt.setString(4, dto.getBfile());
			pstmt.setLong(5, dto.getBnum());
			pstmt.executeUpdate();
		  }catch(SQLException se){
			  se.printStackTrace();
		  }finally{
              try{
                  if(pstmt != null) pstmt.close();
                  if(con != null) con.close();
               }catch(SQLException se){
            	   se.printStackTrace();
               }
            }
     }
	void updateView(Long bnum) {
		Connection con = null;
		Statement stmt = null;
		String sql = "update BOARD set bview=(select bview+1 from board where bnum="+bnum+") where bnum="+bnum+"";
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			stmt.executeQuery(sql);
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
}
	
