package team1.togather.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team1.togather.domain.Member;
import team1.togather.model.MemberService;


@WebServlet("/member/login.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public MemberController() {
        super();
  
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m!=null) {
			m = m.trim();
			switch(m){
			case "join":join(request,response);break;//로그인 창
			case "login":login(request,response);break;//로그인 창
			case "findpassword":findpassword(request,response);break;//로그아웃
			case "loginkakao":loginkakao(request,response);break;//로그아웃
			
			}
		}else {
			response.sendRedirect("memindex.jsp");
		}	
	}
    private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		MemberService service = MemberService.getInstance();		
		String maddr = request.getParameter("maddr");
		String pfrloc = request.getParameter("pfrloc");
		String mname = request.getParameter("mname");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		String pwd = request.getParameter("pwd");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phone =(phone2+phone3);
		Member dto = new Member(-1,maddr,pfrloc,mname,gender,birth,pwd,phone,-1);
		System.out.println(maddr);
		System.out.println(pfrloc);
		System.out.println(mname);
		System.out.println(gender);
		System.out.println(birth);
		System.out.println(pwd);
		System.out.println(phone);
		
		boolean flag =service.insertS(dto);
		
		if(flag==true) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('회원가입성공')");
			pw.println("opener.parent.location.reload();");
			pw.println("window.close();");
			pw.println("location.href='../'");
			pw.println("</script>");
			pw.close();
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('이미 존재하는번호입니다')");
			pw.println("location.href='login/jointest.jsp'");
			pw.println("</script>");
			pw.close();
		}
	}	
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		
    	String phone = request.getParameter("phone");
		String pwd = request.getParameter("pwd");
		MemberService service = MemberService.getInstance();
		if(phone.length()>3) {
		phone = phone.substring(3);
		}
		Member m = service.loginS(phone);
		if(m != null && m.getPhone() !=null && m.getPwd() != null) {
		if(phone.equals(m.getPhone()) && pwd.equals(m.getPwd())) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userid",m.getMname());
			}
		}
		request.setAttribute("m",m);
		request.setAttribute("pwd",pwd);
		request.setAttribute("phone",phone);
		String view ="login/Succeed.jsp";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);

    }
    private void findpassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		
    	String phone = request.getParameter("phone");
    	String mname = request.getParameter("mname");
    	MemberService service = MemberService.getInstance();
    	if(phone.length()>3) {
    	phone = phone.substring(3);
    	}
    	Member m =service.findpasswordS(phone);   	
    	if(m != null && phone.equals(m.getPhone()) && mname.equals(m.getMname())) {
    	    m.getPwd();
    		response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('"+m.getPwd()+"')");
			pw.println("opener.parent.location.reload();");
			pw.println("window.close();");
			pw.println("location.href='../'");
			pw.println("</script>");
			pw.close();  
    	}else {
    		response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println("<script>");
			pw.println("alert('사용자가없습니다')");
			pw.println("location.href='../login/findpassword.jsp'");
			pw.println("</script>");
			pw.close(); 
    	}
    }
    
    private void loginkakao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{			
    	String mname = request.getParameter("mname");
    	HttpSession session = request.getSession(true);
		session.setAttribute("userid",mname);
    	System.out.println("내이름 : "+mname);
    	String view ="/";
		RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
    }
}
