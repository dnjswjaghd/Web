package page.board.domain;

import java.sql.Date;

public class PageMember {
	   private int no;
	   private String name;
	   private String ssn;
	   private String id;
	   private String pwd;
	   private String email;
	   private String phone;
	   private Date joindate;
	public PageMember(int no, String name, String ssn, String id, String pwd, String email, String phone,
			Date joindate) {
		super();
		this.no = no;
		this.name = name;
		this.ssn = ssn;
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.phone = phone;
		this.joindate = joindate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	   
}
