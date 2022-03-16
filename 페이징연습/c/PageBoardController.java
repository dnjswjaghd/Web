package page.board.control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import page.board.domain.PageBoard;
import page.board.model.FileSet;
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
				case "content": content(request, response); break;
				case "input": input(request, response); break;
				case "upload": upload(request, response); break;
				case "download": download(request, response); break;
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
		int page = 1;
		String pageStr=request.getParameter("page");
		if(pageStr != null) {
			page = Integer.parseInt(pageStr);
		}
		int totalCount = list.size();
		int totalPage = (int) Math.ceil(totalCount / (double) ps);
		System.out.println("totalpage: "+totalPage);
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
		if (ps>totalCount) {
			ps=totalCount;
		}
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("page", page);
		request.setAttribute("ps", ps);
		
		//2. View 지정(jsp)
		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void content(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageBoardService service = PageBoardService.getInstance();
		
		
		response.sendRedirect("content.jsp");
	}
	private void input(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageBoardService service = PageBoardService.getInstance();
		
		
		String view = "input.jsp";
		response.sendRedirect(view);
	}
	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageBoardService service = PageBoardService.getInstance();
		String saveDir = FileSet.FILE_DIR;
		File fSaveDir =new File(saveDir);
		if(!fSaveDir.exists()) fSaveDir.mkdirs();
		
		MultipartRequest mr = null;
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		int maxPostSize = 1*1024*1024;
		try {
			mr = new MultipartRequest(request, saveDir, maxPostSize,"utf-8", policy);
		}catch(IOException ie) {
			System.out.println("upload실패 FileSet/111 line");
		}
		
		String fname = mr.getFilesystemName("fname");
		String ofname = mr.getOriginalFileName("fname");
		
		String view = "board.do";
		response.sendRedirect(view);
	}
	private void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageBoardService service = PageBoardService.getInstance();
		String saveDir = FileSet.FILE_DIR;
		String fname = request.getParameter("fname");
		File f = new File(saveDir, fname);
		
		response.setContentType("application/octet-stream"); 
		String Agent=request.getHeader("USER-AGENT");
		if( Agent.indexOf("MSIE") >= 0 ){
			int i = Agent.indexOf( 'M', 2 );
			String IEV = Agent.substring( i + 5, i + 8 );
			if( IEV.equalsIgnoreCase("5.5") ){
				response.setHeader("Content-Disposition", "filename=" + new String( fname.getBytes("utf-8"), "8859_1") );
			}else{
				response.setHeader("Content-Disposition", "attachment;filename="+new String(fname.getBytes("utf-8"),"8859_1"));
			}
		}else{
			response.setHeader("Content-Disposition", "attachment;filename=" + new String( fname.getBytes("utf-8"), "8859_1") );
		}
		
		if(f.exists() && f.isFile()) {
			FileInputStream fis = null;
			OutputStream os = null;
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				fis = new FileInputStream(f); // Data Source(서버측 파일)
				bis = new BufferedInputStream(fis, 2048);
				os = response.getOutputStream();// Data Destination(클라이언트 브라우저) 클라이언트객체에 쓰고싶다-> response 
				bos = new BufferedOutputStream(os, 2048);
				
				int count = 0;
				byte b[] = new byte[1024];
				while((count = bis.read(b)) != -1) {
					bos.write(b, 0, count);
				}
				bos.flush();
			}catch(IOException ie) {
				
			}finally {
				if(bis != null) bis.close();
				if(bos != null) bos.close();
				if(os!=null) os.close();
				if(fis!=null) fis.close();
			}
		}
		
		String view = "input.jsp";
		response.sendRedirect(view);
	}
}
