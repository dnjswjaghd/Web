package team1.togather.model;

import team1.togather.domain.Member;

public class MemberService {
	private MemberDAO dao;
	private static final MemberService instance = new MemberService();
	private MemberService() {
		dao = new MemberDAO();
	}
	public static MemberService getInstance() {
		return instance;
	}
	public boolean insertS(Member dto) {
		return dao.insert(dto);
	}
	public Member loginS(String phone) {
		Member m =dao.login(phone);
		return m;	
	}
	public Member findpasswordS(String phone) {
		Member m =dao.findpassword(phone);
		
		return m;
	}	
}
