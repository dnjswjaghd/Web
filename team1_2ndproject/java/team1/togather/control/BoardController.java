package team1.togather.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team1.togather.domain.Board;
import team1.togather.model.BoardService;

@WebServlet("/board/board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch (m) {
				case "list": list(request, response); break;
				case "content": content(request, response); break;
				case "del" : delete(request, response); break;
				case "input" : input(request, response); break;
				case "insert" : insert(request, response); break;
				case "update" : update(request, response); break;
				case "updateDo" : updateDo(request, response); break;
				case "olist" : olist(request, response); break;
			}
		}else {
			list(request, response);
		}
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		ArrayList<Board> blist = service.blistS(); // db내용을 갖고옴
		ArrayList<String> namelist = new ArrayList<>();
		for(Board li: blist) {
			namelist.add(service.getNameByMnumS(li.getMnum()));
		}
		request.setAttribute("blist", blist); // jsp한테 보내줌
		request.setAttribute("namelist", namelist);
		
		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void olist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		String option = request.getParameter("option"); //sql문 where절 왼쪽항
		String ocontent = request.getParameter("ocontent");
		ArrayList<Board> blist = service.blistS(option, ocontent); // db내용을 갖고옴
		ArrayList<String> namelist = new ArrayList<>();
		for(Board li: blist) {
			namelist.add(service.getNameByMnumS(li.getMnum()));
		}
		request.setAttribute("blist", blist); // jsp한테 보내줌
		request.setAttribute("namelist", namelist);
		
		
		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void content(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		Long bnum = Long.parseLong(request.getParameter("bnum"));
		System.out.println("bnum: "+bnum);
		Board board = service.getContentS(bnum);	
		String name = service.getNameByMnumS(board.getMnum());
		HttpSession session  = request.getSession();
		String username =(String)session.getAttribute("userid");
		
		request.setAttribute("board", board); // jsp한테 보내줌
		request.setAttribute("name", name);
		service.updateViewS(bnum);
		String view = "content.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		Long bnum = Long.parseLong(request.getParameter("bnum"));
		service.deleteS(bnum);
		
		response.sendRedirect("board.do?m=list");
	}
	private void input(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("input.jsp");
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		String btitle = request.getParameter("btitle");
		HttpSession session = request.getSession();		
		long mnum = (Long)session.getAttribute("usermnum");
		String mname = (String)session.getAttribute("userid");
		String bcategory = request.getParameter("bcategory");
		String bcontent = request.getParameter("bcontent");
		String bfile = request.getParameter("file");
		service.insertS(bcategory, btitle, mnum, mname, bcontent, bfile);
		
		response.sendRedirect("board.do");
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		Long bnum = Long.parseLong(request.getParameter("bnum"));
		Board board = service.getContentS(bnum);	
		String name = service.getNameByMnumS(board.getMnum());
		
		request.setAttribute("board", board); // jsp한테 보내줌
		request.setAttribute("name", name);
		
		String view = "updateform.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void updateDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service = BoardService.getInstance();
		Long bnum = Long.parseLong(request.getParameter("bnum"));
		String bcategory=request.getParameter("bcategory");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bfile = request.getParameter("bfile");
		String mname = request.getParameter("mname");
		Board board = new Board(bnum, bcategory, btitle, 1, mname, bcontent, bfile, -1, -1, null);
		service.updateDoS(board);
		
		String view = "board.do";
		response.sendRedirect(view);
	}
}
