package board.mvc.control;

import java.io.IOException;
import java.util.ArrayList;

import board.mvc.model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.mvc.model.MemberService;
import mvc.domain.*;


@WebServlet("/boarddpcp3/board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch (m) {
				case "list": list(request, response); break;
				case "input": input(request, response); break;
				case "insert": insert(request, response); break;
				case "del" : delete(request, response);	break;
				case "update": update(request, response); break;
				case "updateGet": updateGet(request, response); break;
				case "content" : content(request, response); break;
			}
		}else {
			list(request, response);
		}
	}
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//service의 역할
		//1. Model 핸들링(java)
		BoardService service = BoardService.getInstance();
		ArrayList<Board> list = service.listS();
		request.setAttribute("list", list);
		
		//2. View 지정(jsp)
		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	public void input(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//service의 역할
		//1. Model 핸들링(java)		
		//2. View 지정(jsp)
		String view = "input.jsp";
		response.sendRedirect(view);
	}
	public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//service의 역할
		//1. Model 핸들링(java)
		BoardService service = BoardService.getInstance();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		Board dto = new Board(-1,name,email,subject,content,null);
		boolean flag = service.insertS(dto);
		request.setAttribute("flag", flag);
		 
		//2. View 지정(jsp)
		String view = "board.do";
		response.sendRedirect(view);
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//service의 역할
		//1. Model 핸들링(java)
		BoardService service = BoardService.getInstance();
		MemberService service2 = MemberService.getInstance();
		service = service.getInstance();
		ArrayList<Member> list = service2.listS();
		String name = request.getParameter("name");
		String seqStr = request.getParameter("seq");
		int seq = Integer.parseInt(seqStr);
		HttpSession session =request.getSession();
		String SName = (String)session.getAttribute("userId");
		int authority=-1;
		for(Member li: list) {
			if(li.getId().equals(SName)) {
				authority = li.getAthur();
			}
		}
		if(authority==0 || name.equals(SName)) {
			service.deleteS(seq);
		}
		//2. View 지정(jsp)
		response.sendRedirect("board.do");
	}
	public void content(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//service의 역할
		//1. Model 핸들링(java)
		BoardService service = BoardService.getInstance();
		String seqStr = request.getParameter("seq");
		int seq = Integer.parseInt(seqStr);
		ArrayList<Board> listup = service.updateS(seq);
		request.setAttribute("listup", listup);
		
		 
		//2. View 지정(jsp)
		
		String view = "content.jsp";
		//response.sendRedirect(view);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//service의 역할
		//1. Model 핸들링(java)
		BoardService service = BoardService.getInstance();
		String seqStr = request.getParameter("seq");
		int seq = Integer.parseInt(seqStr);
		ArrayList<Board> listup = service.updateS(seq);
		request.setAttribute("listup", listup);
		
		 
		//2. View 지정(jsp)
		
		String view = "update.jsp";
		//response.sendRedirect(view);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	public void updateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//service의 역할
		//1. Model 핸들링(java)
		BoardService service = BoardService.getInstance();
		String seqStr = request.getParameter("seq");
		int seq = Integer.parseInt(seqStr);
		String name = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		Board dto = new Board(seq,name,email,subject,content,null);
		service.updateGetS(dto, name);
		
		//2. View 지정(jsp)
		response.sendRedirect("board.do");
	}


}
