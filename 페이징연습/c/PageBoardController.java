package page.board.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import page.board.domain.PageBoard;
import page.board.model.PageBoardService;




@WebServlet("/page/board.do")
public class PageBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			switch (m) {
				case "list": list(request, response); break;
			}
		}else {
			list(request, response);
		}
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//service의 역할
		//1. Model 핸들링(java)
		PageBoardService service = PageBoardService.getInstance();
		int ps=0;
		if(request.getParameter("ps")!=null) {
			String psStr =request.getParameter("ps");
			ps = Integer.parseInt(psStr);
		}else {
			ps = 3;
		}
		ArrayList<PageBoard> list = service.listS(ps);
		int page = 1; // 시작 페이지
		int totalCount = list.size();
		int totalPage = (int) Math.ceil(totalCount / (double) ps);
		int pageCount = 5;
		int startPage = ((page - 1) / pageCount) * pageCount + 1;
		int endPage = startPage + pageCount - 1;
		// 추가로 조건 설정
		if( totalPage < page) {
			page = totalPage;
		}
		if ( endPage > totalPage) {
			endPage = totalPage;
		}
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("page", page);
		
		//2. View 지정(jsp)
		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}
