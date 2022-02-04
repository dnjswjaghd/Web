package board.mvc.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import member.mvc.model.MemberService;
import mvc.domain.Member;

@WebServlet("/join/member.do")
public class MemberController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m!=null) {
			m = m.trim();
			switch(m){
				case "insert" : insert(request, response); break;
				case "signup" : signup(request, response); break;
				case "signIn" : signIn(request, response); break;
				case "signout" : signout(request, response); break;
				case "login" : login(request, response); break;
			}
		}else {
			response.sendRedirect("../index.jsp");
		}
		
	}
	public void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			MemberService service = MemberService.getInstance();
			response.sendRedirect("signup.jsp");
		
	}
	public void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		response.sendRedirect("signIn.jsp");
	}
	public void signout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		String id = (String)request.getParameter("userId");
		System.out.println("signoutid:" + id);
		HttpSession session;
		session = request.getSession();
		session.removeAttribute("userId");
		System.out.println("removeAttribute 다음줄");
		
		
		response.sendRedirect("member.do"); 
	}
	
	public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		if((name.length()==0)||(id.length()==0)||(pwd.length()==0)) {
			response.sendRedirect("member.do");
			return;
		}else {
			boolean nullflag = false;
			request.setAttribute("null", nullflag);
			Member dto = new Member(-1, name, id, pwd, -1);
			service.insertS(dto);
		} 
		response.sendRedirect("member.do");
	}
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		String id = request.getParameter("id");
		System.out.println("id: "+id);
		String pwd = request.getParameter("pwd");
		System.out.println("pwd: "+pwd);
		HttpSession session;
		
		ArrayList<Member> list = service.listS();
		for(Member li : list) {
			System.out.println("getid: "+li.getId()+"getpwd: "+li.getPwd());
			if(li.getId().equals(id)) {
				System.out.println("아이디같음");
				if(li.getPwd().equals(pwd)) {
					System.out.println("패스워드같음");
					System.out.println("getid: "+li.getId()+"getpwd: "+li.getPwd());
					session = request.getSession();
					session.setAttribute("userId", li.getId());
					response.sendRedirect("../boarddpcp3/board.do");
					return;
				}
			}
		}
		response.sendRedirect("member.do?m=signIn");
		
	
	}
	
	
}
