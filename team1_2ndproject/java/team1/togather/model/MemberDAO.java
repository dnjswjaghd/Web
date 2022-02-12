package team1.togather.model;

import static team1.togather.model.MemberSQL.JOIN;
import static team1.togather.model.MemberSQL.LOGIN;
import static team1.togather.model.MemberSQL.SELECT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import team1.togather.domain.Member;

public class MemberDAO {
	private DataSource ds;
	MemberDAO(){
	try {
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		ds = (DataSource)envContext.lookup("jdbc/myoracle");
	}catch(NamingException ne) {		
	}
}
	
	boolean insert(Member dto) {
		Connection con = null;
		PreparedStatement pstmt ;	
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(JOIN);
			pstmt.setString(1,dto.getMaddr());
			pstmt.setString(2,dto.getPfrloc());
			pstmt.setString(3,dto.getMname());
			pstmt.setString(4,dto.getGender());	
			pstmt.setString(5,dto.getBirth());
			pstmt.setString(6,dto.getPwd());
			pstmt.setString(7,dto.getPhone());
			int i =pstmt.executeUpdate();
			if(i>0){
				return true;
			}else return false;
		}catch(SQLException se){
			return false;
		}
	}
	Member login(String phone) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN);
			System.out.println("phone 1:"+phone);
			pstmt.setString(1,phone);
			System.out.println("phone 2:"+phone);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				long mnum= rs.getLong(1);
				String maddr = rs.getString(2);
				String pfrloc = rs.getString(3);
				String mname = rs.getString(4);
				String gender = rs.getString(5);
				String birth = rs.getString(6);
				String pwd = rs.getString(7);
				int athur = rs.getInt(9);	
				return new Member(mnum,maddr,pfrloc,mname,gender,birth,pwd,phone,athur);
			}else return null;
		}catch(SQLException se) {
			System.out.println(se);
			return null;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {
				
			}
		}
	}
	Member findpassword(String phone) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(SELECT);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				long mnum = rs.getLong(1);
				String maddr = rs.getString(2);
				String pfrlok = rs.getString(3);
				String mname = rs.getString(4);
				String gender = rs.getString(5);
				String birth = rs.getString(6);
				String pwd = rs.getString(7);
				int athur = rs.getInt(9);
				//System.out.println(pwd);
				
				return new Member(mnum,maddr,pfrlok,mname,gender,birth,pwd,phone,athur);
			}else return null;
		}catch(SQLException se) {
			return null;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {
				
			}
		}
	}
}
