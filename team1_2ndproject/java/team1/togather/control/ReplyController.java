package team1.togather.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team1.togather.domain.Reply;
import team1.togather.model.ReplyService;

@WebServlet("/board/reply.do")
public class ReplyController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    
    public ReplyController() {
        super();
    }

   public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String m =request.getParameter("m");
      if(m!=null) {
         m = m.trim();
         switch(m){
            case "list": list(request,response); break;
            case "input": input(request,response); break;
            case "insert":insert(request,response);break;
         }
      }else {
         list(request,response);
      }
   }
   private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      ReplyService service = ReplyService.getInstance();
      long bnum = Long.parseLong(request.getParameter("bnum"));
      ArrayList<Reply> list = service.listS(bnum);
      request.setAttribute("rlist", list);
      String view ="board.do?m=content&bnum="+bnum+"";
    
      RequestDispatcher rd =request.getRequestDispatcher(view);
      rd.forward(request, response);
      
   }
   
   private void input(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{      
         String view ="input.jsp";
         RequestDispatcher rd =request.getRequestDispatcher(view);
         rd.forward(request, response);
   }
   
   private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      ReplyService service =ReplyService.getInstance();
      
      long bnum =getBnum(request);
      System.out.println("bnum은1: " + bnum);
      String mname = request.getParameter("mname");
      System.out.println("mname은1: " + mname);
      long mnum =getMnum(request);
      System.out.println("mnum은1: " + mnum);
      String content = request.getParameter("content");
      System.out.println("content은1: " + content);
      
      Reply dto = new Reply(-1,bnum,mname,mnum,content,-1, null, -1);
      boolean flag = service.insertS(dto);
      request.setAttribute("flag", flag);
      
      String view ="insert.jsp";
      RequestDispatcher rd =request.getRequestDispatcher(view);
      rd.forward(request, response);
      
   }
   
   private long getBnum(HttpServletRequest request){
       long bnum = -1;
      String bnumStr = request.getParameter("bnum");
      if(bnumStr != null){
         bnumStr = bnumStr.trim();
         if(bnumStr.length() != 0){
            try{
               bnum = Long.parseLong(bnumStr); 
               return bnum;
            }catch(NumberFormatException ne){
            }
         }
      }
      return bnum;
   }
   
   private long getMnum(HttpServletRequest request){
       long mnum = -1;
      String mnumStr = request.getParameter("mnum");
      if(mnumStr != null){
         mnumStr = mnumStr.trim();
         if(mnumStr.length() != 0){
            try{
               mnum = Long.parseLong(mnumStr); 
               return mnum;
            }catch(NumberFormatException ne){
            }
         }
      }
      return mnum;
   }
   
   
}