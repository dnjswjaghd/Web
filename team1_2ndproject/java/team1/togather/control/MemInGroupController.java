package team1.togather.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team1.togather.domain.MemInGroup;
import team1.togather.model.MemInGroupService;


@WebServlet("/group/MemInGroup.do")
public class MemInGroupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemInGroupController() {
        super();
        
    }
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m!=null) {
			m = m.trim();
			switch(m){
			case "MemInGroupgroupjoin" : MemInGroupgroupjoin(request,response);break;//모입가입하기
			}
		}else {
			response.sendRedirect("../");
		}	
	}
    private void MemInGroupgroupjoin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	MemInGroupService service = MemInGroupService.getInstance();
    	String gseqstr = request.getParameter("gseq");
    	long gSeq = Long.parseLong(gseqstr);
    	request.setAttribute("groupInfo_gSeq",gSeq);
    	HttpSession session = request.getSession(true);
    	long mNum= (Long)session.getAttribute("usermnum");
    	System.out.println("세션:"+(Long)session.getAttribute("usermnum"));
    	System.out.println("long:"+mNum);
    	
    	MemInGroup dto = new MemInGroup(gSeq,mNum,-1);
    	 service.MemInGroupJoinS(dto);
    	String view ="groupTab.do?m=groupInfo&gSeq="+gSeq+"";
    	RequestDispatcher rd =request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	
}
