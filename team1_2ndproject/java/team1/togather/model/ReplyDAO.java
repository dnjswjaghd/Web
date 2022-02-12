package team1.togather.model;

import static team1.togather.model.ReplySQL.RINSERT;
import static team1.togather.model.ReplySQL.RLIKE;
import static team1.togather.model.ReplySQL.RLIST;
import static team1.togather.model.ReplySQL.RUPDATE1;
import static team1.togather.model.ReplySQL.RUPDATE2;

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

import team1.togather.domain.Reply;

public class ReplyDAO {
	   private DataSource ds;
	   ReplyDAO() {
	      try {
	         Context initContext = new InitialContext();
	         Context envContext  = (Context)initContext.lookup("java:/comp/env");
	         ds = (DataSource)envContext.lookup("jdbc/myoracle");
	      }catch(NamingException ne) {
	      }
	   }
	   
	   ArrayList<Reply> list(long bnum){
	      ArrayList<Reply> list = new ArrayList<Reply>();
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = RLIST;
	      try{
	         con = ds.getConnection();
	         pstmt = con.prepareStatement(sql);
	         pstmt.setLong(1, bnum);
	         rs = pstmt.executeQuery();
	            while(rs.next()){
	               long resq = rs.getLong(1);
	               String mname = rs.getString(3);
	               String content = rs.getString(5);
	               long r_like = rs.getLong(6);
	               Date rdate = rs.getDate(7);
	               list.add(new Reply(resq, -1, mname, -1, content, r_like, rdate, -1));
	            }
	            return list;
	      }catch(SQLException se){
	         System.out.println("#ReplyDAO list() se: " + se);
	         return null;
	      }finally{
	         try{
	            if(rs != null) rs.close();
	            if(pstmt != null) pstmt.close();
	            if(con != null) con.close();
	         }catch(SQLException se){
	        	 
	         }finally{
	             try{
	                 if(rs != null) rs.close();
	                 if(pstmt != null) pstmt.close();
	                 if(con != null) con.close();
	              }catch(SQLException se){
	            	  
	              }
	           }
	      }
	   }
	   ArrayList<Reply> list(){
			ArrayList<Reply> list = new ArrayList<Reply>();
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			String sql = RLIST;
			try{
				con = ds.getConnection();
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
					while(rs.next()){
						long rseq = rs.getLong(1);
						String mname = rs.getString(3);
						String content = rs.getString(5);
						long r_like = rs.getLong(6);
						Date rdate = rs.getDate(7);
						list.add(new Reply(rseq, 1, mname, -1, content, r_like, rdate, -1));
					}
					return list;
			}catch(SQLException se){
				System.out.println("#ReplyDAO list() se: " + se);
				return null;
			}finally{
				try{
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(con != null) con.close();
				}catch(SQLException se){}
			}
		}
	   ArrayList<Reply> update1(long rseq){
			ArrayList<Reply> update1 = new ArrayList<Reply>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = RUPDATE1;
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, rseq);
				rs = pstmt.executeQuery();
					while(rs.next()){
						String mname = rs.getString(3);
						String content = rs.getString(5);
						long r_like = rs.getLong(6);
						update1.add(new Reply(rseq, -1, mname, -1, content, r_like, null, -1));
					}
					return update1;
			}catch(SQLException se){
				System.out.println("#ReplyDAO update1() se: " + se);
				return null;
			}finally{
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(SQLException se){}
			}
		}
	   boolean insert(Reply dto) { //±¸Çö
	      String sql = RINSERT;
	      Connection con = null;
	      PreparedStatement pstmt =null;   
	      try{
	         con = ds.getConnection();
	         pstmt = con.prepareStatement(sql);
	         pstmt.setLong(1,dto.getBnum());
	         pstmt.setString(2,dto.getMname());
	         pstmt.setLong(3,dto.getMnum());
	         pstmt.setString(4,dto.getContent());
	         int i = pstmt.executeUpdate();
	         if(i>0){
	            return true;
	         }else return false;
	      }catch(SQLException se){
	         return false;
	      }finally{
	           try{
	               if(pstmt != null) pstmt.close();
	               if(con != null) con.close();
	            }catch(SQLException se){
	            	
	            }
	         }
	   }
	   boolean update2(Reply dto) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = RUPDATE2;
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,dto.getContent());
				pstmt.setLong(2,dto.getRseq());
				int i =pstmt.executeUpdate();
				if(i>0){
					return true;
				}else return false;
				}catch(SQLException se){
				return false;
			}finally{
				try{
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(SQLException se){}
			}
	 }
	 
	 boolean r_like(Reply dto) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = RLIKE;
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setLong(1,dto.getR_like());
				pstmt.setLong(2,dto.getRseq());
				int i =pstmt.executeUpdate();
				if(i>0){
					return true;
				}else return false;
				}catch(SQLException se){
				return false;
			}finally{
				try{
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				}catch(SQLException se){}
			}
		}
	
	void delete(long rseq) {
		String sql = ReplySQL.RDELETE;
		Connection con =null;
		PreparedStatement pstmt = null;
       try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, rseq);
			pstmt.executeUpdate();	
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
	   
	}