package mvc.domain;

public class Member {
	private int mnum;
	private String name;
	private String id;
	private String pwd;
	private int athur;
	
	public Member(int mnum, String name, String id, String pwd,  int athur) {
		super();
		this.mnum = mnum;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.athur = athur;
	}
	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAthur() {
		return athur;
	}
	public void setAthur(int athur) {
		this.athur = athur;
	}

}
