package member.mvc.model;

import java.util.ArrayList;

import mvc.domain.Member;

public class MemberService {

	private MemberDAO dao;
	private static final MemberService memberService = new MemberService();
	private MemberService(){
		dao = new MemberDAO();
	}
	public static MemberService getInstance() {
		return memberService;
	}
	public ArrayList<Member> listS(){
		return dao.list();
	}
	public void insertS(Member dto) {
		dao.insert(dto);
	}
}
