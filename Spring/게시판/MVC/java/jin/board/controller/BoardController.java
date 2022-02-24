package jin.board.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jin.board.domain.Board;
import jin.board.domain.BoardListResult;
import jin.board.service.BoardService;
import jin.md.fileset.Path;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired	
	private BoardService boardService;
	
	@GetMapping("list.do")
	   public ModelAndView list(HttpServletRequest request, HttpSession session) {
	      String cpStr = request.getParameter("cp");
	      String psStr = request.getParameter("ps");
	      String option = null;
	      String ocontent = null;
	      boolean optionFlag = false;
	      request.setAttribute("optionFlag", optionFlag);
	      BoardListResult listResult = null;
	      if(request.getParameter("option")!=null) {
	    	  option = request.getParameter("option");
	    	  session.setAttribute("soption", option);
	      }
	      if(request.getParameter("ocontent")!=null) {
	    	  ocontent = request.getParameter("ocontent");
	    	  session.setAttribute("socontent", ocontent);
	      }
	      //(1) cp 
	      int cp = 1;
	      if(cpStr == null) {
	         Object cpObj = session.getAttribute("cp");
	         if(cpObj != null) {
	            cp = (Integer)cpObj;
	         }
	      }else {
	         cpStr = cpStr.trim();
	         cp = Integer.parseInt(cpStr);
	      }
	      session.setAttribute("cp", cp);
	      
	      //(2) ps 
	      int ps = 3;
	      if(psStr == null) {
	         Object psObj = session.getAttribute("ps");
	         if(psObj != null) {
	            ps = (Integer)psObj;
	         }
	      }else {
	         psStr = psStr.trim();
	         int psParam = Integer.parseInt(psStr);
	         
	         Object psObj = session.getAttribute("ps");
	         if(psObj != null) {
	            int psSession = (Integer)psObj;
	            if(psSession != psParam) {
	               cp = 1;
	               session.setAttribute("cp", cp);
	            }
	         }else {
	            if(ps != psParam) {
	               cp = 1;
	               session.setAttribute("cp", cp);
	            }
	         }
	         ps = psParam;
	      }
	      session.setAttribute("ps", ps);
	      
	      
	      
	      //(3) ModelAndView 
	      if(ocontent!=null) {
	    	  listResult = boardService.getBoardListResult(cp, ps, option, ocontent);
	    	  optionFlag = true;
	    	  System.out.println("optionflag값: "+optionFlag);
	    	  request.setAttribute("optionFlag", optionFlag);
	    	  request.setAttribute("option", option);
	    	  request.setAttribute("ocontent", ocontent);
	      }else if(ocontent==null){
	    	  listResult = boardService.getBoardListResult(cp, ps);
	    	  optionFlag = false;
	    	  request.setAttribute("optionFlag", optionFlag);
	    	  System.out.println("optionflag값: "+optionFlag);
	      }
	      ModelAndView mv = new ModelAndView("board/list", "listResult", listResult);
	      
	      if(listResult.getList().size() == 0) {
	         if(cp > 1)
	            return new ModelAndView("redirect:list.do?cp="+(cp-1));
	         else 
	            return new ModelAndView("board/list", "listResult", null);
	      }else {
	         return mv;
	      }
	   }
	 @GetMapping("write.do")
	   public String write() {
	      return "board/write";
	   }
	   @PostMapping("write.do")
	   public String write(Board board, @RequestParam MultipartFile file) {
	      boardService.write(board);
	      String ofname = file.getOriginalFilename(); 
			if(ofname != null) ofname = ofname.trim();
			if(ofname.length() != 0) {
				System.out.println("boardcontroller write안 getseq: "+ board.getSeq());
				String url = boardService.saveStore(board.getSeq(),file);
				System.out.println("#url: "+url);
			}
	      return "redirect:list.do?cp=1";
	   }
	   @GetMapping("download.do")
		public ModelAndView download(String fname) {
			File file = new File(Path.FILE_STORE, fname);
			if(file.exists()) {
				return new ModelAndView("fileDownloadView", "downloadFile", file);
			}else {
				return new ModelAndView("redirect:list.do");
			}
		}
	   @GetMapping("content.do")
	   public ModelAndView content(long seq, String fname, HttpServletRequest request) {
		  System.out.println("seq: "+seq);
	      Board board = boardService.getBoard(seq);
	      File fStore = new File(Path.FILE_STORE+seq+"/");
			if(!fStore.exists()) fStore.mkdirs();
			File files[] = fStore.listFiles();
		  request.setAttribute("seq",seq);
	      ModelAndView mv = new ModelAndView("board/content", "board", board);
	      mv.setViewName("board/content");
	      mv.addObject("files", files);
	      return mv;
	   }
	   @GetMapping("update.do")
	   public ModelAndView update(long seq) {
	      Board board = boardService.getBoard(seq);
	      ModelAndView mv = new ModelAndView("board/update", "board", board);
	      
	      return mv;
	   }
	   @PostMapping("update.do")
	   public String update(Board board) {
	      boardService.edit(board);
	      return "redirect:list.do";
	   }
	   @GetMapping("del.do")
	   public String delete(long seq) {
	      boardService.remove(seq);
	      return "redirect:list.do";
	   }
	}